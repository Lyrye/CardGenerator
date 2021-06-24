package util;

import java.awt.*;
import java.util.*;

public class PlaceHoldersUtil {

    public final static int HEIGHT = 774;
    public final static int WIDTH = 1194;
    public static final Font fontAction = new Font("Arial",Font.PLAIN,55);
    public static final Font fontDescription = new Font("Arial",Font.PLAIN,30);
    public static final Font fontImpactInGame = new Font("Arial",Font.PLAIN,30);
    public static final Font fontRole = new Font("Arial",Font.PLAIN,20);
    public static final Font fontToRemember = new Font("Arial",Font.ITALIC,30);

    private static final int WIDTH_ACTION = 8640/9;
    private static final int WIDTH_DESC = 6945/9;
    private static final int WIDTH_IMPACT = 2420/9;
    private static final int WIDTH_TO_REMEMBER = 10040/9;
    private static final int HEIGHT_ACTION = 1090/9;
    private static final int HEIGHT_DESC = 2380/9;
    private static final int HEIGHT_IMPACT = 2380/9;
    private static final int HEIGHT_TO_REMEMBER = 1450/9;

    private static final Point UP_CORNER_ACTION = new Point(960/9,340/9);
    private static final Point UP_CORNER_DESC = new Point(480/9,2120/9);
    private static final Point UP_CORNER_IMPACT = new Point(7650/9,2120/9);
    private static final Point UP_CORNER_TO_REMEMBER = new Point(350/9,5240/9);

    public enum PlaceHoldersType{
        ACTION(fontAction,WIDTH_ACTION,HEIGHT_ACTION,UP_CORNER_ACTION),
        DESCRIPTION(fontDescription, WIDTH_DESC,HEIGHT_DESC,UP_CORNER_DESC),
        ROLE(fontRole, 0,0,new Point()),
        IMPACT_IN_GAME(fontImpactInGame, WIDTH_IMPACT,HEIGHT_IMPACT,UP_CORNER_IMPACT),
        TO_REMEMBER(fontToRemember, WIDTH_TO_REMEMBER,HEIGHT_TO_REMEMBER,UP_CORNER_TO_REMEMBER);

        private Font font;
        private int width;
        private int height;
        private Point upCorner;

        PlaceHoldersType(Font font, int width,int height,Point point) {
            this.font = font;
            this.width = width;
            this.height = height;
            this.upCorner = point;
        }

        public Font getFont() {return font;}
        public int getWidth(){return width;}
        public int getHeight(){return height;}
        public Point getUpCorner(){return upCorner;}
    }

    public static Map<Point,String> getPositionMap(PlaceHoldersType type, FontMetrics fontMetrics, String text) {
        ArrayList<String> lines = getLines(fontMetrics,text,type);

        return getMap(lines,type,fontMetrics);
    }

    private static ArrayList<String> getLines(FontMetrics fontMetrics, String text,PlaceHoldersType type){
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

    private static Map<Point,String> getMap(ArrayList<String> lines, PlaceHoldersType type,FontMetrics fontMetrics){

        Map<Point,String> map = new LinkedHashMap<Point,String>();
        for (int i=0;i<lines.size();i++) {

            int stringSize = fontMetrics.stringWidth(lines.get(i));
            int posX = ((type.getWidth() - stringSize)/2)+type.getUpCorner().x;
            int posY = (i+1)*((type.getHeight()/2)/lines.size())+type.getUpCorner().y;

            map.put(new Point(posX, posY),lines.get(i));
        }
        return map;
    }

}
