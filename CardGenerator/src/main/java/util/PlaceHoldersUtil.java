package util;

import front.PlaceHolder;
import front.PlaceHolderType;
import front.Template;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PlaceHoldersUtil {


    private static List<String> cutTextIntoLines(PlaceHolder placeHolder, FontMetrics fontMetrics)
    {
        ArrayList<String> lines = new ArrayList<String>();

        System.out.println(placeHolder.toString());
        System.out.println(placeHolder.getText());
        String[] mots = placeHolder.getText().split(" ");
        String line = new String();

        for(int index=0;index<mots.length;index++){

            if (fontMetrics.stringWidth(line+" "+mots[index])<placeHolder.getWidth()) {
                if (line.equals("")) line = mots[index];
                else line+=" "+mots[index];
            } else {
                lines.add(line);
                line = new String();
                line+=/*" "+*/mots[index];
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
            //int posY = (i+1)*((placeHolder.getHeight()/2)/lines.size())+placeHolder.getUpLeftCorner().y;
            /*System.out.println(placeHolder.getHeight()/2);
            System.out.println(fontMetrics.getMaxDescent());
            System.out.println((fontMetrics.getMaxAscent()+fontMetrics.getMaxDescent())/2);
            System.out.println(fontMetrics.getLeading());
            System.out.println();*/
            //int posY = (placeHolder.getUpLeftCorner().y + placeHolder.getHeight()/2 - (fontMetrics.getAscent()+fontMetrics.getDescent())/2) +fontMetrics.getAscent();

            int posY =
                    placeHolder.getUpLeftCorner().y +
                            (i+1)*(placeHolder.getHeight())/(lines.size()+1) -
                            (fontMetrics.getAscent()+fontMetrics.getDescent())/(lines.size()+1) +
                            fontMetrics.getAscent();


            map.put(new Point(posX, posY),lines.get(i));
        }
        return map;
    }

    public static Template getPlaceHoldersScrumGame ()
    {
        Template template = new Template();
        template.addPlaceHolder(new PlaceHolder(FontUtil.smallFont,new Point(0,0),new  Point(10,10),"ROLE",new PlaceHolderType("Role")));
        template.addPlaceHolder(new PlaceHolder(FontUtil.bigFont,new Point(960/9,340/9),new Point(1067,159),"ACTION", new PlaceHolderType("ActionName")));
        template.addPlaceHolder(new PlaceHolder(FontUtil.mediumFont,new Point(480/9,2120/9),new Point(480/9+6945/9,2120/9+2380/9),"DESCRIPTION",new PlaceHolderType("ActionDescription")));
        template.addPlaceHolder(new PlaceHolder(FontUtil.mediumFont,new Point(7650/9,2120/9),new Point(7650/9+2420/9,2120/9+2380/9),"IMPACT IN GAME",new PlaceHolderType("ImpactInGame")));
        template.addPlaceHolder(new PlaceHolder(FontUtil.mediumFont,new Point(350/9,5240/9),new Point(350/9+10040/9,5240/9+1450/9),"TO REMEMBER",new PlaceHolderType("ToRemember")));

        return template;
    }

}