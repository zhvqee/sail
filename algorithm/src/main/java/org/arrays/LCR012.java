package org.arrays;

import java.io.IOException;

public class LCR012 {

    //  找到一个j 使得如下等式成立：
    // nums[0]+nums[1]+...+nums[j-1]= nums[j+1]+nums[j+2]+...nums[N-1]
    // pre[j-1]+ pre[N-1]-pre[j]
    //pre[j-1]+pre[j]=pre[N-1]
    //2*pre[j-1]+ nums[j]=pre[N-1]
    public int pivotIndex(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum - nums[0] == 0) {
            return 0;
        }

        int pre = nums[0];
        for (int j = 1; j < nums.length; j++) {
            if (2 * pre + nums[j] == sum) {
                return j;
            }
            pre += nums[j];
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        LCR012 lcr012 = new LCR012();
        int i = lcr012.pivotIndex(new int[]{2, 1, -1});
        System.out.println(i);
    }
}
