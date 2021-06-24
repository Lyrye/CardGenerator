package main;

import front.CardPane;
import front.CommandPane;
import front.MainPane;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
import middle.Card;
import middle.CardManager;
import org.pushingpixels.substance.api.skin.SubstanceGraphiteLookAndFeel;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class CardGeneratorMain {
    public static void main(String[] args) {
        IconFontSwing.register(FontAwesome.getIconFont());

        JFrame.setDefaultLookAndFeelDecorated(true);
        try {
            SwingUtilities.invokeAndWait(() -> {
                try {
                    UIManager.setLookAndFeel(new SubstanceGraphiteLookAndFeel());

                    JFrame frame = new JFrame("Static");
                    frame.setPreferredSize(new Dimension(500,500));
                    MainPane mp = new MainPane();
                    frame.setContentPane(mp);
                    frame.pack();
                    frame.setVisible(true);
                } catch (UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }



    }
}
