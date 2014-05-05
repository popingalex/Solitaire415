package test;

import card.Card;
import card.CardStack;
import junit.framework.TestCase;

public class CardStackTest extends TestCase {
	static Card[] cards;
	@Override
	protected void setUp() throws Exception {
		cards = new Card[52];
		for(int i=0;i<52;i++) {
			cards[i] = new Card(i+1);
		};
	}
	public void testAdd() {
		CardStack cardStack = new CardStack();
		
		cardStack.add(cards[1]);
        assertEquals(0, cardStack.countFaces());

		cardStack.add(cards[0]);
		assertEquals(cards[0], cardStack.getCard(cardStack.countFaces()-1));
		
		cardStack.add(cards[1]);
        assertEquals(cards[1], cardStack.getCard(cardStack.countFaces()-1));

        cardStack.add(cards[2+13]);
        assertEquals(cards[1], cardStack.getCard(cardStack.countFaces()-1));

        cardStack.add(cards[3]);
        assertEquals(cards[1], cardStack.getCard(cardStack.countFaces()-1));

        cardStack.add(cards[2]);
        assertEquals(cards[2], cardStack.getCard(cardStack.countFaces()-1));
	}
}
