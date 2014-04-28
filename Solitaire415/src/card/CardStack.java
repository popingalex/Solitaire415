package card;

import java.util.Stack;

import solitaire.EResult;

/**
 * @author Alex
 */
public class CardStack {
	/**
	 * A stack to store the cards.
	 */
	private Stack<Card> stack;
	/**
	 * get cards
	 * @return all cards
	 */
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
	public EResult add(Card c) {
		if((stack.size()==0 && c.getValue()==EValue.Ace) ||
		        stack.size()>0 &&
				stack.lastElement().getValue().compareTo(c.getValue())==-1 && 
				stack.lastElement().getSuit().compareTo(c.getSuit())==0) {
			this.stack.add(c);
			return EResult.Welldone;
		}
		return EResult.Impossible;
	}
}
