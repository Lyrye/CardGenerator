package front;

import middle.Card;
import util.PlaceHoldersUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.Map;

import static util.PlaceHoldersUtil.*;

public class CardPane extends JPanel {

    private Image backgroundImage;
    public double zoom = 1d;
    private Card card ;
    private PlaceHolders placeHolders;
    private Point pointClicked;
    private Point tmpPoint;
    Point backgroundImagePosition = new Point(0,0);

    public Point getTmpPoint() {
        return tmpPoint;
    }
    public void setTmpPoint(Point tmpPoint) {
        this.tmpPoint = tmpPoint;
    }
    public void setPointClicked(Point pointClicked) {
        this.pointClicked = pointClicked;
    }
    public Point getPointClicked() {
        return pointClicked;
    }
    public Point getBackgroundImagePosition() {
        return backgroundImagePosition;
    }


    public CardPane(Card card) {
        this.card = card;
        try {
            backgroundImage = ImageIO.read(getClass().getClassLoader().getResourceAsStream("TemplateALarrache.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setPreferredSize(new Dimension(PlaceHoldersUtil.WIDTH, PlaceHoldersUtil.HEIGHT));
        placeHolders = new PlaceHolders();
    }

   @Override
    public void paint(Graphics g) {

        g.setColor(Color.black);
        g.clearRect(0,0,this.getWidth(),this.getHeight());

       //((Graphics2D)g).setTransform(AffineTransform.getScaleInstance(zoom,zoom));
       //((Graphics2D)g).setTransform(new AffineTransform());

        drawBackground(g);
        //drawCard(g,card);

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
        }
   }


    private void drawBackground(Graphics g) {
        //System.out.println("backgroundImagePosition.x : " + backgroundImagePosition.x);
        //System.out.println("backgroundImagePosition.y : " + backgroundImagePosition.y);
        g.drawImage(backgroundImage, backgroundImagePosition.x, backgroundImagePosition.y,PlaceHoldersUtil.WIDTH,PlaceHoldersUtil.HEIGHT, this);
    }

    /*private void drawCard(Graphics g, Card card)
    {
        drawPlaceHolder(g,card.getActionName(),PlaceHoldersType.ACTION);
        drawPlaceHolder(g,card.getActionDescription(),PlaceHoldersType.DESCRIPTION);
        drawPlaceHolder(g,card.getRole(),PlaceHoldersType.ROLE);
        drawPlaceHolder(g,card.getInpactInGame(),PlaceHoldersType.IMPACT_IN_GAME);
        drawPlaceHolder(g,card.getToRemember(),PlaceHoldersType.TO_REMEMBER);
    }

    private void drawPlaceHolder(Graphics g, String text, PlaceHoldersUtil.PlaceHoldersType type ) {

        g.setFont(type.getFont());
        FontMetrics fontMetrics = g.getFontMetrics();

        Map<Point,String> map = PlaceHoldersUtil.getPositionMap(type,fontMetrics,text);
        for (Point p:map.keySet()) {
            g.drawString(map.get(p),p.x,p.y);
        }
    }*/
}
