package main;

import front.*;
import org.pushingpixels.substance.api.skin.SubstanceGraphiteLookAndFeel;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class CardGeneratorSpringMain {

    public static void main(String[] args) {

        JFrame.setDefaultLookAndFeelDecorated(true);
        try {
            SwingUtilities.invokeAndWait(() -> {
                try {
                    UIManager.setLookAndFeel(new SubstanceGraphiteLookAndFeel());
                    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
                    MainPane mainPane = (MainPane) context.getBean("mainPaneId");
                    JFrame frame = new JFrame();
                    frame.setContentPane(mainPane);
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
