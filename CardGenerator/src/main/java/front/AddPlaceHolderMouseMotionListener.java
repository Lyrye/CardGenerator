package front;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class AddPlaceHolderMouseMotionListener implements MouseMotionListener {

    private CardPane cardPane;
    public AddPlaceHolderMouseMotionListener(CardPane cardPane)
    {
        this.cardPane = cardPane;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        cardPane.setTmpPoint(e.getPoint());
        cardPane.setTmpPoint2(e.getPoint());
        cardPane.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
