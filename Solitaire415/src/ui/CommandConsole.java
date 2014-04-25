package ui;




public class CommandConsole extends AbstractUI {

    @Override
    public void refresh() {
        System.out.println("deck["+dataSource.getDeckData().getCards().size()+"]:"+dataSource.getDeckData().getCurrentCard());
        for(int i=0;i<4;i++) {
            System.out.printf("stack%d:%s\n", i+1, dataSource.getStackData()[i].getStack());
        }
        try{
        for(int i=0;i<7;i++) {
            int open = dataSource.getListData()[i].getOpenedIndex();
            int length = dataSource.getListData()[i].getCards().size();
            System.out.printf("list%d:%s\n", i+1, dataSource.getListData()[i].getCards().subList(open, length).toString());
        }
        }catch (Exception e) {
            // TODO: handle exception
        }
    }

}
