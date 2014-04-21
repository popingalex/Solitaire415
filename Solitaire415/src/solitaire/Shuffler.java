package solitaire;

import java.util.LinkedList;

import card.Card;

public class Shuffler {
    
    public static void shuffle(LinkedList<Card> cardLink) {
        shuffle(cardLink, ShuffleStrategy.Fisher_Yates);
    }
    public static void shuffle(LinkedList<Card> cardLink, ShuffleStrategy strategy) {
        // TODO Auto-generated method stub
        
    }
}
