package front;

import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CommandPane extends JPanel{

    public static final String PREVIOUS = "<";
    public static final String NEXT = ">";
    public static final String SAVE = "Enregister la carte";
    public static final String SAVE_ALL = "Enregistrer toutes les cartes";

    private int index = 0;
    private int sizeOfCardList = 0;
    private JLabel countLabel = new JLabel("?/?");
    private CommandPaneActionEvent commandPaneActionEvent;

    public CommandPane(CommandPaneActionEvent commandPaneActionEvent) {
        super(new FlowLayout());
        this.commandPaneActionEvent = commandPaneActionEvent;
    }
    public void setIndex(int index, int sizeOfCards){
        this.index=index;
        this.sizeOfCardList = sizeOfCards;
        createButtons();
    }


    private void createButtons() {

        countLabel.setText(index +"/"+ sizeOfCardList);
        add(getButton(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.KEYBOARD_ARROW_LEFT,20,Color.WHITE),PREVIOUS, commandPaneActionEvent));
        add(getButton(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.SAVE,20,Color.WHITE),SAVE, commandPaneActionEvent) );
        add(countLabel);
        add(getButton(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.WEEKEND,20,Color.WHITE),SAVE_ALL, commandPaneActionEvent));
        add(getButton(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.KEYBOARD_ARROW_RIGHT,20,Color.WHITE),NEXT, commandPaneActionEvent));

    }

    private static JButton getButton(Icon icon, String actionString, ActionListener listener) {
        JButton button = new JButton(icon);
        button.setActionCommand(actionString);
        button.addActionListener(listener);
        button.setToolTipText(actionString);
        return button;
    }

    public void updateCountLabel (int index)
    {
        countLabel.setText(index + "/" + sizeOfCardList);
    }
}
