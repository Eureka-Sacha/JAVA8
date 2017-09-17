package org.test.thread;

import java.util.concurrent.CyclicBarrier;

/**
 * @author: 奎
 * @date: 2017/8/20 23:15
 * @description:
 */
public class CyclicBarrierTest {
    public static class CyclicBarrierThread extends Thread
    {
        private CyclicBarrier cb;
        private int sleepSecond;

        public CyclicBarrierThread(CyclicBarrier cb, int sleepSecond)
        {
            this.cb = cb;
            this.sleepSecond = sleepSecond;
        }

        public void run()
        {
            try
            {
                System.out.println(this.getName() + "运行了");
                Thread.sleep(sleepSecond * 1000);
                System.out.println(this.getName() + "准备等待了, 时间为" + System.currentTimeMillis());
                cb.await();
                System.out.println(this.getName() + "结束等待了, 时间为" + System.currentTimeMillis());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args)
    {
        Runnable runnable = () -> System.out.println("CyclicBarrier的所有线程await()结束了，我运行了, 时间为" + System.currentTimeMillis());
        CyclicBarrier cb = new CyclicBarrier(3, runnable);
        CyclicBarrierThread cbt0 = new CyclicBarrierThread(cb, 3);
        CyclicBarrierThread cbt1 = new CyclicBarrierThread(cb, 6);
        CyclicBarrierThread cbt2 = new CyclicBarrierThread(cb, 9);
        cbt0.start();
        cbt1.start();
        cbt2.start();
    }
}
