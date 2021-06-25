package front;

import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
import middle.Card;
import middle.CardManager;
import util.FontUtil;

import javax.swing.*;
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

    public MainPane() {

        super(new BorderLayout());
        CardManager cardManager = new CardManager();
        cardManager.load();

        List<Card> cards = cardManager.getCards().getCards();
        this.commandPane = new CommandPane(cards);
        this.cardPane = commandPane.getCardPane();
        setBottomComponent(commandPane);
        add(cardPane, BorderLayout.CENTER);

        JToolBar bar = new JToolBar();
        add(bar, BorderLayout.PAGE_START);

        Icon moveIcon = IconFontSwing.buildIcon(FontAwesome.ARROWS_ALT, 20, Color.WHITE);
        JToggleButton moveButton = new JToggleButton(moveIcon);
        bar.add(moveButton);
        Icon addPlaceHolder = IconFontSwing.buildIcon(FontAwesome.PLUS, 20, Color.WHITE);
        JToggleButton addPlaceHolderButton = new JToggleButton(addPlaceHolder);
        bar.add(addPlaceHolderButton);

        //Icon drawIcon = IconFontSwing.buildIcon(FontAwesome.)
        JToggleButton drawCard = new JToggleButton();

        ButtonGroup group = new ButtonGroup();
        group.add(moveButton);
        group.add(addPlaceHolderButton);

        MouseListener moveCardMouseListener = new MoveCardMouseListener(cardPane);
        MouseMotionListener moveCardMouseMotionListener = new MoveCardMouseMotionListener(cardPane);
        MouseListener addPlaceHolderMouseListener = new AddPlaceHolderMouseListener(cardPane) ;
        MouseMotionListener addPlaceHolderMouseMotionListener = new AddPlaceHolderMouseMotionListener(cardPane);

        moveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardPane.removeMouseListener(addPlaceHolderMouseListener);
                cardPane.removeMouseMotionListener(addPlaceHolderMouseMotionListener);

                cardPane.addMouseListener(moveCardMouseListener);
                cardPane.addMouseMotionListener(moveCardMouseMotionListener);

            }
        });

        addPlaceHolderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardPane.removeMouseListener(moveCardMouseListener);
                cardPane.removeMouseMotionListener(moveCardMouseMotionListener);

                cardPane.addMouseListener(addPlaceHolderMouseListener);
                cardPane.addMouseMotionListener(addPlaceHolderMouseMotionListener);
            }
        });



    }

    public void setTopComponent(JPanel component) {
        add(component, BorderLayout.PAGE_START);
    }
    public void setBottomComponent(JPanel component) {
        add(component, BorderLayout.PAGE_END);
    }




}
