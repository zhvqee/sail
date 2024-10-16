package org.heaps;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LCR060 {


    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, int[]> map = new HashMap<>();
        for (int n : nums) {
            int[] arr = map.getOrDefault(n, new int[]{n, 0});
            arr[1]++;
            map.put(n,arr);
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for (int[] value : map.values()) {
            queue.add(value);
        }
        int[] result = new int[k];
        while (k > 0 && !queue.isEmpty()) {
            result[result.length - k] = queue.poll()[0];
            k--;
        }
        return result;
    }

    public static void main(String[] args) {
        LCR060 lcr060 = new LCR060();
        int[] ints = lcr060.topKFrequent(new int[]{1, 1, 3, 4, 2, 3, 2, 2, 2}, 3);
        for (int anInt : ints) {
            System.out.println(anInt);
        }

    }
}
