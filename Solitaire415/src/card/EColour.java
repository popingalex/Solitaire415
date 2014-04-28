package card;

/**
 * @author Alex
 */

public enum EColour {
    Red,
    Black;

    public static EColour getColour(ESuit suit) {
        switch (suit) {
        case Heart:
        case Diamond:
            return EColour.Red;
        case Spade:
        case Club:
            return EColour.Black;
        default:
            return null;
        }
    }
}
