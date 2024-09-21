package org.string;

public class LCR014 {

    public boolean checkInclusion(String s1, String s2) {
        int[] chNum = new int[26];
        int length = s1.length();
        int total = 0;
        for (int i = 0; i < length; i++) {
            chNum[s1.charAt(i) - 'a'] += 1;
            total++;
        }

        int len2 = s2.length();
        int left = 0;
        int right = 0;
        while (right < len2) {
            int ch = s2.charAt(right) - 'a';
            chNum[ch]--;
            while (chNum[ch] < 0 && left <= right) {
                chNum[s2.charAt(left)-'a']++;
                left++;
            }
            if (right - left + 1 == total) {
                return true;
            }
            right++;
        }


        return false;

    }

    public static void main(String[] args) {
        LCR014 lcr014 = new LCR014();
        boolean checkInclusion = lcr014.checkInclusion("abc", "eidbaabcooccbc");
        System.out.println(checkInclusion);
    }
}
