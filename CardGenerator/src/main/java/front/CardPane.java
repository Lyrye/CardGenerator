package front;

import middle.Card;
import org.eclipse.swt.internal.C;
import util.PlaceHoldersUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Map;

import static util.PlaceHoldersUtil.*;

public class CardPane extends JPanel {

    public double zoom = 1d;
    public  Point offset = new Point(0, 0);
    private Card card ;

    //private Point uperleftTmpPlaceHolder;
    //private Point currentLocation;

    private Point pointClicked;

    public Point getTmpPoint() {
        return tmpPoint;
    }

    public void setTmpPoint(Point tmpPoint) {
        this.tmpPoint = tmpPoint;
    }

    private Point tmpPoint;

    public void setPointClicked(Point pointClicked) {
        this.pointClicked = pointClicked;
    }

    public Point getPointClicked() {
        return pointClicked;
    }

    public Point getBackgroundImagePosition() {
        return backgroundImagePosition;
    }

    private Point backgroundImagePosition = new Point(0,0);


    private Image backgroundImage;

    public CardPane(Card card) {
        this.card = card;
        try {
            backgroundImage = ImageIO.read(getClass().getClassLoader().getResourceAsStream("TemplateALarrache.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setPreferredSize(new Dimension(PlaceHoldersUtil.WIDTH,PlaceHoldersUtil.HEIGHT));

        //MouseAdapter mickey = getMouseAdapter();
        /*ZoomDragListener mickey = new ZoomDragListener(this);
        addMouseWheelListener(mickey);
        addMouseListener(mickey);
        addMouseMotionListener(mickey);*/


        /*addMouseMotionListener(new MouseMotionListener() {
           @Override
           public void mouseDragged(MouseEvent e) {
               backgroundImagePosition.x = backgroundImagePosition.x + (e.getPoint().x-pointClicked.x);
               backgroundImagePosition.y = backgroundImagePosition.y + (e.getPoint().y-pointClicked.y);
               pointClicked = e.getPoint();
               repaint();
           }

           @Override
           public void mouseMoved(MouseEvent e) {

           }
        });

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                pointClicked = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        this.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                zoom += e.getUnitsToScroll()*0.01;
                System.out.println(zoom);
                repaint();
            }
        });*/

    }
    /*public MouseAdapter getMouseAdapter(){
        return new MouseAdapter() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (e.getPreciseWheelRotation() > 0&&zoom>=0.1) zoom -= 0.05;
                else if(e.getPreciseWheelRotation() < 0&&zoom>=0.1) zoom += 0.05;
                else if(e.getPreciseWheelRotation() > 0&&zoom<=0.1) zoom -= 0.01;
                else if(e.getPreciseWheelRotation() < 0&&zoom<=0.1) zoom += 0.01;
                repaint();
            }
            private Point startPoint;
            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint();
                startPoint.x -= offset.x;
                startPoint.y -= offset.y;
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                startPoint = null;
            }
            @Override
            public void mouseDragged(MouseEvent e) {
                Point p = e.getPoint();
                int x = p.x - startPoint.x;
                int y = p.y - startPoint.x;
                offset = new Point(x, y);
                repaint();
            }
        };
    }*/

   @Override
    public void paint(Graphics g) {

        g.setColor(Color.black);
        g.clearRect(0,0,this.getWidth(),this.getHeight());

       //((Graphics2D)g).setTransform(AffineTransform.getScaleInstance(zoom,zoom));
       //((Graphics2D)g).setTransform(new AffineTransform());

       if (offset == null) {
            offset = new Point(0, 0);
        }
        //applyZoom((Graphics2D) g);
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

            //System.out.println("uperleftTmpPlaceHolder : " + uperleftTmpPlaceHolder);
            g.drawRect(minX, minY, maxX-minX, maxY-minY);
        }

       /*g.setColor(Color.black);
       System.out.println("uperleftTmpPlaceHolder : " + uperleftTmpPlaceHolder);
       System.out.println("currentLocation : " + currentLocation);

       if (uperleftTmpPlaceHolder!=null && currentLocation!=null) {
           System.out.println("uperleftTmpPlaceHolder.x : " + uperleftTmpPlaceHolder.x);
           System.out.println("uperleftTmpPlaceHolder.y : " + uperleftTmpPlaceHolder.y);
           System.out.println("currentLocation.x-uperleftTmpPlaceHolder.x : " + (currentLocation.x-uperleftTmpPlaceHolder.x));
           System.out.println("uperleftTmpPlaceHolder.y-uperleftTmpPlaceHolder.x : " + (uperleftTmpPlaceHolder.y-uperleftTmpPlaceHolder.x));

           g.drawRect(uperleftTmpPlaceHolder.x,
                   uperleftTmpPlaceHolder.y,
                   currentLocation.x-uperleftTmpPlaceHolder.x,
                   uperleftTmpPlaceHolder.y-uperleftTmpPlaceHolder.x);

       }*/

   }


    private void drawBackground(Graphics g) {
        //g.drawImage(backgroundImage, offset.x, offset.y,PlaceHoldersUtil.WIDTH,PlaceHoldersUtil.HEIGHT, this);
        System.out.println("backgroundImagePosition.x : " + backgroundImagePosition.x);
        System.out.println("backgroundImagePosition.y : " + backgroundImagePosition.y);
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
            g.drawString(map.get(p),p.x+offset.x,p.y+offset.y);
        }
    }

    public void applyZoom(Graphics2D g) {
        AffineTransform at = new AffineTransform();
        at.scale(zoom, zoom);
        g.setTransform(at);
    }


}
