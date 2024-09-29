package org.queue;

import java.util.LinkedList;

public class LCR041 {

    class MovingAverage {

        private int size;

        private int sum;

        private LinkedList<Integer> queue;

        public MovingAverage(int size) {
            this.size = size;
            this.queue = new LinkedList<>();
        }


        public double next(int val) {
            if (queue.size() == size) {
                Integer firstValue = queue.removeFirst();
                sum -= firstValue;
            }
            sum += val;
            queue.addLast(val);
            return sum * 0.1 / queue.size();

        }
    }
}
