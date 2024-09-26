package org.string;

import java.util.ArrayList;
import java.util.List;

public class LCR015 {
    //输入: s = "abab", p = "ab"
    //输出: [0,1,2]
    //输入: s = "cbaebabacd", p = "abc"
    //输出: [0,6]
    public List<Integer> findAnagrams(String s, String p) {
        int[] parr = new int[26];
        for (int i = 0; i < p.length(); i++) {
            parr[p.charAt(i) - 'a']++;
        }
        int left = 0;
        int right = 0;
        int len = s.length();
        int min = p.length();
        List<Integer> ret = new ArrayList<>();
        while (right < len) {
            int ch = s.charAt(right) - 'a';
            parr[ch]--;
            if (parr[ch] == 0 && right - left + 1 == min) {
                ret.add(left);
                parr[s.charAt(left) - 'a']++;
                left++;
                right++;
            } else if (parr[ch] > 0) {
                right++;
            } else { // 小于0，说明减多了，或者不存在的字符
                while (parr[ch] != 0 && left <= right) {
                    parr[s.charAt(left) - 'a']++;
                    left++;
                }
                right++;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        LCR015 lcr015 = new LCR015();
        List<Integer> anagrams = lcr015.findAnagrams("abab", "ab");
        System.out.println(anagrams);
    }
}
