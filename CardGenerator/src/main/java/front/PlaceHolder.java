package front;

import util.PlaceHoldersUtil;

import java.awt.*;

public class PlaceHolder {

    private Font font = null;
    private Point upLeftCorner = null;
    private Point downRightCorner = null;
    private String text;
    private PlaceHoldersUtil.placeHolderType type;

    public void setType(PlaceHoldersUtil.placeHolderType type) {
        this.type = type;
    }

    public PlaceHolder(){}
    public PlaceHolder(Font font,Point upCorner, Point downCorner,String text, PlaceHoldersUtil.placeHolderType type){
        this.font = font;
        this.upLeftCorner = upCorner;
        this.downRightCorner=downCorner;
        this.text=text;
        this.type = type;
    }

    public PlaceHoldersUtil.placeHolderType getType() {return this.type;}
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
