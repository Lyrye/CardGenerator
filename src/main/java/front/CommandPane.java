package front;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CommandPane extends JPanel{

    public static final String PREVIOUS = "<";
    public static final String NEXT = ">";
    public static final String SAVE = "Save";
    public static final String SAVE_ALL = "Save all";

    private int index = 0;
    private int sizeOfCardList = 0;
    private JLabel countLabel = new JLabel("?/?");
    private ActionEventSwitchCardPanel actionEventSwitchCardPanel;

    public CommandPane(ActionEventSwitchCardPanel actionEventSwitchCardPanel ,int index, int sizeOfCardList) {
        super(new BorderLayout());
        this.actionEventSwitchCardPanel = actionEventSwitchCardPanel;
        this.index = index+1; // Pour avoir un index qui commence à 1 au niveau de l'affichage
        this.sizeOfCardList= sizeOfCardList-1;
        createNavBar();
        createSaveOptions();
    }

    private void createSaveOptions() {
        add(getButton(SAVE,actionEventSwitchCardPanel), BorderLayout.CENTER);
        add(getButton(SAVE_ALL,actionEventSwitchCardPanel), BorderLayout.PAGE_END);
    }

    private void createNavBar() {
        JPanel navBar = new JPanel(new BorderLayout());
        countLabel.setText(index +"/"+ sizeOfCardList);
        countLabel.setHorizontalAlignment(JLabel.CENTER);
        navBar.add(getButton(PREVIOUS,actionEventSwitchCardPanel), BorderLayout.LINE_START);
        navBar.add(getButton(NEXT,actionEventSwitchCardPanel), BorderLayout.LINE_END);
        navBar.add(countLabel, BorderLayout.CENTER);
        add(navBar,BorderLayout.PAGE_START);
    }

    private static JButton getButton(String actionString, ActionListener listener) {
        JButton button = new JButton(actionString);
        button.setActionCommand(actionString);
        button.addActionListener(listener);
        return button;
    }

    public void updateCountLabel (int index)
    {
        countLabel.setText(index + "/" + sizeOfCardList);
    }
}
