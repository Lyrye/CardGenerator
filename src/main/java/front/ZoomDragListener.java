package front;

import java.awt.*;
import java.awt.event.*;

public class ZoomDragListener implements MouseListener, MouseMotionListener, MouseWheelListener {

    private CardPane carpPane;
    public ZoomDragListener(CardPane carpPane) {
        this.carpPane = carpPane;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getPreciseWheelRotation() > 0&&carpPane.zoom>=0.1) carpPane.zoom -= 0.05;
        else if(e.getPreciseWheelRotation() < 0&&carpPane.zoom>=0.1) carpPane.zoom += 0.05;
        else if(e.getPreciseWheelRotation() > 0&&carpPane.zoom<=0.1) carpPane.zoom -= 0.01;
        else if(e.getPreciseWheelRotation() < 0&&carpPane.zoom<=0.1) carpPane.zoom += 0.01;
        carpPane.repaint();
    }
    private Point startPoint;
    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = e.getPoint();
        startPoint.x -= carpPane.getOffset().x;
        startPoint.y -= carpPane.getOffset().y;
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
        carpPane.setOffset(new Point(x, y));
        carpPane.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
