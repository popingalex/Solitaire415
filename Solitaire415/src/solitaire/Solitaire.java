package solitaire;

import java.util.Arrays;
import java.util.LinkedList;

import ui.PaintingPanel;
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

    private String command;
    private PaintingPanel gui;
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
    public void executeCommand(String command) {
        // TODO Auto-generated method stub

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
                LinkedList<Card> cardLink = new LinkedList<Card>();
                cardLink.addAll(Arrays.asList(cards));
                Shuffler.shuffle(cardLink);
                deck.init(cardLink);
                break;
            case Looping://loop a game
                //deal with command
                if(command != null) {
                    executeCommand(command);
                    command = null;
                }
                break;
            case Closing://closing the game
                
                break;
            case Closed://here's a No Man Land
                break;
            }
        }
    }
    @Override
    public void handleCommand(String command) {
        // TODO Auto-generated method stub
        while(command!=null);
        //judge
        this.command = command;
    }
}
