package back;

import util.CsvUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardDTO {

    private List<List<String>> cards ;

    public CardDTO () {
        cards = new ArrayList<>();
    }

    public List<List<String>> getCards() {
        return cards;
    }

    public void addCardFromLine (String line) {
        cards.add(Arrays.asList(line.split(CsvUtil.DELIMITER)));
    }
}
