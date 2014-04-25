package solitaire;

public enum ECommand {
    DrawCard,
    Restart,
    DeckTo,
    Link,
    Send,
    Quit;

    public static ECommand valueOfIgnoreCase(String commandString) {
        for(ECommand eCommand : ECommand.values()) {
            if(eCommand.toString().equalsIgnoreCase(commandString))
                return eCommand;
        }
        return null;
    }

    public int countParams() {
        switch (this) {
        case DeckTo:
        case Send:
            return 1;
        case Link:
            return 2;
        case DrawCard:
        case Restart:
        case Quit:
            return 0;
        }
        return 0;
    }

    @Override
    public String toString() {
        switch (this) {
        case DeckTo:
            return "Deck";
        case DrawCard:
            return "Draw";
        default:
            return super.toString();
        }
    }
}
