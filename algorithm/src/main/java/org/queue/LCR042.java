package org.queue;

import java.util.LinkedList;

public class LCR042 {

    class RecentCounter {

        private LinkedList<Integer> times = new LinkedList<>();


        public RecentCounter() {

        }

        public int ping(int t) {
            times.add(t);
            while (times.getFirst() < t - 3000) {
                times.removeFirst();
            }
            return times.size();
        }
    }
}
