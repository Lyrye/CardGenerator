package middle;

import back.CardDAO;

import java.util.List;

public class GenericCardManager {

    private GenericCards cards;
    private CardDAO cardDAO;

    public GenericCardManager()
    {
        cards = new GenericCards();
        cardDAO = new CardDAO();
        cardDAO.load("CardGenerator\\src\\main\\resources\\Cartes.csv");
    }

    public void load()
    {
        for (List<String> line: cardDAO.getCards()) {
            GenericCard card = new GenericCard();
            for (int i =0; i<getTypeCollumn().size();i++)
            {
                card.addData(getTypeCollumn().get(i),line.get(i));
            }
            cards.addCard(card);
        }
    }

    public GenericCards getCards()
    {
        return cards;
    }

    public List <String> getTypeCollumn()
    {
        return cardDAO.getCards().get(0) ;

    }


}
