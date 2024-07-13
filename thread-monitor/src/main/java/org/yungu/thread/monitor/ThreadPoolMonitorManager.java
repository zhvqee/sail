package org.yungu.thread.monitor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolMonitorManager {

    private static List<ThreadPoolMonitor> monitorList = new ArrayList<ThreadPoolMonitor>();

    public static long lastJstackPrintTime = 0;
    public static ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1, new ThreadFactory() {
        AtomicInteger id = new AtomicInteger();

        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            thread.setName("monitor-thread-" + id.incrementAndGet());
            return thread;
        }

    });

    public static void register(ThreadPoolMonitor monitor) {
        synchronized (monitorList) {
            monitorList.add(monitor);
        }
    }

    public static void init() {
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            for (ThreadPoolMonitor threadPoolMonitor : monitorList) {
                double value = threadPoolMonitor.value();
                String indicatorDesc = threadPoolMonitor.indicatorDesc();
                String format = String.format("%s ,current value %s", indicatorDesc, value);
                System.out.println(format);
                if (threadPoolMonitor.ifPrintJStack(value)) {
                    if (lastJstackPrintTime == 0 || System.currentTimeMillis() - lastJstackPrintTime > 3000) {
                        String jstack = JStackUtil.jstack();
                        System.out.println(jstack);
                        lastJstackPrintTime = System.currentTimeMillis();
                    }
                }
            }
        }, 1, 3, TimeUnit.SECONDS);

    }


}
