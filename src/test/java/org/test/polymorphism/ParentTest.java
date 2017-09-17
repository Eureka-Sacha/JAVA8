package org.test.polymorphism;

import org.eureka.polymorphism.ChildPrint;
import org.eureka.polymorphism.ChildWhoAmI;
import org.junit.Before;
import org.junit.Test;

/**
 * @author: 奎
 * @date: 2017/8/20 16:33
 * @description:
 */
public class ParentTest {
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void polymorphismTest() {
        ChildPrint childA = new ChildPrint();
        ChildWhoAmI childB=new ChildWhoAmI();

        childA.printMessage();
        childB.printMessage();
    }
}