package org.nums;

public class LCR001 {
    public static int div(int a, int b) {
        if (b == Integer.MIN_VALUE) {
            if (a == Integer.MIN_VALUE) {
                return 1;
            }
            return 0;
        }
        if (b == 0) {
            return Integer.MAX_VALUE;
        }
        if (a == Integer.MIN_VALUE) {
            if (b == 1) {
                return Integer.MIN_VALUE;
            }
            if (b == -1) {
                return Integer.MAX_VALUE;
            }
        }

        boolean neg = (a > 0 && b < 0) || (a < 0 && b > 0);


        if (a > 0) {
            a = -a;
        }
        if (b > 0) {
            b = -b;
        }

        int result = 0;
        while (a <= b) {
            int base = 1;
            int bb = b;
            while (a - bb < bb) {
                bb = bb << 1;
                base = base << 1;
            }
            a -= bb;
            result += base;
        }
        return !neg ? result : -result;
    }

    public static void main(String[] args) {
        System.out.println(div(1, 2) + "==" + (1 / 2));
        System.out.println(div(7, 3) + "==" + (7 / 3));
        System.out.println(div(-7, 3) + "==" + (-7 / 3));
        System.out.println(div(-7, -3) + "==" + (-7 / -3));
        System.out.println(div(-127, -32) + "==" + (-127 / -32));
        System.out.println(div(-1000, -32) + "==" + (-1000 / -32));
        System.out.println(div(Integer.MIN_VALUE, -3) + "==" + (Integer.MIN_VALUE / -3));
        System.out.println(div(Integer.MIN_VALUE, 3) + "==" + (Integer.MIN_VALUE / 3));
        System.out.println(div(Integer.MIN_VALUE, 2) + "==" + (Integer.MIN_VALUE / 2));
    }


}
