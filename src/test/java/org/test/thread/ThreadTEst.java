package org.test.thread;

import org.junit.Test;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * The type Thread test.
 *
 * @author Eureka.
 * @date 2017 -05-02 14:19:03
 * @author: 奎
 * @date: 2017 /5/1 16:33
 * @description:
 */
public class ThreadTest {
    private int i = 3;
    /* 1024778537  吴
     * 线程计数器
     * 	将线程数量初始化
     * 	每执行完成一条线程，调用countDown()使计数器减1
     * 	主线程调用方法await()使其等待，当计数器为0时才被执行
     */
    private CountDownLatch latch = new CountDownLatch(i);

    /**
     * The type My thread.
     *
     * @author Eureka.
     * @date 2017 -05-02 14:19:03
     */
    static class MyThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                System.out.println(Thread.currentThread().getName() + "\t" + System.currentTimeMillis());
                try {
                    Thread.sleep(1000 * 3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Create thread.
     */
    @Test
    public void createThread() {

    }


    private static boolean b;
    private static int count = 0;

    /**
     * Lamdba thread test.
     */
    @Test
    public void lamdbaThreadTest() {

        new Thread(() -> {
            while (true) {
                count++;
                if (b == !b) {
                    System.out.println(new Date() + ":" + count);
                }
            }
        }).start();
        new Thread(() -> {
            while (true) {
                b = !b;
            }
        }
        ).start();
        try {
            latch.await(); // 主线程等待
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) throws InterruptedException {
//        Thread t1 = new Thread(() -> {
//            while (true) {
//                System.out.println(Thread.currentThread().getName() + "\t" + System.currentTimeMillis());
//                try {
//                    Thread.sleep(1000 * 1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, "lamdbaThread");
//        Thread t2 = new Thread(new MyThread());
//        t1.start();
//        t2.start();
//    }

    /**
     * The type My thread printer 2.
     *
     * @author Eureka.
     * @date 2017 -05-02 14:19:03
     */
    static class MyThreadPrinter2 implements Runnable {

        private String name;
        private Object prev;
        private Object self;

        private MyThreadPrinter2(String name, Object prev, Object self) {
            this.name = name;
            this.prev = prev;
            this.self = self;
        }

        @Override
        public void run() {
            int count = 10;
            while (count > 0) {
                synchronized (prev) {
                    synchronized (self) {
                        System.out.print(name);
                        count--;
                        self.notify();
                    }
                    try {
                        prev.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        /**
         * The entry point of application.
         *
         * @param args the input arguments
         * @throws Exception the exception
         */
        public static void main(String[] args) throws Exception {
            Object a = new Object();
            Object b = new Object();
            Object c = new Object();
            MyThreadPrinter2 pa = new MyThreadPrinter2("A", c, a);
            MyThreadPrinter2 pb = new MyThreadPrinter2("B", a, b);
            MyThreadPrinter2 pc = new MyThreadPrinter2("C", b, c);


            new Thread(pa).start();
            Thread.sleep(100);  //确保按顺序A、B、C执行
            new Thread(pb).start();
            Thread.sleep(100);
            new Thread(pc).start();
            Thread.sleep(100);
        }
    }
}
