package main;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class MainPrintCard {
    private static Document document;
    private static int widthDocument= 380;
    private static int heightDocument= 800;
    private static int widthCard = 380/2;
    private static int heightCard= 820/3;

    public static void main(String[] args) {

        // Creating a PdfWriter
        String dest = "addingImage.pdf";
        PdfWriter writer = null;
        try {
            writer = new PdfWriter(dest);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PdfDocument pdf = new PdfDocument(writer);
        document = new Document(pdf);
        File file = new File("cartes3D");
        String[] truc = file.list((dir, name) -> {
            if(name.endsWith(".png")) return true;
            return false;
        });
        List<Image> images = new ArrayList<>();
        for (String t:truc) {
            try {
                System.out.println("t :"+t);
                images.add(new Image(ImageDataFactory.create("cartes3D/"+t)));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        for (Image img:addListImages(images)) {
            document.add(img);
        }
        // Closing the document
        document.close();
        System.out.println();
        System.out.println("Images added");
    }
    public static List<Image> addListImages(List<Image> images){
        int i = 0;
        int j = 0;
        int numPage = 1;
        for (Image img:images) {
            if(i==3) i=0;
            if(j==3) j=0;
            img.setFixedPosition(numPage,i*widthCard,j*heightCard,widthCard);
            System.out.println("posY :"+j*heightCard);
            System.out.println("posX :"+i*widthCard);
            if( i%2==0 &&i>0)j++;
            i++;
            if(i==3&&j==3)numPage++;
        }
        return images;
    }

}