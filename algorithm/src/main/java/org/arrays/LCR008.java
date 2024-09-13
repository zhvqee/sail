package org.arrays;

public class LCR008 {
    public int minSubArrayLen(int target, int[] nums) {
        int min = -1;
        int curValue = 0;
        int left = 0, right = 0;
        while (right < nums.length) {
            curValue += nums[right];
            if (curValue >= target) {
                while (curValue >= target) {
                    if (min == -1) {
                        min = right - left + 1;
                    } else {
                        min = Math.min(min, right - left + 1);
                    }
                    curValue -= nums[left];
                    left++;
                }
            }
            right++;
        }

        return min == -1 ? 0 : min;
    }

    public static void main(String[] args) {
        LCR008 lcr008 = new LCR008();
        int i = lcr008.minSubArrayLen(11, new int[]{1, 1, 1, 1, 1, 1, 1, 1});
        System.out.println(i);
    }
}
