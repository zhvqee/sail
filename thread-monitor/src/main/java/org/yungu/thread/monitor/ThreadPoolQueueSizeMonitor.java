package org.yungu.thread.monitor;

import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolQueueSizeMonitor extends AbstractThreadExecutorPoolMonitor implements ThreadPoolMonitor {
    private int queueSizeThreshold;

    public ThreadPoolQueueSizeMonitor(ThreadPoolExecutor threadPoolExecutor, int queueSizeThreshold) {
        super(threadPoolExecutor);
        this.queueSizeThreshold = queueSizeThreshold;
    }

    public String indicatorDesc() {
        return "thread pool queue size monitor";
    }

    public double value() {
        return getCurrentMonitorThreadPool().getQueue().size();
    }

    public boolean ifPrintJStack(double value) {
        return value >= queueSizeThreshold;
    }
}
