package card;

/**
 * @author Alex
 */

public enum ESuit {
    Heart,
    Diamond,
    Spade,
    Club;

    public static ESuit getSuit(int cardIndex) {
        switch ((cardIndex-1)/13) {
        case 0:
            return ESuit.Heart;
        case 1:
            return ESuit.Diamond;
        case 2:
            return ESuit.Spade;
        case 3:
            return ESuit.Club;
        default:
            return null;
        }
    }
}
