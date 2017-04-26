package org.test.lamdba;

import org.eureka.stream.Apple;
import org.junit.Test;

import java.util.function.Predicate;

/**
 * The type Lamdba test.
 *
 * @author Eureka_Sacha E-mail: 928858519@qq.com
 * @version 创建时间 ：2017年3月16日 下午10:49:48 Lamdba表达式测试
 */
public class LamdbaTest {

    /**
     * Test org.test.lamdba param.
     */
    @Test
    public void testLamdbaParam() {
        Runnable r1 = () -> {
        };//因为runnable 的函数签名为 void->void 所以lamdba表达式可以为 ()->{}
        Runnable r2 = () -> {
            System.out.println("测试");
        };//因为runnable 的函数签名为 void->void 所以lamdba表达式可以为 ()->{}
        Predicate<Apple> p1 = (Apple a) -> a.getColor().equals("red");
        r1.run();//什么也不会发生 返回void.
        r2.run();//输出字符串 返回void

        LamdbaTest.Inner inner = (int x) -> {
            System.out.println(x);
            return x;
        };//用lamdba生成了函数接口的实现
        inner.printSomething(9);//然后就可以和普通方法一样调用了.

        //方法引用
        LamdbaTest.Inner inner2 = inner::printSomething;
        Runnable r4 = r1::run;

    }

    /**
     * 简单的函数式接口
     *
     * @author Eureka_Sacha
     * @FunctionalInterface 是java8中新添加的注解, 是专门用来标记函数式接口的注解
     */
    @FunctionalInterface
    public interface Inner {
        /**
         * Print something int.
         *
         * @param x the x
         * @return the int
         */
        int printSomething(int x);
    }
}
