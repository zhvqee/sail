package org.heaps;

import java.util.PriorityQueue;

public class LCR059 {

    public static class KthLargest {
        private PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((a, b) -> a - b);
        private int k = 0;

        public KthLargest(int k, int[] nums) {
            for (int num : nums) {
                priorityQueue.add(num);
                if (priorityQueue.size() > k) {
                    priorityQueue.poll();
                }
            }
            this.k = k;
        }

        public int add(int val) {
            priorityQueue.add(val);
            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
            return priorityQueue.peek();
        }
    }

    public static void main(String[] args) {
        //["KthLargest", "add", "add", "add", "add", "add"]
        //[[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
        //输出：
        //[null, 4, 5, 5, 8, 8]
        /*KthLargest kthLargest = new KthLargest(3, new int[]{4, 5, 8, 2});
        System.out.println(kthLargest.add(3));
        System.out.println(kthLargest.add(5));
        System.out.println(kthLargest.add(10));
        System.out.println(kthLargest.add(9));
        System.out.println(kthLargest.add(4));*/

        // 4, 5, 8, 2,9,10,3,1
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> a - b);
        queue.add(4);
        queue.add(5);
        queue.add(8);
        queue.add(2);
        queue.add(9);
        queue.add(10);
        queue.add(3);
        queue.add(1);

        queue.remove(new Integer(8));

    }
}
