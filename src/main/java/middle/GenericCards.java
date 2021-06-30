package middle;

import java.util.ArrayList;
import java.util.List;

public class GenericCards {

    private List<GenericCard> GenericCards;

    public GenericCards () {
        GenericCards = new ArrayList<>();
    }

    public void addCard(GenericCard card) {
        GenericCards.add(card);
    }
    public List<GenericCard> getCards() {
        return GenericCards;
    }

    public void print()
    {
        for (GenericCard card:GenericCards) {
            card.print();

        }
    }
}
