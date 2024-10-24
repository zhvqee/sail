package org.binarysearch;

public class LCR072 {

    public int mySqrt(int x) {
        int C = x;
        long sqrt = x;
        while ((long)sqrt * sqrt > x) {
            sqrt = (sqrt + C / sqrt) / 2;
        }
        return (int) sqrt;
    }

    public int mySqrt2(int x) {
        if (x == 0) {
            return 0;
        }
        if (x <= 3) {
            return 1;
        }

        long sqrt = 2;
        while (sqrt * sqrt < x) {
            sqrt = sqrt << 1;
        }
        if (sqrt * sqrt == x) {
            return (int) sqrt;
        }
        sqrt = sqrt - 1;
        while (sqrt * sqrt > x) {
            sqrt--;
        }
        return (int) sqrt;
    }

    public static void main(String[] args) {
        LCR072 sqrt = new LCR072();
        int mySqrt = sqrt.mySqrt(2147483647);
        System.out.println(mySqrt);
        System.out.println(sqrt.mySqrt(2147483647) + "," + (int) Math.sqrt(2147483647));


    }
}
