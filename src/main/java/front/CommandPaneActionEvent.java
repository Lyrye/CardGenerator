package front;

import util.FileUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CommandPaneActionEvent implements ActionListener {

    private MainPane mainPane;

    public CommandPaneActionEvent(MainPane mainPane){
        this.mainPane = mainPane;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case(CommandPane.PREVIOUS):
                mainPane.changeToPreviousCard();
                break;
            case (CommandPane.NEXT):
                mainPane.changeToNextCard();
                break;
            case (CommandPane.SAVE):
                FileUtil.SaveImage(mainPane.getCardPane(),"png","./card"+mainPane.getIndex()+".png");
                break;
            case (CommandPane.SAVE_ALL):
                for (int i=0;i<mainPane.getCards().size();i++)
                {
                    FileUtil.SaveImage(mainPane.getCardPane(),"png","./card"+i+".png");
                    mainPane.changeToNextCard();
                }
                break;
        }
    }
}
