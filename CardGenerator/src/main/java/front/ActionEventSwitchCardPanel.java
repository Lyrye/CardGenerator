package front;

import util.FileUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionEventSwitchCardPanel implements ActionListener {

    private MainPane mainPane;

    public ActionEventSwitchCardPanel(MainPane mainPane){
        this.mainPane = mainPane;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case(CommandPane.PREVIOUS):
                mainPane.changeToPreviousCardPane();
                break;
            case (CommandPane.NEXT):
                mainPane.changeToNextCardPane();
                break;
            case (CommandPane.SAVE):
                FileUtil.SaveImage(mainPane.getCardPane(),"png","./card1.png");
                break;
            case (CommandPane.SAVE_ALL):
                for (int i=0;i<mainPane.getCards().size();i++)
                {
                    FileUtil.SaveImage(mainPane.getCardPane(),"png","./card"+i+".png");
                    mainPane.changeToNextCardPane();
                }
                break;
        }
    }
}
