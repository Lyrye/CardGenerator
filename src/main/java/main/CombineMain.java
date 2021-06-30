package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CombineMain {


    public static void main(String[] args) throws Throwable {
        final File rootDir = new File("C:\\Users\\wam\\IdeaProjects\\CardGenerator\\cardV2 - Copie");
        final String outPrefix = "C:\\Users\\wam\\IdeaProjects\\CardGenerator\\cardV2 - Copie\\Page";
        final int imageWidth = 696;
        final int imageHeight = 1074;

        File[] files = rootDir.listFiles();

        BufferedImage outImage = null;
        Graphics2D g2d = null;

        int count = 0;
        int x = 0;
        int y = 0;
        int pageNumber = 1;
        for (File f : files) {
            BufferedImage currentBufferedImage = currentBufferedImage = ImageIO.read(f);
            if (count % 9 == 0 ) {
                if (outImage!=null) {
                    g2d.dispose();
                    ImageIO.write(outImage, "png", new File(outPrefix + pageNumber + ".png"));
                    pageNumber++;
                }
                outImage = new BufferedImage(imageWidth*3, imageHeight*3, BufferedImage.TYPE_INT_RGB);
                g2d = outImage.createGraphics();
                x = 0;
                y = 0;
            }
            System.out.println("I'm drawing " + f.getName() + " at " + x + ", " +y);
            g2d.drawImage(currentBufferedImage, x, y,null);
            x += imageWidth;
            if (x+1 > imageWidth*3) {
                x = 0;
                y += imageHeight;
            }

            count++;
        }
    }
}
