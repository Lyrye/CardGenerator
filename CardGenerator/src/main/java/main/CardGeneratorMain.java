package main;

import front.MainPane;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;
import org.pushingpixels.substance.api.skin.SubstanceGraphiteLookAndFeel;
import util.PlaceHoldersUtil;
import util.ScreenUtil;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;


public class CardGeneratorMain {
    public static void main(String[] args) {

        IconFontSwing.register(FontAwesome.getIconFont());
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());


        JFrame.setDefaultLookAndFeelDecorated(true);
        try {
            SwingUtilities.invokeAndWait(() -> {
                try {
                    UIManager.setLookAndFeel(new SubstanceGraphiteLookAndFeel());

                    ToolTipManager.sharedInstance().setInitialDelay(300);

                    JFrame frame = new JFrame("Générateur de cartes");
                    //frame.setPreferredSize(new Dimension(,PlaceHoldersUtil.HEIGHT));
                    MainPane mp = new MainPane();
                    frame.setContentPane(mp);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    ScreenUtil.center(frame);
                    frame.setVisible(true);
                } catch (UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
            e.getCause();
        } catch (InvocationTargetException e) {
            e.getCause();
            e.printStackTrace();
        }



    }
}
