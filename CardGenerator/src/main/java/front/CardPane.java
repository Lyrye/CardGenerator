package front;

import middle.GenericCard;
import util.PlaceHoldersUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CardPane extends JPanel {

    private Image backgroundImage;
    public double zoom = 1d;
    private GenericCard card ;
    private Template template;
    private Point pointClicked;
    private Point pointReleased;
    private Font tmpFontPlaceHolder;
    private PlaceHolderType tmpTypePlaceHolder;
    private Point offset = new Point(0,0);
    private int backgroundHeight = 774;
    private int backgroundWidth = 1194;
    private Images images;

    private Font nextFont;
    /*JeB*/
    public void setNextFont(Font nextFont) {
        this.nextFont = nextFont;
    }
    public Font getNextFont() {
        return nextFont;
    }

    public Font getTmpFontPlaceHolder() {
        return tmpFontPlaceHolder;
    }
    public void setTmpFontPlaceHolder(Font tmpFontPlaceHolder) {
        this.tmpFontPlaceHolder = tmpFontPlaceHolder;
    }
    public PlaceHolderType getTmpTypePlaceHolder() {
        return tmpTypePlaceHolder;
    }
    public void setTmpTypePlaceHolder(PlaceHolderType tmpTypePlaceHolder) {
        this.tmpTypePlaceHolder = tmpTypePlaceHolder;
    }
    public Point getPointReleased() {
        return pointReleased;
    }
    public void setPointReleased(Point pointReleased) {
        this.pointReleased = pointReleased;
    }
    public void setCard(GenericCard card) {
        this.card = card;
    }
    public void setPointClicked(Point pointClicked) {
        this.pointClicked = pointClicked;
    }
    public Point getPointClicked() {
        return pointClicked;
    }
    public Point getOffset() {
        return offset;
    }
    public void setOffset(Point offset) {
        this.offset = offset;
    }
    public void setBackgroundImage(String path) {

        loadBackgroundImage(path);
    }

    public CardPane(GenericCard card) {
        this.card = card;
        setPreferredSize(new Dimension(backgroundWidth, backgroundHeight));
        template = new Template();
        this.setOpaque(true);
        this.setBackground(Color.WHITE);
        images = new Images();
        //placeHolders = PlaceHoldersUtil.getPlaceHoldersScrumGame();
    }

    private void loadBackgroundImage(String path) {
        try {
            System.out.println("path" +  path);
            backgroundImage = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        backgroundHeight = backgroundImage.getHeight(this);
        backgroundWidth = backgroundImage.getWidth(this);
        //setPreferredSize(new Dimension(backgroundWidth, backgroundHeight));
        setSize(new Dimension(backgroundWidth+ offset.x, backgroundHeight+ offset.y));
    }

    public void addNewPlaceHolder(PlaceHolder placeHolder){

        template.addPlaceHolder(placeHolder);
        drawPlaceHolder(this.getGraphics(),placeHolder);
    }

    @Override
    public void paint(Graphics g) {
        setSize(new Dimension(backgroundWidth, backgroundHeight));
        g.setColor(Color.black);
        g.clearRect(0,0,this.getWidth(),this.getHeight());

        //((Graphics2D)g).setTransform(AffineTransform.getScaleInstance(zoom,zoom));
        //((Graphics2D)g).setTransform(new AffineTransform());

        drawBackground(g);
        drawCard(g,card);

        if (pointClicked!=null && pointReleased != null) {
            int maxX = Math.max(pointReleased.x, pointClicked.x);
            int maxY = Math.max(pointReleased.y, pointClicked.y);

            int minX = Math.min(pointReleased.x, pointClicked.x);
            int minY = Math.min(pointReleased.y, pointClicked.y);

            /*int.x > pointClicked.x) {
                maxX = tmpPoint.x;
                minX = pointClicked.x;
            }*/

            ((Graphics2D)g).setStroke(new BasicStroke(1.0f,
                    BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_MITER,
                    10.0f, new float[]{2.0f}, 0.0f));
            g.drawRect(minX, minY, maxX-minX, maxY-minY);
        }
    }

    private void drawBackground(Graphics g) {
        g.drawImage(backgroundImage, offset.x, offset.y,backgroundWidth,backgroundHeight, this);
    }
    private void drawCard(Graphics g, GenericCard card) {
        for (PlaceHolder placeHolder: this.template.getPlaceHolderList()) {
            String data = card.getData(placeHolder.getType().toString());
            if (data == null) data = "Text null";
            placeHolder.setText(data);
            drawPlaceHolder(g,placeHolder);

        }
    }
    public void drawPlaceHolder(Graphics g, PlaceHolder placeHolder ) {
        if(placeHolder.getType().getType().contains("<<PNG>>")) {
            drawImage(g, placeHolder);
        }
        else{
            drawText(g, placeHolder);
        }
    }

    private void drawText(Graphics g, PlaceHolder placeHolder) {
        g.setFont(placeHolder.getFont());
        FontMetrics fontMetrics = g.getFontMetrics();
        Map<Point,String> map = PlaceHoldersUtil.getPositionCenteredInPlaceHolders(placeHolder,fontMetrics);
        for (Point p:map.keySet()) {
            g.drawString(map.get(p),p.x+ offset.x,p.y+ offset.y);
        }
    }

    private void drawImage(Graphics g, PlaceHolder placeHolder) {
        if(!images.exist(placeHolder.getText())){
            File file = new File(placeHolder.getText());
            if(file.exists()){
                try{
                    Image img = ImageIO.read(file);
                    images.addImage(placeHolder.getText(), img);
                }catch (IOException e){}
            }
        }
        if(images.exist(placeHolder.getText()))
            g.drawImage(images.getImage(placeHolder.getText()), placeHolder.getUpLeftCorner().x+ offset.x, placeHolder.getUpLeftCorner().y+ offset.y,placeHolder.getWidth(),placeHolder.getHeight(),this);
    }
}
