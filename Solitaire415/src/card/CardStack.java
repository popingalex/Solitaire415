package card;

import java.util.Stack;

/**
 * 
 * @author [sign your own name]
 *
 */
public class CardStack {
	/**
	 * A stack to store the cards.
	 */
	private Stack<Card> stack;

	public Stack<Card> getStack() {
		return stack;
	}

	public CardStack() {
		stack = new Stack<Card>();
	}
	public void init() {
		stack.clear();
	}
	/**
	 * Adds c to the top of the stack, if the rules allow this.
	 * @return
	 */
	public void add(Card c) {
		if((stack.size()==0 && c.getValue()==EValue.King) ||
				stack.lastElement().getValue().compareTo(c.getValue())==1 && 
				stack.lastElement().getSuit().compareTo(c.getSuit())==0) {
			this.stack.add(c);
		}
	}
}
