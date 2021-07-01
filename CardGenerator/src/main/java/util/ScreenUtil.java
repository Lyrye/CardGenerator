package util;

import javax.swing.*;
import java.awt.*;

public class ScreenUtil {

    static public void pack(JComponent c) {
        if (c.getTopLevelAncestor() instanceof JFrame) ((JFrame)c.getTopLevelAncestor()).pack();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = c.getSize();
        if (screenSize.width < frameSize.width) c.setSize(screenSize.width, c.getSize().height);
        if (screenSize.height < frameSize.height) c.setSize(c.getSize().width, screenSize.height);
    }

    public static void center(JFrame f) {//Todo : surement un bug si la frame est plus grande que l'écran !
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = f.getSize();

        f.setLocation((screenSize.width-frameSize.width)/2, (screenSize.height-frameSize.height)/2);
    }

}
