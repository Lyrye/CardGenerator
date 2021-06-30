package front;

import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
import middle.GenericCard;
import middle.GenericCardManager;
import say.swing.JFontChooser;
import util.FontUtil;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

public class MainPane extends JPanel {

    private int index = 0;
    private CardPane cardPane;
    private CommandPane commandPane;
    private List<GenericCard> cards;
    private ActionEventSwitchCardPanel actionEventSwitchCardPanel;
    private GenericCardManager manager;
    private JToolBar toolBar;
    private JComboBox placeHolderChoice;
    private MouseListener moveCardMouseListener ;
    private MouseMotionListener moveCardMouseMotionListener;
    private MouseListener addPlaceHolderMouseListener ;
    private MouseMotionListener addPlaceHolderMouseMotionListener;
    private JToggleButton moveButton;
    private JToggleButton addPlaceHolderButton;
    private JButton fontChooserButton ;
    private JButton bgChooserButton;
    private JButton csvChooserButton;

    public MainPane() {

        super(new BorderLayout());

        actionEventSwitchCardPanel = new ActionEventSwitchCardPanel(this);
        this.cardPane = new CardPane();
        this.commandPane = new CommandPane(actionEventSwitchCardPanel);

        setBottomComponent(commandPane);
        add(cardPane, BorderLayout.CENTER);

        toolBar = new JToolBar();
        Icon csvChooserButtonIcon = IconFontSwing.buildIcon(FontAwesome.TABLE,20,Color.WHITE);
        csvChooserButton = getButton(csvChooserButtonIcon);
        addActionListenerCsvChooserButton();
        add(toolBar, BorderLayout.PAGE_START);
    }

    private String getPathFromChooser() {
        JFileChooser jFileChooser = new JFileChooser();
        FileFilter csvFilter = new FileNameExtensionFilter("les fichiers csv (*.csv)","csv");
        jFileChooser.addChoosableFileFilter(csvFilter);
        jFileChooser.setAcceptAllFileFilterUsed(false);
        jFileChooser.showOpenDialog(this);
        return jFileChooser.getSelectedFile().getAbsolutePath();
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

    private void constructToolBar() {

        createToolBarButtons();

        createPlaceHolderChoice();

        createButtonGroup();

        initializeListeners();

        cardPane.setTmpFontPlaceHolder(FontUtil.getFont(placeHolderChoice.getActionCommand()));
        moveButton.setSelected(true);
        UpdateActionMoveListener(addPlaceHolderMouseListener,addPlaceHolderMouseMotionListener,moveCardMouseListener,moveCardMouseMotionListener);

        addActionListenerMoveButton();
        addActionListenerAddPlaceHolderButton();
        addActionListenerPlaceHolderChoice();
        addActionListenerFontChooserButton();
        addActionListenerBgChooserButton();
    }
    private void createButtonGroup() {
        ButtonGroup group = new ButtonGroup();
        group.add(moveButton);
        group.add(addPlaceHolderButton);
    }
    private void createToolBarButtons() {
        Icon moveIcon = IconFontSwing.buildIcon(FontAwesome.ARROWS_ALT, 20, Color.WHITE);
        moveButton = getJToggleButton(moveIcon);

        Icon addPlaceHolderIcon = IconFontSwing.buildIcon(FontAwesome.PLUS, 20, Color.WHITE);
        addPlaceHolderButton = getJToggleButton(addPlaceHolderIcon);

        Icon fontChooserButtonIcon = IconFontSwing.buildIcon(FontAwesome.FONT,20,Color.WHITE);
        fontChooserButton = getButton(fontChooserButtonIcon);

        Icon bgChooserButtonIcon = IconFontSwing.buildIcon(FontAwesome.PICTURE_O,20,Color.WHITE);
        bgChooserButton = getButton(bgChooserButtonIcon);
    }

    private void addActionListenerCsvChooserButton(){
        csvChooserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager=null;
                cards=null;
                try{
                    toolBar.remove(fontChooserButton);
                    toolBar.remove(bgChooserButton);
                    toolBar.remove(addPlaceHolderButton);
                    toolBar.remove(moveButton);
                    toolBar.remove(placeHolderChoice);
                }catch(Exception exception){}

                fontChooserButton=null;
                bgChooserButton=null;
                addPlaceHolderButton=null;
                moveButton=null;
                placeHolderChoice=null;
                String pathCsv = getPathFromChooser();
                manager = new GenericCardManager(pathCsv);
                manager.load();
                cards = manager.getCards().getCards();
                commandPane.setIndex(index,cards.size());
                cardPane.setCard(cards.get(0));
                constructToolBar();
                revalidate();
            }
        });
    }
    private void addActionListenerBgChooserButton() {
        bgChooserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                FileFilter imageFilter = new FileNameExtensionFilter("Les fichiers images (*.png)","png");
                jFileChooser.addChoosableFileFilter(imageFilter);
                jFileChooser.setAcceptAllFileFilterUsed(false);
                jFileChooser.showOpenDialog(getParent());
                cardPane.setBackgroundImage(jFileChooser.getSelectedFile().getAbsolutePath());

            }
        });
    }
    private void addActionListenerFontChooserButton() {
        fontChooserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //POPUP
                JFontChooser jFontChooser = new JFontChooser();
                if (cardPane.getNextFont()!=null) jFontChooser.setSelectedFont(cardPane.getNextFont());
                JFrame fontFrameChooser = new JFrame();
                fontFrameChooser.setLayout(new BorderLayout());
                fontFrameChooser.add(jFontChooser,BorderLayout.CENTER);
                fontFrameChooser.setVisible(true);

                JButton fontChooserValidate = new JButton("Valider");
                fontFrameChooser.add(fontChooserValidate,BorderLayout.PAGE_END);
                fontFrameChooser.pack();
                fontChooserValidate.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fontFrameChooser.setVisible(false);
                        cardPane.setTmpFontPlaceHolder(jFontChooser.getSelectedFont());

                        /*JeB*/
                        System.out.println("JeB selected font: " + jFontChooser.getSelectedFont());
                        cardPane.setNextFont(jFontChooser.getSelectedFont());
                    }
                });
            }
        });
    }
    private void addActionListenerPlaceHolderChoice() {
        placeHolderChoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardPane.setTmpTypePlaceHolder (new PlaceHolderType(placeHolderChoice.getSelectedItem().toString()));
            }
        });
    }
    private void addActionListenerAddPlaceHolderButton() {
        addPlaceHolderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardPane.removeMouseListener(moveCardMouseListener);
                cardPane.removeMouseMotionListener(moveCardMouseMotionListener);

                cardPane.addMouseListener(addPlaceHolderMouseListener);
                cardPane.addMouseMotionListener(addPlaceHolderMouseMotionListener);
                cardPane.setTmpFontPlaceHolder(FontUtil.getFont(placeHolderChoice.getActionCommand()));
            }
        });
    }
    private void addActionListenerMoveButton() {
        moveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateActionMoveListener(addPlaceHolderMouseListener,addPlaceHolderMouseMotionListener,moveCardMouseListener,moveCardMouseMotionListener);
            }
        });
    }
    private void initializeListeners() {
        moveCardMouseListener = new MoveCardMouseListener(cardPane);
        moveCardMouseMotionListener = new MoveCardMouseMotionListener(cardPane);
        addPlaceHolderMouseListener = new AddPlaceHolderMouseListener(cardPane) ;
        addPlaceHolderMouseMotionListener = new AddPlaceHolderMouseMotionListener(cardPane);
    }
    private void  createPlaceHolderChoice() {
        Object[] types = cards.get(0).getColumns().toArray();

        System.out.println("Les types"+cards.get(0).getColumns().toArray()[0]);
        placeHolderChoice = new JComboBox(types);
        placeHolderChoice.setSelectedItem(types[0]);
        cardPane.setTmpTypePlaceHolder(new PlaceHolderType(placeHolderChoice.getSelectedItem().toString()));
        toolBar.add(placeHolderChoice);

    }
    private JToggleButton getJToggleButton(Icon icon) {

        JToggleButton moveButton = new JToggleButton(icon);
        toolBar.add(moveButton);
        return moveButton;
    }
    private JButton getButton(Icon icon){

        JButton button = new JButton(icon);
        toolBar.add(button);
        return button;

    }
    private void UpdateActionMoveListener(MouseListener addPlaceHolderMouseListener, MouseMotionListener addPlaceHolderMouseMotionListener, MouseListener moveCardMouseListener, MouseMotionListener moveCardMouseMotionListener) {
        cardPane.removeMouseListener(addPlaceHolderMouseListener);
        cardPane.removeMouseMotionListener(addPlaceHolderMouseMotionListener);
        cardPane.addMouseListener(moveCardMouseListener);
        cardPane.addMouseMotionListener(moveCardMouseMotionListener);
    }
    public void changeToNextCardPane( ) {
        if (index < cards.size()-1)
        {
            index ++;
            this.cardPane.setCard(cards.get(index));
            System.out.println("Index :"+index);
            cards.get(index).print();
            commandPane.updateCountLabel(index);
            repaint();
        }
    }
    public void changeToPreviousCardPane( ) {
        if (index > 0)
        {
            index --;
            this.cardPane.setCard(cards.get(index));
            commandPane.updateCountLabel(index);
            repaint();
        }
    }
    public void setTopComponent(JPanel component) {
        add(component, BorderLayout.PAGE_START);
    }
    public void setBottomComponent(JPanel component) {
        add(component, BorderLayout.PAGE_END);
    }

}