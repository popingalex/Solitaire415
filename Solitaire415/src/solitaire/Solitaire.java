package solitaire;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import ui.PaintingPanel;
import ui.PrintConsole;
import card.Card;
import card.CardDeck;
import card.CardList;
import card.CardStack;

/**
 * This is the main class of the game.
 * @author [sign your own name]
 */
public class Solitaire implements ICommandListener {
    private Card[] cards;
    /**
     * A CardDeck for the current game's deck.
     */
    private CardDeck deck;
    /**
     * An array of CardStacks (of length 4).
     */
    private CardStack[] stacks;
    /**
     * An array of CardLists (of length 7).
     */
    private CardList[] lists;
    /**
     * Should create a new Solitaire, then call showGUI,
     * passing the newly-created Solitaire object to it.
     * Lastly, should call the Solitaire object's
     * startGame method.
     * @param args
     */
    private GameState gameState;

    private PaintingPanel gui;
    private PrintConsole cui;
    public static void main(String[] args) {
        Solitaire solitaire = new Solitaire();
        Solitaire.showGUI(solitaire);
        solitaire.startGame();
    }

    public Solitaire() {
        deck = new CardDeck();
        stacks = new CardStack[4];
        for(int i=0;i<4;i++) {
            stacks[i] = new CardStack();
        }
        lists = new CardList[7];
        for(int i=0;i<7;i++) {
            lists[i] = new CardList();
        }
        cards = new Card[52];
        for(int i=0;i<52;i++) {
            cards[i] = new Card(i+1);
        }
    }

    /**
     * Should create a GUI and display the game.
     * @param game
     */
    public static void showGUI(Solitaire game) {
        PaintingPanel gamePanel = new PaintingPanel();
        gamePanel.addCommandListener(game);
        game.linkGUI(gamePanel);
    }
    private void linkGUI(PaintingPanel gamePanel) {
        this.gui = gamePanel;
    }

    /**
     * Perform whatever command indicates if
     * the rules allow it and return a success message.
     * If the command is invalid,
     * return a warning instead.
     * @param command
     */
    public EResult executeCommand(String commandString) {
        String paramString = null;
        if(commandString.indexOf(' ')>0) {
            paramString = commandString.substring(commandString.indexOf(' ')+1);
            commandString = commandString.substring(0, commandString.indexOf(' '));
        }

        ECommand command = ECommand.valueOfIgnoreCase(commandString);
        if(command==null) {
            return EResult.Unresolvable;
        }
        switch (command) {
        case DrawCard:
            deck.drawCard();
            break;
        case Restart:
            gameState = GameState.Prepare;
            break;
        case DeckTo:
        {
            if(paramString==null)
                return EResult.IllegalCommand;
            int listIndex = Integer.getInteger(paramString, 0);
            if(listIndex<1||listIndex>7)
                return EResult.IllegalCommand;
            
            return deckTo(listIndex);
        }
        case Send:
        {
            if(paramString==null)
                return EResult.IllegalCommand;
            Card card = Card.parse(paramString);
            if(card==null)
                return EResult.IllegalCommand;

            return send(card);
        }
        case Link:
        {
            if(paramString==null)
                return EResult.IllegalCommand;
            int index = paramString.indexOf(' ');
            
            if(index<0)
                return EResult.IllegalCommand;
            Card card = Card.parse(paramString.substring(0, index));
            int listIndex = Integer.getInteger(paramString.substring(index+1));
            if(card==null || listIndex <1 || listIndex > 7)
                return EResult.IllegalCommand;
            
            return link(card, listIndex);
        }
        case Quit:
            gameState = GameState.Closing;
            break;
        }
        return EResult.Welldone;
    }
    private EResult link(Card card, int listIndex) {
        CardList cut = null;
        for(int i=0;i<7;i++) {
            int cardIndex = lists[i].indexOf(card);
            if(cardIndex<0)
                continue;
            if(cardIndex >= lists[i].getOpenedIndex() && 
                    lists[listIndex].getTailCard().getValue().compareTo(card.getValue())==1 &&
                    lists[listIndex].getTailCard().getColour().compareTo(card.getColour())!=0) {
                
                cut = lists[i].cut(cardIndex);
                break;
            }
            return EResult.Impossible;
        }
        if(cut==null)
            return EResult.Impossible;
        return EResult.Welldone;
    }

    private EResult send(Card card) {
        int index = card.getSuit().ordinal();
        CardStack stack = stacks[index];
        if(stack.add(card)==EResult.Impossible)
            return EResult.Impossible;
        return EResult.Welldone;
    }

    private EResult deckTo(int listIndex) {
        Card deckCard = deck.getCurrentCard();
        if(deckCard==null)
            return EResult.Impossible;
        if(lists[3].add(deckCard) == EResult.Impossible)
            return EResult.Impossible;
        deck.takeCard();
        return EResult.Welldone;
    }

    /**
     * Runs a loop that accepts commands
     * until either a quit command is given or the player wins.
     * Should attempt to perform any commands given,
     * and prints all messages back to the user.
     */
    public void startGame() {
        gameState = GameState.Prepare;
        while(gameState != GameState.Closed) {
            switch (gameState) {
            case Prepare://prepare for a new game
                prepare();
                break;
            case Looping://loop a game
                loop();
                break;
            case Closing://closing the game
                close();
                break;
            case Closed://here's a No Man Land
                break;
            }
        }
    }
    private void close() {
        gameState = GameState.Closed;
    }

    private void loop() {
        //run the clock ?
        //        System.out.println("yo");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        executeCommand(command);
    }

    private void prepare() {
        LinkedList<Card> cardLink = new LinkedList<Card>();
        cardLink.addAll(Arrays.asList(cards));
        Shuffler.shuffle(cardLink);

        int index = 0;

        for(int i=0;i<4;i++) {
            stacks[i].init();
        }
        for(int i=0;i<7;i++) {
            List<Card> subList = cardLink.subList(index, index+i+1);
            lists[i].init(subList, i);
            subList.clear();
            index+=i+1;
        }
        deck.init(cardLink);

        gameState = GameState.Looping;
    }

    @Override
    public synchronized EResult handleCommand(String command) {
        // TODO Auto-generated method stub
        if(gameState!=GameState.Looping)
            return EResult.Refused;
        return executeCommand(command);
    }
}
