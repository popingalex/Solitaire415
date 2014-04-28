package card;

/**
 * @author Alex
 */
public enum EValue {
    Ace,
    Two,
    Three,
    Four,
    Five,
    Six,
    Seven,
    Eight,
    Nine,
    Ten,
    Jack,
    Queen,
    King;

    public static EValue getValue(int cardIndex) {
        switch ((cardIndex-1)%13) {
        case 0:
            return Ace;
        case 1:
            return Two;
        case 2:
            return Three;
        case 3:
            return Four;
        case 4:
            return Five;
        case 5:
            return Six;
        case 6:
            return Seven;
        case 7:
            return Eight;
        case 8:
            return Nine;
        case 9:
            return Ten;
        case 10:
            return Jack;
        case 11:
            return Queen;
        case 12:
            return King;
        default:
            return null;
        }
    }

    @Override
    public String toString() {
        switch (this) {
        case Two:
            return "2";
        case Three:
            return "3";
        case Four:
            return "4";
        case Five:
            return "5";
        case Six:
            return "6";
        case Seven:
            return "7";
        case Eight:
            return "8";
        case Nine:
            return "9";
        case Ten:
            return "10";
        default:
            return super.toString().substring(0, 1);
        }
    }

    public String toName() {
        return super.toString();
    }
}
