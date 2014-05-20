package solitaire;

public enum ECommand {
    Draw,
    Deck,
    Link,
    Send,
    Restart,
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
        case Deck:
        case Send:
            return 1;
        case Link:
            return 2;
        case Draw:
        case Restart:
        case Quit:
            return 0;
        }
        return 0;
    }
}
