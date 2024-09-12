package org.yungu.thread.monitor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolSizeTest {

    private static ThreadPoolExecutor executorService = new ThreadPoolExecutor(500,
            500,
            0,
            TimeUnit.MICROSECONDS,
            new ArrayBlockingQueue<>(400));

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (true) {
                double v = executorService.getActiveCount() * 1.0 / executorService.getPoolSize();
                System.out.println(v + ":" + executorService.getQueue().size());
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        ThreadLocalRandom random = ThreadLocalRandom.current();
        while (true){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(random.nextLong(1000));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            Thread.sleep(2);
        }
        //Thread.sleep(1000000L);

    }
}
