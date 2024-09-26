package org.string;

import java.util.HashMap;
import java.util.Map;

public class LCR016 {
    //最长无重复 子串
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        Map<Character, Integer> letter = new HashMap<>();

        int left = 0;
        int right = 0;
        int len = s.length();
        int max = 0;
        while (right < len) {
            char ch = s.charAt(right);
            if (letter.get(ch) == null || letter.get(ch) == 0) {
                letter.put(ch, 1);
                right++;
                max = Math.max(max, right - left);
            } else {
                max = Math.max(max, right - left);
                letter.put(ch, letter.get(ch) + 1);
                while (letter.get(ch) != 1 && left < right) {
                    letter.put(s.charAt(left), letter.get(s.charAt(left)) - 1);
                    left++;
                }
                right++;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        LCR016 lcr016 = new LCR016();
        int abcddaeccabfcde = lcr016.lengthOfLongestSubstring("tmmzuxt");
        System.out.println(abcddaeccabfcde);
    }
}
