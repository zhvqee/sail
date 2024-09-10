package org.nums;

public class LCR005 {

    public static int maxProduct(String[] words) {
        int len = words.length;
        int max = 0;
        for (int i = 0; i < len - 1; i++) {
            int mask1 = bitMask(words[i]);
            for (int j = i + 1; j < len; j++) {
                int mask2 = bitMask(words[j]);
                if ((mask2 & mask1) == 0) {
                    max = Math.max(words[i].length() * words[j].length(), max);
                }
            }
        }
        return max;
    }

    private static int bitMask(String str) {
        int mask = 0;
        int len = str.length();
        for (int i = 0; i < len; i++) {
            int bitN = str.charAt(i) - 'a';
            mask |= (1 << bitN);
        }
        return mask;
    }

    public static void main(String[] args) {
        int i1 = maxProduct(new String[]{"abcw", "baz", "foo", "bar", "fxyz", "abcdef"});

        int i2 = maxProduct(new String[]{"a", "ab", "abc", "d", "cd", "bcd", "abcd"});
        int i3 = maxProduct(new String[]{"a", "aa", "aaa", "aaaa"});
        System.out.println(i1);
        System.out.println(i2);
        System.out.println(i3);
    }
}
