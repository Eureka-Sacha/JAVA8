package org.test.thread;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: 奎
 * @date: 2017/5/4 9:56
 * @description:
 */
public class ThreadPoolTest {

    public static void main(String []args){
        ThreadPoolTest test=new ThreadPoolTest();
        test.createSampleThreadPoolTest();
    }

    @Test
    public void createSampleThreadPoolTest(){
        ThreadPoolExecutor pool =new ThreadPoolExecutor(10,20,1, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(10));

        for(int i=0;i<30;i++){
            MyTask myTask = new MyTask(i);
            pool.execute(myTask);
            System.out.println("线程池中线程数目："+pool.getPoolSize()+"，队列中等待执行的任务数目："+
                    pool.getQueue().size()+"，已执行玩别的任务数目："+pool.getCompletedTaskCount());
        }
        pool.shutdown();
    }
    class MyTask implements Runnable {
        private int taskNum;

        public MyTask(int num) {
            this.taskNum = num;
        }

        @Override
        public void run() {
            System.out.println("正在执行task " + taskNum);
            try {
                Thread.currentThread().sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task " + taskNum + "执行完毕");
        }
    }
}
