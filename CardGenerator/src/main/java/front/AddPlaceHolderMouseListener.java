package front;

import util.PlaceHoldersUtil;

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
        cardPane.setPointClicked(null);
        cardPane.setTmpPoint(null);
        if(cardPane.getTmpPoint()!=null&&cardPane.getPointClicked()!=null){
            PlaceHolder placeHolder = new PlaceHolder();
            placeHolder.setText("??????");
            placeHolder.setFont(cardPane.tmpFontPlaceHolder);
            placeHolder.setDownRightCorner(cardPane.getTmpPoint());
            placeHolder.setUpLeftCorner(cardPane.getPointClicked());
            placeHolder.setType(PlaceHoldersUtil.placeHolderType.ACTION_NAME);
            cardPane.addNewPlaceHolder(placeHolder);
            cardPane.repaint();
            cardPane.TEST();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
