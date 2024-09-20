package org.arrays;

import java.util.HashMap;
import java.util.Map;

public class LCR010 {

    //设置前缀和为数组下标0，到i 的数字相加，pre[i]=nums[0]+nums[1]+...+nums[i];
    // pre[i] = pre[i-1]+ nums[i],
    //那么找到所有区间[j,i] 的和值为k, 即 nums[j]+nums[j+1]+nums[j+2]+...+nums[i]=k,
    //即 pre[i]-pre[j-1]= k;
    // 则 pre[j-1]=pre[i]-k,
    // 那么我们只要找到满足j,i 满足上面等式的个数即可。
    //可以通过一个map<前嘴和，达到该前缀和的次数> mp;
    //那么 当处于i 位置， 前缀和为pre[i], 查找是否满足pre[i]-k 在之前的mp 里有多少次即可。
    //则结果是所有的次数相加
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> mp = new HashMap<>();
        mp.put(0, 1);
        int pre = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if (mp.containsKey(pre - k)) {
                count += mp.get(pre - k);
            }
            mp.put(pre, mp.getOrDefault(pre, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        LCR010 lcr010 = new LCR010();
        int subarraySum = lcr010.subarraySum(new int[]{1, 1, 1}, 2);
        System.out.println(subarraySum);
    }
}
