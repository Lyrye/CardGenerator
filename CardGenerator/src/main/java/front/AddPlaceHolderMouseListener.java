package front;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AddPlaceHolderMouseListener implements MouseListener {

    private CardPane cardPane;

    public AddPlaceHolderMouseListener(CardPane cardPane)
    {
        this.cardPane = cardPane;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

        cardPane.setPointClicked(e.getPoint());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        cardPane.setPointReleased(e.getPoint());
        if(cardPane.getPointClicked()!=null){
            PlaceHolder placeHolder = new PlaceHolder();
            placeHolder.setText("? ?????");
            placeHolder.setFont(cardPane.getTmpFontPlaceHolder());
            placeHolder.setDownRightCorner(cardPane.getPointReleased());
            placeHolder.setUpLeftCorner(cardPane.getPointClicked());
            placeHolder.setType(cardPane.getTmpTypePlaceHolder());
            cardPane.addNewPlaceHolder(placeHolder);
            cardPane.repaint();
            cardPane.setPointReleased(null);
            cardPane.setPointClicked(null);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
