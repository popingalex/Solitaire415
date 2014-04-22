package solitaire;

import java.util.Collections;
import java.util.LinkedList;

import card.Card;

public class Shuffler {
    
    public static void shuffle(LinkedList<Card> cardLink) {
        shuffle(cardLink, ShuffleStrategy.Java_Shuffle);
    }
    public static void shuffle(LinkedList<Card> cardLink, ShuffleStrategy strategy) {
        switch (strategy) {
        case Fisher_Yates:
            break;
        case Java_Shuffle:
            Collections.shuffle(cardLink);
            break;
        }
    }
}
