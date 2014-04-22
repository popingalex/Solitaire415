package card;

import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * 
 * @author Alex
 *
 */
public class Card {
    public final static String CARD = "Card";
    /**
     * The card's index, from 1 to 52.
     */
    private int cardIndex;
    
    public Card(int cardIndex) {
        this.cardIndex = cardIndex;
    }
    /**
     * Returns the card's suit.
     * @return
     */
    public ESuit getSuit() {
        switch ((cardIndex-1)/13+1) {
        case 1:
            return ESuit.Heart;
        case 2:
            return ESuit.Diomand;
        case 3:
            return ESuit.Spade;
        case 4:
            return ESuit.Club;
        default:
            return null;
        }
    }
    /**
     * Returns the card's value (such as 10, king, etc).
     * @return
     */
    public EValue getValue() {
        return EValue.valueOf((cardIndex-1)%13+1);
    }
    /**
     * The colour of the card is 'red' if this card is a heart or diamond,
     * and 'black' otherwise.
     * @return
     */
    public EColour getColour() {
        switch (getSuit()) {
        case Heart:
        case Diomand:
            return EColour.Red;
        case Spade:
        case Club:
            return EColour.Black;
        default:
            return null;
        }
    }
    /**
     * Returns a string representation of this card,
     * including its suit and rank.
     * Example: Ace of clubs would be ClubA,
     * ten of diamonds would be Diamond10,
     * and queen of spades
     * would be SpadeQ.
     */
    @Override
    public String toString() {
        return getSuit().toString()+getValue().toString();
    }
    /**
     * Draws the card.
     * In the simple GUI,
     * this should draw a Rectangle,
     * with the string representation of the card
     * written in the colour corre-sponding to
     * the colour of the card (either black or red).
     * (You don't have to make it pretty)
     * @param g
     */
    public void paintThis(Graphics g) {
        // TODO Auto-generated method stub
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawString(this.toString(), 20, 20);
    }
    public static Card parse(String cardString) {
        // TODO Auto-generated method stub
        return null;
    }
}
