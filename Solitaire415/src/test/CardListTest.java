package test;

import java.util.Arrays;
import java.util.LinkedList;

import junit.framework.TestCase;
import card.Card;
import card.CardList;

public class CardListTest extends TestCase {

    static Card[] cards;
    @Override
    protected void setUp() throws Exception {
        cards = new Card[52];
        for(int i=0;i<52;i++) {
            cards[i] = new Card(i+1);
        }
    }
    
    public void testInit() {
        CardList cardList = new CardList();
        cardList.init(new LinkedList<Card>(Arrays.asList(new Card[]{
                cards[1],
                cards[3],
                cards[5],
                cards[7],
                })), 2);
        assertEquals("step1", 4, cardList.getCards().size());
        assertEquals("step2", cards[7], cardList.getTailCard());
        assertEquals("step3", 2, cardList.getOpenedIndex());
    }
    public void testCut1() {
        CardList cardList = new CardList();
        cardList.init(new LinkedList<Card>(Arrays.asList(new Card[]{
                cards[1],
                cards[3],
                cards[5],
                cards[7],
                cards[2],
                cards[4],
                cards[6],
                cards[8],
                })), 4);
        CardList childList = cardList.cut(4);
        assertEquals("step1", 4, cardList.getCards().size());
        assertEquals("step2", 3, cardList.getOpenedIndex());
        assertEquals("step3", cards[7], cardList.moveTail());
        assertEquals("step4", 4, childList.getCards().size());
        assertEquals("step5", 0, childList.getOpenedIndex());
        assertEquals("step6", cards[8], childList.moveTail());
    }
    public void testCut2() {
        CardList cardList = new CardList();
        cardList.init(new LinkedList<Card>(Arrays.asList(new Card[]{
                cards[1],
                cards[3],
                cards[5],
                cards[7],
                cards[2],
                cards[4],
                cards[6],
                cards[8],
                })), 4);
        CardList childList = cardList.cut(5);
        assertEquals("step1", 5, cardList.getCards().size());
        assertEquals("step2", 4, cardList.getOpenedIndex());
        assertEquals("step3", cards[2], cardList.moveTail());
        assertEquals("step4", 3, childList.getCards().size());
        assertEquals("step5", 0, childList.getOpenedIndex());
        assertEquals("step6", cards[8], childList.moveTail());
    }
    public void testCut3() {
        CardList cardList = new CardList();
        cardList.init(new LinkedList<Card>(Arrays.asList(new Card[]{
                cards[1],
                cards[3],
                cards[5],
                cards[7],
                cards[2],
                cards[4],
                cards[6],
                cards[8],
                })), 4);
        CardList childList = cardList.cut(6);
        assertEquals("step1", 6, cardList.getCards().size());
        assertEquals("step2", 4, cardList.getOpenedIndex());
        assertEquals("step3", cards[4], cardList.moveTail());
        assertEquals("step4", 2, childList.getCards().size());
        assertEquals("step5", 0, childList.getOpenedIndex());
        assertEquals("step6", cards[8], childList.moveTail());
    }
    public void testCut4() {
        CardList cardList = new CardList();
        cardList.init(new LinkedList<Card>(Arrays.asList(new Card[]{
                cards[1],
                cards[3],
                cards[5],
                cards[7],
                cards[2],
                cards[4],
                cards[6],
                cards[8],
                })), 4);
        CardList childList = cardList.cut(2);
        assertEquals("step1", 8, cardList.getCards().size());
        assertEquals("step2", 4, cardList.getOpenedIndex());
        assertEquals("step3", cards[8], cardList.moveTail());
        assertEquals("step4", 0, childList.getCards().size());
        assertEquals("step5", -1, childList.getOpenedIndex());
        assertNull("step6", childList.moveTail());
    }
    public void testLink1() {
        CardList cardList1 = new CardList();
        cardList1.init(new LinkedList<Card>(Arrays.asList(new Card[]{
                cards[8],
                cards[7],
                cards[6],
                cards[5],
                })), 3);
        CardList cardList2 = new CardList();
        cardList2.init(new LinkedList<Card>(Arrays.asList(new Card[]{
                cards[4],
                cards[3],
                cards[2],
                cards[1],
                })), 0);
        cardList1.link(cardList2);
        assertEquals("step1", 8, cardList1.getCards().size());
        assertEquals("step2", 3, cardList1.getOpenedIndex());
        assertEquals("step3", cards[1], cardList1.moveTail());
    }
    public void testLink2() {
        CardList cardList1 = new CardList();
        cardList1.init(new LinkedList<Card>(Arrays.asList(new Card[]{
                cards[8],
                cards[7],
                cards[6],
                cards[5],
                })), 3);
        CardList cardList2 = new CardList();
        cardList2.init(new LinkedList<Card>(Arrays.asList(new Card[]{
                cards[1],
                cards[2],
                cards[3],
                cards[4],
                })), 0);
        cardList1.link(cardList2);
        assertEquals("step1", 4, cardList1.getCards().size());
    }
    public void testAdd1() {
        CardList cardList = new CardList();
        cardList.init(new LinkedList<Card>(Arrays.asList(new Card[]{
                cards[8],
                cards[7],
                cards[6],
                cards[5],
                })), 3);
        cardList.add(cards[4]);
        assertEquals("step1", 5, cardList.getCards().size());
        assertEquals("step2", 3, cardList.getOpenedIndex());
        assertEquals("step3", cards[4], cardList.moveTail());
    }
    public void testAdd2() {
        CardList cardList = new CardList();
        cardList.init(new LinkedList<Card>(Arrays.asList(new Card[]{
                cards[8],
                cards[7],
                cards[6],
                cards[5],
                })), 3);
        cardList.add(cards[7]);
        assertEquals("step1", 4, cardList.getCards().size());
        assertEquals("step2", 3, cardList.getOpenedIndex());
        assertEquals("step3", cards[5], cardList.moveTail());
    }
    public void testMoveTail1() {
        CardList cardList = new CardList();
        cardList.init(new LinkedList<Card>(Arrays.asList(new Card[]{
                cards[8],
                cards[7],
                cards[6],
                cards[5],
                })), 3);
        Card tail = cardList.moveTail();
        assertEquals("step1", 3, cardList.getCards().size());
        assertEquals("step2", 2, cardList.getOpenedIndex());
        assertEquals("step3", cards[6], cardList.moveTail());
        assertEquals("step4", cards[5], tail);
    }
    public void testMoveTail2() {
        CardList cardList = new CardList();
        cardList.init(new LinkedList<Card>(Arrays.asList(new Card[]{
                cards[8],
                cards[7],
                cards[6],
                cards[5],
                })), 2);
        Card tail = cardList.moveTail();
        assertEquals("step1", 3, cardList.getCards().size());
        assertEquals("step2", 2, cardList.getOpenedIndex());
        assertEquals("step3", cards[6], cardList.moveTail());
        assertEquals("step4", cards[5], tail);
    }
}
