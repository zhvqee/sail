package org.yungu.thread.monitor.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadStopTest {

    private static ThreadPoolExecutor executorService = new ThreadPoolExecutor(500,
    
    500,
    0,
    TimeUnit.MICROSECONDS,
    new ArrayBlockingQueue<>(400));


    public static void main(String[] args) throws InterruptedException {
       
        executorService.submit(()-> {
            while(true){
                System.out.println("start");
                for(int i=1;i<=10000;i++){

                }
                
            }
        });

        Thread.sleep(5000L);
        System.out.println("close thread pool");
        executorService.shutdownNow();
        System.out.println("close thread pool end");

        Thread.sleep(1000000L);

    }
    
}
