package back;

import util.CsvUtil;

import java.util.List;

public class CardDAO {

    private CardDTO cardDTO;

    public CardDAO ()
    {
        cardDTO = new CardDTO();
    }

    public void load (String path)
    {
       List<String> cards = CsvUtil.FromCsvToList(path);
        for (String line: cards ){
            cardDTO.addCardFromLine(line);
        }
    }

    public List<List<String>> getCards()
    {
        return cardDTO.getCards();
    }
}
