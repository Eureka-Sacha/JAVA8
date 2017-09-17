package org.eureka.polymorphism;

/**
 * @author: 奎
 * @date: 2017/8/20 16:29
 * @description:
 */
public class ChildPrint extends Parent {

    @Override
    public void print() {
        System.out.println("print form childA.");
    }

    @Override
    public void whoami() {
        System.out.println("I am ChildPrint");
    }
}
