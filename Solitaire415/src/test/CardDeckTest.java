package test;

import java.util.Arrays;
import java.util.LinkedList;

import card.Card;
import card.CardDeck;

public class CardDeckTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Card[] cardArray = new Card[]{new Card(1)};
        LinkedList<Card> cards = new LinkedList<Card>(Arrays.asList(cardArray));
        CardDeck cd = new CardDeck();
        cd.init(cards);
    }

}
