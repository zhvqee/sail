package org.string;

public class LCR019 {


    public boolean validPalindrome(String s) {
        int right = s.length() - 1;
        int left = 0;
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                boolean s1 = validPalindrome(s, left + 1, right);
                if (s1) {
                    return true;
                }
                return validPalindrome(s, left, right - 1);
            }
        }
        return true;
    }


    public boolean validPalindrome(String s, int leftIndex, int rightIndex) {
        int right = rightIndex;
        int left = leftIndex;
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LCR019 lcr019 = new LCR019();
        boolean abc = lcr019.validPalindrome("ababacc");
        System.out.println(abc);
    }
}
