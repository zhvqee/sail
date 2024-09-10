package org.nums;

public class LCR004 {

    public static int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 31; i >= 0; i--) {
            int c = 0;
            for (int n : nums) {
                c += (n >> i) & 0x1;
            }
            c = (c % 3) << i;
            res += c;
        }
        return res;
    }

    public static void main(String[] args) {
        int singleNumber = singleNumber(new int[]{2, 2, 4, 2,3,3,3});
        System.out.println(singleNumber);
    }
}
