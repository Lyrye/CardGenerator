package front;

import middle.Card;
import util.PlaceHoldersUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Map;

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
        placeHolders.addPlaceHolder(new PlaceHolder(FontUtil.bigFont,new Point(960/9,340/9),new Point(1067,159),"ACTION",PlaceHoldersUtil.placeHolderType.ACTION_NAME));
        placeHolders.addPlaceHolder(new PlaceHolder(FontUtil.mediumFont,new Point(480/9,2120/9),new Point(480/9+6945/9,2120/9+2380/9),"DESCRIPTION",PlaceHoldersUtil.placeHolderType.DESCRIPTION));
        placeHolders.addPlaceHolder(new PlaceHolder(FontUtil.mediumFont,new Point(7650/9,2120/9),new Point(7650/9+2420/9,2120/9+2380/9),"IMPACT IN GAME",PlaceHoldersUtil.placeHolderType.IMPACT_IN_GAME));
        placeHolders.addPlaceHolder(new PlaceHolder(FontUtil.mediumFont,new Point(350/9,5240/9),new Point(350/9+10040/9,5240/9+1450/9),"TO REMEMBER",PlaceHoldersUtil.placeHolderType.TO_REMEMBRE));
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

    private void drawCard(Graphics g, Card card)
    {
        for (PlaceHolder placeHolder: this.placeHolders.getPlaceHolderList()) {
           placeHolder.setText(card.getText(placeHolder.getType()));
            drawPlaceHolder(g,placeHolder);

        }
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
