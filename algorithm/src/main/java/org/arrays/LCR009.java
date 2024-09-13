package org.arrays;

public class LCR009 {


    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int count = 0;
        int curValue = 1;
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            curValue *= nums[right];
            if (curValue < k) {
                count += right - left + 1;
                right++;
            } else {
                //找到第一次满足 curValue<k
                while (left <= right && curValue >= k) {
                    curValue /= nums[left];
                    left++;
                }
                //及时left>right 时，也保证结果不变
                count += right - left + 1;
                right++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        LCR009 lcr009 = new LCR009();
        //解释: 8 个乘积小于 100 的子数组分别为: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]。
        int i = lcr009.numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100);
        System.out.println(i);

        i = lcr009.numSubarrayProductLessThanK(new int[]{1, 2, 3}, 0);
        System.out.println(i);
    }
}
