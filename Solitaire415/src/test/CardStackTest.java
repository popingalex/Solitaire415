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
		cardStack.add(cards[12]);
		assertEquals("step1", cards[12], cardStack.getStack().lastElement());
		cardStack.add(cards[11]);
		assertEquals("step1", cards[11], cardStack.getStack().lastElement());
		cardStack.add(cards[8+13]);
		assertEquals("step1", cards[11], cardStack.getStack().lastElement());
		cardStack.add(cards[8]);
		assertEquals("step1", cards[11], cardStack.getStack().lastElement());
		cardStack.add(cards[10]);
		assertEquals("step1", cards[10], cardStack.getStack().lastElement());
        
	}
}
