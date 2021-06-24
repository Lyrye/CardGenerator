package front;

import middle.Card;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

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

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
