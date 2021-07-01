package middle;

import back.CardDAO;

import java.util.ArrayList;
import java.util.List;

public class GenericCardManager {

    private List<GenericCard> cards = new ArrayList<>();
    private CardDAO cardDAO;
    private String path;

    public GenericCardManager(String path)
    {
        cardDAO = new CardDAO();
        this.path=path;
    }

    public void load()
    {
        cardDAO.load(path);
        for (List<String> line: cardDAO.getCards()) {
            GenericCard card = new GenericCard();
            for (int i =0; i<getTypeCollumn().size();i++)
            {
                card.addData(getTypeCollumn().get(i),line.size()>=i-1?line.get(i):"***");
            }
            cards.add(card);
        }
    }

    public List<GenericCard> getCards()
    {
        return cards;
    }

    public List <String> getTypeCollumn()
    {
        return cardDAO.getCards().get(0) ;

    }


}
