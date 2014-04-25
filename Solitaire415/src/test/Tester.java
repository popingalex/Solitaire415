package test;

import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class Tester {
    /**
     * @param args
     */
    public static void main(String[] args) {
        TestSuite suite = new TestSuite();
        
        suite.addTestSuite(CardTest.class);
        suite.addTestSuite(CardDeckTest.class);
        suite.addTestSuite(CardListTest.class);
        suite.addTestSuite(CardStackTest.class);
        
        TestRunner.run(suite);
    }
}
