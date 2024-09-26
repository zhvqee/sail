package org.string;

public class LCR018 {

    public boolean validPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        int right = s.length() - 1;
        int left = 0;
        while (left < right) {
            char ch1 = s.charAt(left);
            char ch2 = s.charAt(right);

            if (needFilter(ch1)) {
                left++;
                continue;
            }
            if (needFilter(ch2)) {
                right--;
                continue;
            }
            if (ch1 >= 'A' && ch1 <= 'Z') {
                ch1 = (char) (ch1 - ('A' - 'a'));
            }
            if (ch2 >= 'A' && ch2 <= 'Z') {
                ch2 = (char) (ch2 - ('A' - 'a'));
            }
            if (ch1 != ch2) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    private boolean needFilter(char ch) {
        if ((ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9') || (ch >= 'A' && ch <= 'Z')) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        LCR018 lcr019 = new LCR018();
        boolean abc = lcr019.validPalindrome("0P");
        System.out.println(abc);
    }
}
