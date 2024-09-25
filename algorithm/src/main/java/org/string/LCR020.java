package org.string;

public class LCR020 {
    public int countSubstrings(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (i != s.length() - 1)
                sb.append(s.charAt(i) + "#");
            else
                sb.append(s.charAt(i));
        }
        String s2 = sb.toString();
        int sum = 0;
        for (int i = 0; i < s2.length(); i++) {
            int left = i;
            int right = i;
            while (left >= 0 && right <= s2.length() - 1) {
                if (s2.charAt(left) == s2.charAt(right)) {
                    if (s2.charAt(left) != '#') {
                        sum += 1;
                    }

                    left--;
                    right++;
                } else {
                    break;
                }
            }
        }
        return sum;
    }

    public int countSubstringsV2(String s) {
        int n = s.length(), ans = 0;
        for (int i = 0; i < 2 * n - 1; ++i) {
            int l = i / 2, r = i / 2 + i % 2;
            System.out.println(l + " : " + r);
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                --l;
                ++r;
                ++ans;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        LCR020 lcr = new LCR020();
        int ababa = lcr.countSubstrings("ababa");
        int ababa1 = lcr.countSubstringsV2("ababa");
        System.out.println(ababa);
    }
}
