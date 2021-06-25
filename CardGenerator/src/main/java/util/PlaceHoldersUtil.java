package util;

//import front.PlaceHolders;

import front.PlaceHolder;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PlaceHoldersUtil {

    public final static int HEIGHT = 774;
    public final static int WIDTH = 1194;

    public enum placeHolderType
    {
        ACTION_NAME,
        ROLE,
        DESCRIPTION,
        TO_REMEMBRE,
        IMPACT_IN_GAME;
    }


    /*public static Map<Point,String> getPositionMap(PlaceHolders.PlaceHoldersType type, FontMetrics fontMetrics, String text) {
        ArrayList<String> lines = getLines(fontMetrics,text,type);

        return getMap(lines,type,fontMetrics);
    }

    private static ArrayList<String> getLines(FontMetrics fontMetrics, String text, PlaceHolders.PlaceHoldersType type){
        ArrayList<String> lines = new ArrayList<String>();

        String[] mots = text.split(" ");
        String line = new String();

        for(int index=0;index<mots.length;index++){

            if (fontMetrics.stringWidth(line+" "+mots[index])<type.getWidth()) {
                line+=" "+mots[index];
            } else {
                lines.add(line);
                line = new String();
                line+=" "+mots[index];
            }

        }
        lines.add(line);
        return lines;
    }

    private static Map<Point,String> getMap(ArrayList<String> lines, PlaceHolders.PlaceHoldersType type, FontMetrics fontMetrics){

        Map<Point,String> map = new LinkedHashMap<Point,String>();
        for (int i=0;i<lines.size();i++) {

            int stringSize = fontMetrics.stringWidth(lines.get(i));
            int posX = ((type.getWidth() - stringSize)/2)+type.getUpCorner().x;
            int posY = (i+1)*((type.getHeight()/2)/lines.size())+type.getUpCorner().y;

            map.put(new Point(posX, posY),lines.get(i));
        }
        return map;
    }*/

    private static List<String> cutTextIntoLines(PlaceHolder placeHolder, FontMetrics fontMetrics)
    {
        ArrayList<String> lines = new ArrayList<String>();

        String[] mots = placeHolder.getText().split(" ");
        String line = new String();

        for(int index=0;index<mots.length;index++){

            if (fontMetrics.stringWidth(line+" "+mots[index])<placeHolder.getWidth()) {
                line+=" "+mots[index];
            } else {
                lines.add(line);
                line = new String();
                line+=" "+mots[index];
            }
        }
        lines.add(line);
        return lines;
    }

    public static Map<Point,String> getPositionCenteredInPlaceHolders (PlaceHolder placeHolder,FontMetrics fontMetrics)
    {
        List <String> lines = cutTextIntoLines(placeHolder,fontMetrics);
        Map<Point,String> map = new LinkedHashMap<Point,String>();
        for (int i=0;i<lines.size();i++) {

            int stringSize = fontMetrics.stringWidth(lines.get(i));
            int posX = ((placeHolder.getWidth() - stringSize)/2)+placeHolder.getUpLeftCorner().x;
            int posY = (i+1)*((placeHolder.getHeight()/2)/lines.size())+placeHolder.getUpLeftCorner().y;
            map.put(new Point(posX, posY),lines.get(i));
        }
        return map;
    }

}
