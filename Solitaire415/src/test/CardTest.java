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
			assertEquals("step"+i, i+1, Card.indexOf(cards[i].getShortName()));
			assertEquals("step"+i, i+1, Card.indexOf(cards[i].getLongName()));
		}
	}

	public void testNext() {
		assertEquals(Card.isNext(cards[0], cards[1]), true);
		assertEquals(Card.isNext(cards[0], cards[13+1]), true);
		assertEquals(Card.isNext(cards[0], cards[13*3+1]), true);
		assertEquals(Card.isNext(cards[12], cards[13]), false);
	}
	
	
	public void testSameColour() {
		assertEquals(Card.isSameColour(cards[0], cards[1]), true);
		assertEquals(Card.isSameColour(cards[0], cards[5]), true);
		assertEquals(Card.isSameColour(cards[0], cards[17]), true);
		assertEquals(Card.isSameColour(cards[0], cards[26]), false);
		
	}
}
