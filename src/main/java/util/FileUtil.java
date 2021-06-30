package util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class FileUtil {
    public static void SaveImage(JPanel panel, String formatName, String pathName){
        try {
            ImageIO.write(ImageUtil.createImage(panel), formatName, new File(pathName));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
