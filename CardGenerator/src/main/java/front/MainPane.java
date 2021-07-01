package front;

import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
import middle.GenericCard;
import middle.GenericCardManager;
import say.swing.JFontChooser;
import util.ScreenUtil;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

public class MainPane extends JPanel {

    private int index = 0;
    private final CardPane cardPane = new CardPane();
    private final CommandPane commandPane;
    private List<GenericCard> cards;

    private final MouseListener moveCardMouseListener = new MoveCardMouseListener(cardPane);
    private final MouseMotionListener moveCardMouseMotionListener = new MoveCardMouseMotionListener(cardPane);
    private final MouseListener addPlaceHolderMouseListener = new AddPlaceHolderMouseListener(cardPane);
    private final MouseMotionListener addPlaceHolderMouseMotionListener = new AddPlaceHolderMouseMotionListener(cardPane);

    MainPaneToolbar toolbar = new MainPaneToolbar();

    public MainPane() {
        super(new BorderLayout());
        commandPane = new CommandPane(new CommandPaneActionEvent(this));

        add(new JScrollPane(cardPane), BorderLayout.CENTER);
        add(toolbar, BorderLayout.PAGE_START);
        add(commandPane, BorderLayout.PAGE_END);
    }

    private String askPathFromChooser() {
        JFileChooser jFileChooser = new JFileChooser();
        FileFilter csvFilter = new FileNameExtensionFilter("les fichiers csv (*.csv)","csv");
        jFileChooser.addChoosableFileFilter(csvFilter);
        jFileChooser.setAcceptAllFileFilterUsed(false);
        jFileChooser.showOpenDialog(this);

        if (jFileChooser.getSelectedFile()!=null)
            return jFileChooser.getSelectedFile().getAbsolutePath();
        else
            return null;
    }
    public List<GenericCard> getCards() {
        return cards;
    }
    public CardPane getCardPane() {
        return cardPane;
    }
    public int getIndex() {
        return index;
    }

    private void loadFromCsv(String csvPath) {
        if (csvPath!=null) {
            GenericCardManager manager = new GenericCardManager(csvPath);
            manager.load();

            cards = manager.getCards();
            commandPane.setIndex(index,cards.size());
            cardPane.setCard(cards.get(0));

            toolbar.setDisplayMode(MainPaneToolbar.MainPaneToolbarDisplayMode.FULL);
            toolbar.updatePlaceholdersTypes(cards.get(0).getHeaders().toArray());
        }
    }

    private void loadBackgroundImage(){
        JFileChooser jFileChooser = new JFileChooser();
        FileFilter imageFilter = new FileNameExtensionFilter("Les fichiers images (*.png)","png");
        jFileChooser.addChoosableFileFilter(imageFilter);
        jFileChooser.setAcceptAllFileFilterUsed(false);
        jFileChooser.showOpenDialog(getParent());
        if (jFileChooser.getSelectedFile() != null) {
            cardPane.setBackgroundImage(jFileChooser.getSelectedFile().getAbsolutePath());
            ScreenUtil.pack(this);
        }
    }
    private void chooseFontFromChooser(){
        JFontChooser jFontChooser = new JFontChooser();
        if (cardPane.getNextFont()!=null) jFontChooser.setSelectedFont(cardPane.getNextFont());
        getFontChooserFrame(jFontChooser).setVisible(true);
    }

    private JFrame getFontChooserFrame(JFontChooser jFontChooser) {
        JFrame fontFrameChooser = new JFrame();
        fontFrameChooser.setLayout(new BorderLayout());
        fontFrameChooser.add(jFontChooser,BorderLayout.CENTER);
        JButton fontChooserValidate = new JButton("Valider");
        JPanel p = new JPanel();//todo : utiliser un GridbagLayout !
        p.add(fontChooserValidate,BorderLayout.CENTER);
        fontFrameChooser.add(p,BorderLayout.PAGE_END);
        fontFrameChooser.pack();
        fontChooserValidate.addActionListener(e -> {
            fontFrameChooser.setVisible(false);
            cardPane.setNextFont(jFontChooser.getSelectedFont());
        });
        ScreenUtil.center(fontFrameChooser);
        return fontFrameChooser;
    }

    public void changeToNextCard() {
        if (index < cards.size()-1) {
            index ++;
            cardPane.setCard(cards.get(index));
            commandPane.updateCountLabel(index);
        }
    }
    public void changeToPreviousCard() {
        if (index > 0) {
            index --;
            this.cardPane.setCard(cards.get(index));
            commandPane.updateCountLabel(index);
        }
    }

    private class MainPaneToolbar extends JToolBar {
        private enum MainPaneToolbarDisplayMode {
            LIGHT,
            FULL
        }
        private enum MainPaneToolbarTool {
            MOVE,
            ADD_PLACEHOLDER
        }

        private final JButton csvChooserButton = new JButton(IconFontSwing.buildIcon(FontAwesome.TABLE,20,Color.WHITE));
        private final JToggleButton moveButton = new JToggleButton(IconFontSwing.buildIcon(FontAwesome.ARROWS_ALT, 20, Color.WHITE));
        private final JToggleButton addPlaceHolderButton = new JToggleButton(IconFontSwing.buildIcon(FontAwesome.PLUS, 20, Color.WHITE));
        private final JButton fontChooserButton = new JButton(IconFontSwing.buildIcon(FontAwesome.FONT,20,Color.WHITE));
        private final JButton bgChooserButton = new JButton(IconFontSwing.buildIcon(FontAwesome.PICTURE_O,20,Color.WHITE));
        private final JComboBox placeHolderChoice = new JComboBox();

        public MainPaneToolbar() {
            initGUI();
            initListeners();
            setDisplayMode(MainPaneToolbarDisplayMode.LIGHT);
        }

        private void initListeners() {
            csvChooserButton.addActionListener(e -> loadFromCsv(askPathFromChooser()));
            moveButton.addActionListener(e -> setTool(MainPaneToolbarTool.MOVE));
            addPlaceHolderButton.addActionListener(e -> setTool(MainPaneToolbarTool.ADD_PLACEHOLDER));
            fontChooserButton.addActionListener(e -> chooseFontFromChooser());
            bgChooserButton.addActionListener(e -> loadBackgroundImage());
            placeHolderChoice.addActionListener(e -> cardPane.setNextPlaceHolderType(new PlaceHolderType(placeHolderChoice.getSelectedItem().toString())));
        }

        private void initGUI() {
            csvChooserButton.setToolTipText("Charger un fichier csv");
            add(csvChooserButton);
            addSeparator();
            add(moveButton);
            moveButton.setToolTipText("Déplacer l'image");
            addSeparator();
            add(bgChooserButton);
            bgChooserButton.setToolTipText("Charger une image de fond");
            add(fontChooserButton);
            fontChooserButton.setToolTipText("Choisir une police");
            add(placeHolderChoice);
            placeHolderChoice.setToolTipText("Choisir un type d'élément");
            add(addPlaceHolderButton);
            addPlaceHolderButton.setToolTipText("Ajouter un élément");

            ButtonGroup group = new ButtonGroup();
            group.add(moveButton);
            group.add(addPlaceHolderButton);
        }

        public void setDisplayMode(MainPaneToolbarDisplayMode mode) {
            if (mode == MainPaneToolbarDisplayMode.LIGHT) {
                setDisplayModeToLight();
            }
            if (mode == MainPaneToolbarDisplayMode.FULL) {
                setDisplayModeToFull();
            }
        }

        private void setDisplayModeToFull() {
            moveButton.setVisible(true);
            addPlaceHolderButton.setVisible(true);
            fontChooserButton.setVisible(true);
            bgChooserButton.setVisible(true);
            placeHolderChoice.setVisible(true);
        }

        private void setDisplayModeToLight() {
            moveButton.setVisible(false);
            addPlaceHolderButton.setVisible(false);
            fontChooserButton.setVisible(false);
            bgChooserButton.setVisible(false);
            placeHolderChoice.setVisible(false);
        }

        public void setTool(MainPaneToolbarTool tool) {
            if (tool == MainPaneToolbarTool.MOVE) {
                updateListersToMoveTool();
            }
            if (tool == MainPaneToolbarTool.ADD_PLACEHOLDER) {
                updateListersToAddPlaceholderTool();
            }
        }

        private void updateListersToAddPlaceholderTool() {
            cardPane.removeMouseListener(moveCardMouseListener);
            cardPane.removeMouseMotionListener(moveCardMouseMotionListener);
            cardPane.addMouseListener(addPlaceHolderMouseListener);
            cardPane.addMouseMotionListener(addPlaceHolderMouseMotionListener);
        }

        private void updateListersToMoveTool() {
            cardPane.addMouseListener(moveCardMouseListener);
            cardPane.addMouseMotionListener(moveCardMouseMotionListener);
            cardPane.removeMouseListener(addPlaceHolderMouseListener);
            cardPane.removeMouseMotionListener(addPlaceHolderMouseMotionListener);
        }

        public void updatePlaceholdersTypes(Object[] types) {
            placeHolderChoice.setModel(new DefaultComboBoxModel<>(types));
            if (placeHolderChoice.getItemCount()!=0) placeHolderChoice.setSelectedIndex(0);
        }
    }
}