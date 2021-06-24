package main;

import org.pushingpixels.substance.api.skin.SubstanceGraphiteLookAndFeel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

public class SwingDemoMain {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Card");

        JFrame.setDefaultLookAndFeelDecorated(true);
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    try {
                        UIManager.setLookAndFeel(new SubstanceGraphiteLookAndFeel());

                        JLabel centerLabel = new JLabel("Center");
                        centerLabel.setBorder(BorderFactory.createEtchedBorder());

                        JButton b = new JButton("Submit");
                        b.addActionListener(e -> {
                            b.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                            new Thread(() -> {
                                try { Thread.sleep(2000); } catch (Throwable t) {}
                                try {
                                    SwingUtilities.invokeAndWait(() -> b.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)));
                                } catch (InterruptedException interruptedException) {
                                    interruptedException.printStackTrace();
                                } catch (InvocationTargetException invocationTargetException) {
                                    invocationTargetException.printStackTrace();
                                }
                            }).start();
                        });

                        JPanel panel = new JPanel(new BorderLayout());
                        panel.add(new JLabel("North"), BorderLayout.PAGE_START);
                        panel.add(b, BorderLayout.PAGE_END);
                        panel.add(new JLabel("East"), BorderLayout.LINE_END);
                        panel.add(new JLabel("West"), BorderLayout.LINE_START);
                        panel.add(centerLabel, BorderLayout.CENTER);

                        frame.setContentPane(panel);
                        frame.pack();
                        frame.setVisible(true);

                    } catch (Exception e) {
                        System.out.println("Substance Graphite failed to initialize");
                    }
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

}
