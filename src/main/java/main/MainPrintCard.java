package main;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Image;

public class MainPrintCard {


    public static void main(String[] args) {

        Document document = new Document();
        String input = "C:\\Users\\Lila\\Travail\\CardGenerator\\CardGenerator\\card1.png"; // .gif and .jpg are ok too!
        String output = "C:\\Users\\Lila\\Travail\\CardGenerator\\CardGenerator\\pdf.pdf";

        try {
            FileOutputStream fos = new FileOutputStream(output);
            PdfWriter writer = PdfWriter.getInstance(document, fos);
            writer.open();
            document.open();
            document.add(Image.getInstance(input));


            document.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}