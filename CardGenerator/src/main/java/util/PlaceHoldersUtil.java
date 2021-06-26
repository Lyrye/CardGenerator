package util;

import front.PlaceHolder;
import front.PlaceHolders;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PlaceHoldersUtil {

    public final static int HEIGHT = 774; // A changer de place -> pourquoi taille de l'image stocké dans une class qui parle de PlaceHolder
    public final static int WIDTH = 1194;

    public enum placeHolderType
    {
        ACTION_NAME,
        ROLE,
        DESCRIPTION,
        TO_REMEMBRE,
        IMPACT_IN_GAME;
    }

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

    public static PlaceHolders getPlaceHoldersScrumGame ()
    {
        PlaceHolders placeHolders = new PlaceHolders();
        placeHolders.addPlaceHolder(new PlaceHolder(FontUtil.bigFont,new Point(960/9,340/9),new Point(1067,159),"ACTION", PlaceHoldersUtil.placeHolderType.ACTION_NAME));
        placeHolders.addPlaceHolder(new PlaceHolder(FontUtil.mediumFont,new Point(480/9,2120/9),new Point(480/9+6945/9,2120/9+2380/9),"DESCRIPTION",PlaceHoldersUtil.placeHolderType.DESCRIPTION));
        placeHolders.addPlaceHolder(new PlaceHolder(FontUtil.mediumFont,new Point(7650/9,2120/9),new Point(7650/9+2420/9,2120/9+2380/9),"IMPACT IN GAME",PlaceHoldersUtil.placeHolderType.IMPACT_IN_GAME));
        placeHolders.addPlaceHolder(new PlaceHolder(FontUtil.mediumFont,new Point(350/9,5240/9),new Point(350/9+10040/9,5240/9+1450/9),"TO REMEMBER",PlaceHoldersUtil.placeHolderType.TO_REMEMBRE));

        return placeHolders;
    }

}
