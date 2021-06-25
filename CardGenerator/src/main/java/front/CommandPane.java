package front;

import middle.Card;
import util.FileUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CommandPane extends JPanel implements ActionListener {
    private static final String PREVIOUS = "<";
    private static final String NEXT = ">";
    private static final String SAVE = "Save";
    private static final String SAVE_ALL = "Save all";

    private int index = 0;
    private List<Card> cards;

    private JLabel countLabel = new JLabel("?/?");
    private CardPane cardPane;

    public CommandPane(List<Card> cards) {
        super(new BorderLayout());
        this.cards = cards;
        this.cardPane = new CardPane(cards.get(index));

        createNavBar();
        createSaveOptions();
    }

    private void createSaveOptions() {
        add(getButton(SAVE,this), BorderLayout.CENTER);
        add(getButton(SAVE_ALL,this), BorderLayout.PAGE_END);
    }


    private void createNavBar() {
        JPanel navBar = new JPanel(new BorderLayout());
        countLabel.setHorizontalAlignment(JLabel.CENTER);
        navBar.add(getButton(PREVIOUS,this), BorderLayout.LINE_START);
        navBar.add(getButton(NEXT,this), BorderLayout.LINE_END);
        navBar.add(countLabel, BorderLayout.CENTER);
        add(navBar,BorderLayout.PAGE_START);
    }

    private static JButton getButton(String actionString, ActionListener listener) {
        JButton button = new JButton(actionString);
        button.setActionCommand(actionString);
        button.addActionListener(listener);
        return button;
    }
    public CardPane getCardPane ()
    {
        return this.cardPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case (SAVE):
                FileUtil.SaveImage(cardPane,"png","./card1.png");
                break;
            case(PREVIOUS):
                if (index >0) index --;
                break;
            case (NEXT):
                if (index< cards.size()) index ++;
                break;
        }
        updateCardPane();
    }

    private void updateCardPane()
    {
        this.cardPane = new CardPane(cards.get(index));
    }
}
