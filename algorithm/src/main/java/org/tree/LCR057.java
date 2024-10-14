package org.tree;

public class LCR057 {


    //超时
    //给你一个整数数组 nums 和两个整数 k 和 t 。
    // 请你判断是否存在 两个不同下标 i 和 j，使得 abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。
    //nums[j]-nums[i]<=t==> nums[j]<=nums[i]+t
    //j-i<=k==>j<=i+k;
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        for (int i = 0; i < nums.length; i++) {
            int j = Math.min(k+i,nums.length-1);
            if (j > i) {
                while (j > i) {
                    long s1= nums[j];
                    if (Math.abs(s1 - nums[i]) <= t ) {
                        System.out.println("index:" + i + "," + j);
                        return true;
                    }
                    j--;
                }
            }
        }
        return false;
    }


    //示例 1：
    //
    //输入：nums = [1,2,3,1], k = 3, t = 0
    //输出：true
    //示例 2：
    //
    //输入：nums = [1,0,1,1], k = 1, t = 2
    //输出：true
    //示例 3：
    //
    //输入：nums = [1,5,9,1,5,9], k = 2, t = 3
    //输出：false
    public static void main(String[] args) {
        LCR057 lcr057 = new LCR057();
        boolean b = lcr057.containsNearbyAlmostDuplicate(new int[]{1, 2, 3, 1}, 3, 0);
        System.out.println(b);
        b = lcr057.containsNearbyAlmostDuplicate(new int[]{1, 0, 1, 1}, 1, 2);
        System.out.println(b);
        boolean b2 = lcr057.containsNearbyAlmostDuplicate(new int[]{1, 5, 9, 1, 5, 9}, 2, 3);
        System.out.println(b2);
/*
        boolean b1 = lcr057.containsNearbyAlmostDuplicate(new int[]{1, 2, 5, 6, 7, 2, 4}, 4, 0);
        System.out.println(b1);*/
    }


}
