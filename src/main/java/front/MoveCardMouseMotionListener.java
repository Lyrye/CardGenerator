package front;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MoveCardMouseMotionListener implements MouseMotionListener {

    private CardPane cardPane;

    public MoveCardMouseMotionListener(CardPane cardPane) {

        this.cardPane =cardPane;

    }

    @Override
    public void mouseDragged(MouseEvent e) {

        cardPane.getOffset().x = cardPane.getOffset().x + (e.getPoint().x-cardPane.getPointClicked().x);
        cardPane.getOffset().y = cardPane.getOffset().y + (e.getPoint().y-cardPane.getPointClicked().y);

        cardPane.setPointClicked(e.getPoint());
        cardPane.repaint();

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
