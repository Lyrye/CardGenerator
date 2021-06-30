/*package middle;

import back.CardDAO;

import java.util.List;

public class CardManager {

    private Cards cards;

    public CardManager ()
    {
        cards = new Cards();
    }

    public void load()
    {
        CardDAO cardDAO = new CardDAO();
        cardDAO.load("CardGenerator\\src\\main\\resources\\Cartes.csv");
        for (List<String> line: cardDAO.getCards()) {
            Card card = new Card(line.get(0), line.get(1), line.get(2), line.get(3), line.get(4));
            cards.addCard(card);
        }
    }

    public Cards getCards()
    {
        return cards;
    }


}*/
