package org.yungu.thread.monitor;

import java.util.concurrent.ThreadPoolExecutor;

public abstract class AbstractThreadExecutorPoolMonitor implements ThreadPoolMonitor {
    private final ThreadPoolExecutor threadPoolExecutor;

    public AbstractThreadExecutorPoolMonitor(ThreadPoolExecutor threadPoolExecutor) {
        this.threadPoolExecutor = threadPoolExecutor;
    }


    public ThreadPoolExecutor getCurrentMonitorThreadPool() {
        return threadPoolExecutor;
    }
}
