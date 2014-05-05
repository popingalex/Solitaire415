package ui;

import java.util.Arrays;


public class CommandConsole extends AbstractUI {
    @Override
    public void refresh() {
        
        
        System.out.println("deck["+countDeckBacks()+":"+countDeckFaces()+"]:"+getDeckCard(countDeckBacks()));
        for(int i=0;i<4;i++) {
            System.out.printf("stack%d:%s\n", i+1, countStackFaces(i)==0?"NONE":getStackCard(countStackFaces(i)-1, i));
        }
        for(int i=0;i<7;i++) {
            System.out.printf("list%d:%d:%s\n", i+1, countListBacks(i), Arrays.toString(getListCards(i)));
        }
    }

}
