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
        assertEquals(2, cardList.countFaces());
        assertEquals(2, cardList.countBacks());
        assertEquals(2, cardList.getOpenedIndex());
        assertEquals(cards[7], cardList.getTailCard());
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
        
        assertEquals(1, cardList.countFaces());
        assertEquals(3, cardList.countBacks());
        assertEquals(3, cardList.getOpenedIndex());
        assertEquals(cards[7], cardList.moveTail());

        assertEquals(4, childList.countFaces());
        assertEquals(0, childList.countBacks());
        assertEquals(0, childList.getOpenedIndex());
        assertEquals(cards[8], childList.moveTail());
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
        
        assertEquals(1, cardList.countFaces());
        assertEquals(4, cardList.countBacks());
        assertEquals(4, cardList.getOpenedIndex());
        assertEquals(cards[2], cardList.moveTail());
        
        assertEquals(3, childList.countFaces());
        assertEquals(0, childList.countBacks());
        assertEquals(0, childList.getOpenedIndex());
        assertEquals(cards[8], childList.moveTail());
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
        
        assertEquals(2, cardList.countFaces());
        assertEquals(4, cardList.countBacks());
        assertEquals(cards[4], cardList.moveTail());

        assertEquals(2, childList.countFaces());
        assertEquals(0, childList.countBacks());
        assertEquals(0, childList.getOpenedIndex());
        assertEquals(cards[8], childList.moveTail());
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
        
        assertNull(childList);

        assertEquals(4, cardList.countFaces());
        assertEquals(4, cardList.countBacks());
        assertEquals(4, cardList.getOpenedIndex());
        assertEquals(cards[8], cardList.moveTail());
        
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
                cards[13*2+4],
                cards[3],
                cards[2],
                cards[1],
        })), 0);
        cardList2.link(cardList1);
        assertEquals(5, cardList1.countFaces());
        assertEquals(3, cardList1.countBacks());
        assertEquals(cards[1], cardList1.moveTail());
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
        cardList2.link(cardList1);
        assertEquals(1, cardList1.countFaces());
        assertEquals(3, cardList1.countBacks());
        assertEquals(3, cardList1.getOpenedIndex());
        assertEquals(cards[5], cardList1.moveTail());
    }
    public void testAdd1() {
        CardList cardList = new CardList();
        cardList.init(new LinkedList<Card>(Arrays.asList(new Card[]{
                cards[8],
                cards[7],
                cards[6],
                cards[5],
        })), 3);
        cardList.add(cards[13*2+4]);
        assertEquals(2, cardList.countFaces());
        assertEquals(3, cardList.countBacks());
        assertEquals(3, cardList.getOpenedIndex());
        assertEquals(cards[13*2+4], cardList.moveTail());
    }
    public void testAdd2() {
        CardList cardList = new CardList();
        cardList.init(new LinkedList<Card>(Arrays.asList(new Card[]{
                cards[8],
                cards[7],
                cards[6],
                cards[5],
        })), 3);
        cardList.add(cards[13*2+7]);
        assertEquals(1, cardList.countFaces());
        assertEquals(3, cardList.countBacks());
        assertEquals(3, cardList.getOpenedIndex());
        assertEquals(cards[5], cardList.moveTail());
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
        assertEquals(1, cardList.countFaces());
        assertEquals(2, cardList.countBacks());
        assertEquals(cards[6], cardList.moveTail());
        assertEquals(cards[5], tail);
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
        assertEquals(1, cardList.countFaces());
        assertEquals(2, cardList.countBacks());
        assertEquals(cards[6], cardList.moveTail());
        assertEquals(cards[5], tail);
    }
}
