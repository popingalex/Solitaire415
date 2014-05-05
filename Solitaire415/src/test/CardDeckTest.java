package test;

import java.util.Arrays;
import java.util.LinkedList;

import junit.framework.TestCase;
import card.Card;
import card.CardDeck;

public class CardDeckTest extends TestCase{
    
    Card[] cards;
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
        assertEquals(4, cardDeck.countBacks());
        assertEquals(0, cardDeck.countFaces());
        assertNull(cardDeck.getCurrentCard());
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
        
        assertNull(cardDeck.getCurrentCard());
        assertEquals(4, cardDeck.countBacks());
        assertEquals(0, cardDeck.countFaces());
        
        cardDeck.drawCard();
        assertEquals(cards[7], cardDeck.getCurrentCard());
        assertEquals(3, cardDeck.countBacks());
        assertEquals(1, cardDeck.countFaces());

        cardDeck.drawCard();
        assertEquals(cards[5], cardDeck.getCurrentCard());
        assertEquals(2, cardDeck.countBacks());
        assertEquals(2, cardDeck.countFaces());
        
        cardDeck.drawCard();
        assertEquals(cards[3], cardDeck.getCurrentCard());
        assertEquals(1, cardDeck.countBacks());
        assertEquals(3, cardDeck.countFaces());
        
        cardDeck.drawCard();
        assertEquals(cards[1], cardDeck.getCurrentCard());
        assertEquals(0, cardDeck.countBacks());
        assertEquals(4, cardDeck.countFaces());
        
        cardDeck.drawCard();
        assertNull(cardDeck.getCurrentCard());
        assertEquals(4, cardDeck.countBacks());
        assertEquals(0, cardDeck.countFaces());
        
        cardDeck.drawCard();
        assertEquals(cards[7], cardDeck.getCurrentCard());
        assertEquals(3, cardDeck.countBacks());
        assertEquals(1, cardDeck.countFaces());

        cardDeck.drawCard();
        assertEquals(cards[5], cardDeck.getCurrentCard());
        assertEquals(2, cardDeck.countBacks());
        assertEquals(2, cardDeck.countFaces());
        
        cardDeck.drawCard();
        assertEquals(cards[3], cardDeck.getCurrentCard());
        assertEquals(1, cardDeck.countBacks());
        assertEquals(3, cardDeck.countFaces());
        
        cardDeck.drawCard();
        assertEquals(cards[1], cardDeck.getCurrentCard());
        assertEquals(0, cardDeck.countBacks());
        assertEquals(4, cardDeck.countFaces());
    }
    public void testTakeCard() {
        CardDeck cardDeck = new CardDeck();
        cardDeck.init(new LinkedList<Card>(Arrays.asList(new Card[]{
                cards[1],
                cards[3],
                cards[5],
                cards[7],
                })));
        assertNull(cardDeck.takeCard());
        cardDeck.drawCard();
        assertEquals(cards[7], cardDeck.takeCard());
        cardDeck.drawCard();
        assertEquals(cards[5], cardDeck.takeCard());
        cardDeck.drawCard();
        assertEquals(cards[3], cardDeck.takeCard());
        cardDeck.drawCard();
        assertEquals(cards[1], cardDeck.takeCard());
        assertNull(cardDeck.takeCard());
        cardDeck.drawCard();
        assertNull(cardDeck.takeCard());
    }
}
