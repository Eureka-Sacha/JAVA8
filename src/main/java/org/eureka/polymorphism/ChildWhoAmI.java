package org.eureka.polymorphism;

/**
 * @author: 奎
 * @date: 2017/8/20 16:32
 * @description:
 */
public class ChildWhoAmI extends Parent {
    @Override
    public void print() {
        System.out.println("print from childB.");

    }

    @Override
    public void whoami() {
        System.out.println("I am ChildWhoAmI");
    }
}
