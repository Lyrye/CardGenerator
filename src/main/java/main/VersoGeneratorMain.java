package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class VersoGeneratorMain {
    public static void main(String[] args) throws Throwable {
        File outFile = new File("D:\\Travail\\jServices\\_cartes à jouer\\verso.png");
        BufferedImage currentBufferedImage = ImageIO.read(new File("D:\\Travail\\jServices\\_cartes à jouer\\feuPaysage.png"));
        int imageWidth = 696;
        int imageHeight = 1074;

        BufferedImage outImage = new BufferedImage(imageWidth*3, imageHeight*3, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = outImage.createGraphics();
        g2d.setColor(Color.white);
        g2d.fillRect(0,0,imageWidth*3, imageHeight*3);
        for (int i = 0; i < 3; i++) {
            for (int j =0; j < 3; j++) {
                g2d.drawImage(currentBufferedImage, i * imageWidth, j * imageHeight, null);
            }
        }
        g2d.dispose();
        ImageIO.write(outImage, "png", outFile);
    }
}
