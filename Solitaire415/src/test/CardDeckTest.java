package test;

import java.util.Arrays;
import java.util.LinkedList;

import junit.framework.TestCase;
import card.Card;
import card.CardDeck;

public class CardDeckTest extends TestCase{
    
    static Card[] cards;
    @Override
    protected void setUp() throws Exception {
        cards = new Card[52];
        for(int i=0;i<52;i++) {
            cards[i] = new Card(i+1);
        }
    }
    
    public void testInit() {
        CardDeck cardDeck = new CardDeck();
        cardDeck.init(new LinkedList<Card>(Arrays.asList(new Card[]{
                cards[1],
                cards[3],
                cards[5],
                cards[7],
                })));
        assertEquals("step1", 4, cardDeck.countCards());
        assertNull("step2", cardDeck.getCurrentCard());
        //==========
    }
    public void testDrawCard() {
        CardDeck cardDeck = new CardDeck();
        cardDeck.init(new LinkedList<Card>(Arrays.asList(new Card[]{
                cards[1],
                cards[3],
                cards[5],
                cards[7],
                })));
        assertNull("step1", cardDeck.getCurrentCard());
        cardDeck.drawCard();
        assertEquals("step2", cards[7], cardDeck.getCurrentCard());
        cardDeck.drawCard();
        assertEquals("step3", cards[5], cardDeck.getCurrentCard());
        cardDeck.drawCard();
        assertEquals("step4", cards[3], cardDeck.getCurrentCard());
        cardDeck.drawCard();
        assertEquals("step5", cards[1], cardDeck.getCurrentCard());
        cardDeck.drawCard();
        assertNull("step6", cardDeck.getCurrentCard());
        cardDeck.drawCard();
        assertEquals("step7", cards[7], cardDeck.getCurrentCard());
        cardDeck.drawCard();
        assertEquals("step8", cards[5], cardDeck.getCurrentCard());
        cardDeck.drawCard();
        assertEquals("step9", cards[3], cardDeck.getCurrentCard());
        cardDeck.drawCard();
        assertEquals("step10", cards[1], cardDeck.getCurrentCard());
    }
    public void testTakeCard() {
        CardDeck cardDeck = new CardDeck();
        cardDeck.init(new LinkedList<Card>(Arrays.asList(new Card[]{
                cards[1],
                cards[3],
                cards[5],
                cards[7],
                })));
        assertNull("step1", cardDeck.takeCard());
        cardDeck.drawCard();
        assertEquals("step2", cards[7], cardDeck.takeCard());
        assertEquals("step3", cards[5], cardDeck.takeCard());
        assertEquals("step4", cards[3], cardDeck.takeCard());
        assertEquals("step5", cards[1], cardDeck.takeCard());
        assertNull("step6", cardDeck.takeCard());
    }
}
