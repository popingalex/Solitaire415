package test;

import junit.framework.TestCase;
import card.Card;

public class CardTest extends TestCase {

    static Card[] cards;
    @Override
    protected void setUp() throws Exception {
        cards = new Card[52];
        for(int i=0;i<52;i++) {
            cards[i] = new Card(i+1);
        }
    }

    public void testIndex() {
        for(int i=0;i<52;i++) {
            cards[i] = new Card(i+1);
            assertEquals("step"+i, i, Card.indexOf(cards[i].getShortName()));
            assertEquals("step"+i, i, Card.indexOf(cards[i].getLongName()));
        }
    }
}
