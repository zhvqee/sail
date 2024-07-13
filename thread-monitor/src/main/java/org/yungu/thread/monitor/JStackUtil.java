package org.yungu.thread.monitor;

import java.util.Map;

public class JStackUtil {


    public static String jstack() {
        StringBuilder sb = new StringBuilder();
        Map<Thread, StackTraceElement[]> stackTraces = Thread.getAllStackTraces();
        stackTraces.forEach((thread, el) -> {
            if (el != null && el.length > 0) {
                String format = String.format("thread-name:%s,thread-id:%s,thread-state:%s,", thread.getName(), thread.getId(), thread.getState());
                sb.append(format);
                sb.append("trace:\n");
                for (StackTraceElement stackTraceElement : el) {
                    String s1 = String.format("%s\n", stackTraceElement.toString());
                    sb.append(s1);
                }
                sb.append("\n");
            }
        });
        return sb.toString();
    }

}
