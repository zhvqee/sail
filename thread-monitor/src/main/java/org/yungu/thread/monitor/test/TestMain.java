package org.yungu.thread.monitor.test;

import org.yungu.thread.monitor.ThreadPoolMonitorManager;
import org.yungu.thread.monitor.ThreadPoolQueueSizeMonitor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestMain {
    private static ThreadPoolExecutor executorService = new ThreadPoolExecutor(10,
            20,
            0,
            TimeUnit.MICROSECONDS,
            new ArrayBlockingQueue<>(400));

    public static void main(String[] args) {
        ThreadPoolMonitorManager.init();
        ThreadPoolMonitorManager.register(new ThreadPoolQueueSizeMonitor(executorService, 10));


        while (true) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
