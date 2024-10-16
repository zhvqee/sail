package org.heaps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class LCR061 {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> {

            //(x1,y1) 比较 (x2,y2) 和最小
            int x1 = nums1[o1[0]];
            int y1 = nums2[o1[1]];

            int x2 = nums1[o2[0]];
            int y2 = nums2[o2[1]];
            return x1 + y1 - (x2 + y2);
        });

        List<List<Integer>> result = new ArrayList<>();

        //因为排好序，那么最小的，肯定是（x,0)下标
        int m = Math.min(k, nums1.length);
        for (int i = 0; i < m; i++) {
            queue.add(new int[]{i, 0});
        }

        while (k > 0 && !queue.isEmpty()) {
            int[] idxPair = queue.poll();
            result.add(Arrays.asList(nums1[idxPair[0]], nums2[idxPair[1]]));
            k--;

            if (idxPair[1] + 1 < nums2.length) {//不是最后一个元素，还可以扩充
                queue.add(new int[]{idxPair[0], idxPair[1] + 1});
            }
            if (queue.isEmpty()) {
                if (idxPair[0] + 1 < nums1.length) {
                    queue.add(new int[]{idxPair[0] + 1, 0});
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
        LCR061 lcr061 = new LCR061();
        List<List<Integer>> lists = lcr061.kSmallestPairs(new int[]{1, 2}, new int[]{3}, 3);
        System.out.println(lists);
    }

}
