package front;

import middle.GenericCard;
import util.PlaceHoldersUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Map;

public class CardPane extends JPanel {

    private Image backgroundImage;
    public double zoom = 1d;
    private GenericCard card ;
    private PlaceHolders placeHolders;
    private Point pointClicked;

    public void setCard(GenericCard card) {
        this.card = card;
    }

    private Point pointClicked2;
    private Point tmpPoint;
    private Point tmpPoint2;
    public Font tmpFontPlaceHolder;
    public Point backgroundImagePosition = new Point(0,0);

    public Point getTmpPoint() {
        return tmpPoint;
    }
    public Point getTmpPoint2() {
        return tmpPoint2;
    }
    public void setTmpPoint(Point tmpPoint) {
        this.tmpPoint = tmpPoint;
    }
    public void setTmpPoint2(Point tmpPoint2) {
        this.tmpPoint2 = tmpPoint2;
    }
    public void setPointClicked(Point pointClicked) {
        this.pointClicked = pointClicked;
    }
    public Point getPointClicked() {
        return pointClicked;
    }
    public void setPointClicked2(Point pointClicked2) {
        this.pointClicked2 = pointClicked2;
    }
    public Point getPointClicked2() {
        return pointClicked2;
    }
    public Point getBackgroundImagePosition() {
        return backgroundImagePosition;
    }


    public CardPane(GenericCard card) {
        this.card = card;
        loadBackgroundImage();
        setPreferredSize(new Dimension(PlaceHoldersUtil.WIDTH, PlaceHoldersUtil.HEIGHT));
        placeHolders = PlaceHoldersUtil.getPlaceHoldersScrumGame();


    }

    private void loadBackgroundImage() {
        try {
            backgroundImage = ImageIO.read(getClass().getClassLoader().getResourceAsStream("TemplateALarrache.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addNewPlaceHolder(PlaceHolder placeHolder){
        placeHolders.addPlaceHolder(placeHolder);
    }

   @Override
    public void paint(Graphics g) {

        g.setColor(Color.black);
        g.clearRect(0,0,this.getWidth(),this.getHeight());

       //((Graphics2D)g).setTransform(AffineTransform.getScaleInstance(zoom,zoom));
       //((Graphics2D)g).setTransform(new AffineTransform());

        drawBackground(g);
        drawCard(g,card);

        if (pointClicked!=null && tmpPoint != null) {
            int maxX = Math.max(tmpPoint.x, pointClicked.x);
            int maxY = Math.max(tmpPoint.y, pointClicked.y);

            int minX = Math.min(tmpPoint.x, pointClicked.x);
            int minY = Math.min(tmpPoint.y, pointClicked.y);

            /*int.x > pointClicked.x) {
                maxX = tmpPoint.x;
                minX = pointClicked.x;
            }*/

            ((Graphics2D)g).setStroke(new BasicStroke(1.0f,
                    BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_MITER,
                    10.0f, new float[]{2.0f}, 0.0f));
            g.drawRect(minX, minY, maxX-minX, maxY-minY);
            System.out.println(placeHolders.getPlaceHolderList().size());
        }
   }

    private void drawBackground(Graphics g) {
        g.drawImage(backgroundImage, backgroundImagePosition.x, backgroundImagePosition.y,PlaceHoldersUtil.WIDTH,PlaceHoldersUtil.HEIGHT, this);
    }

    private void drawCard(Graphics g, GenericCard card)
    {
        for (PlaceHolder placeHolder: this.placeHolders.getPlaceHolderList()) {

            System.out.println(placeHolder.getType().toString());
            placeHolder.setText(card.getData(placeHolder.getType().toString()));
          // placeHolder.setText(card.getText(placeHolder.getType()));
            drawPlaceHolder(g,placeHolder);
        }
    }
    public void TEST(){
        System.out.println("TAILLE :"+placeHolders.getPlaceHolderList().size());
    }

    public void drawPlaceHolder(Graphics g, PlaceHolder placeHolder ) {

        g.setFont(placeHolder.getFont());
        FontMetrics fontMetrics = g.getFontMetrics();
        Map<Point,String> map = PlaceHoldersUtil.getPositionCenteredInPlaceHolders(placeHolder,fontMetrics);
        for (Point p:map.keySet()) {
            g.drawString(map.get(p),p.x,p.y);
        }
    }
}
