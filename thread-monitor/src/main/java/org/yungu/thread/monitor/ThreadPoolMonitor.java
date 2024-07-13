package org.yungu.thread.monitor;

import java.util.concurrent.ThreadPoolExecutor;

public interface ThreadPoolMonitor {

    String indicatorDesc();

    double value();

    boolean ifPrintJStack(double value);

    ThreadPoolExecutor getCurrentMonitorThreadPool();
}
