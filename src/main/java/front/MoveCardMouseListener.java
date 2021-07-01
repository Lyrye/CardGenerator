package front;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MoveCardMouseListener implements MouseListener {

    private CardPane cardPane;
    public MoveCardMouseListener(CardPane cardPane)
    {
        this.cardPane = cardPane;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        cardPane.setPointClicked (e.getPoint());
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        cardPane.setPointClicked(null);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
