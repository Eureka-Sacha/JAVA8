package org.eureka.polymorphism;

/**
 * @author: 奎
 * @date: 2017/8/20 16:29
 * @description:
 */
public abstract class Parent implements PrintInterface,WhoAmIInterface {
    public Parent(){
        print();
    }

    public void printMessage(){
        whoami();
    }
}
