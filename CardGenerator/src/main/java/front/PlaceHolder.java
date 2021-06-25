package front;

import java.awt.*;

public class PlaceHolder {

    private Font font = null;
    private Point upLeftCorner = null;
    private Point downRightCorner = null;
    private String text;

    public PlaceHolder(){}
    public PlaceHolder(Font font,Point upCorner, Point downCorner,String text){
        this.font = font;
        this.upLeftCorner = upCorner;
        this.downRightCorner=downCorner;
        this.text=text;
    }
    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Point getUpLeftCorner() {
        return upLeftCorner;
    }

    public void setUpLeftCorner(Point upCorner) {
        this.upLeftCorner = upCorner;
    }

    public Point getDownRightCorner() {
        return downRightCorner;
    }

    public void setDownRightCorner(Point downCorner) {
        this.downRightCorner = downCorner;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getHeight()
    {
        return this.downRightCorner.y - this.upLeftCorner.y;
    }
    public int getWidth()
    {
        return this.downRightCorner.x - this.upLeftCorner.x;
    }
}
