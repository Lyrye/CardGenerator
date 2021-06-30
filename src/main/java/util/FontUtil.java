package util;

import java.awt.*;

public class FontUtil {

    public static final Font bigFont = new Font("Arial",Font.PLAIN,55);
    public static final Font mediumFont = new Font("Arial",Font.PLAIN,30);
    public static final Font smallFont = new Font("Arial",Font.PLAIN,20);
    public static final String bigText = new String("Grand texte");
    public static final String mediumText = new String("Moyen texte");
    public static final String smallText = new String("Petit texte");

    public static Font getFont(String typeTexte){
        if(typeTexte.equals(bigText)) return bigFont;
        if(typeTexte.equals(smallText)) return smallFont;
        return mediumFont;
    }
}
