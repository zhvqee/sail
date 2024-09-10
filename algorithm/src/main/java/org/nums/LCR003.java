package org.nums;

public class LCR003 {

    public static int[] countBits(int n) {
        int[] result = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            result[i] = countBit(i);
        }
        return result;
    }

    private static int countBit(int i) {
        int result = 0;
        while (i != 0) {
            if ((i & 0x1) == 1) {
                result++;
            }
            i = i >> 1;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] countBits = countBits(10);
        for (int i : countBits) {
            System.out.println(i);
        }
    }
}
