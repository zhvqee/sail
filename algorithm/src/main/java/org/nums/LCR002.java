package org.nums;

public class LCR002 {


    public static String addBinary(String a, String b) {
        if (a.length() > b.length()) {
            return addBinary(b, a);
        }
        char[] sb = new char[b.length() + 1];
        int insertPos = b.length();

        //保证a <b;
        int posa = a.length() - 1;
        int posb = b.length() - 1;
        int jin = 0;
        for (; posa >= 0 && posb >= 0; posa--, posb--) {
            int ch1 = a.charAt(posa) - '0';
            int ch2 = b.charAt(posb) - '0';
            int res = ch1 + ch2 + jin;
            if (res >= 2) {
                jin = 1;
                res -= 2;
            } else {
                jin = 0;
            }
            sb[insertPos--] = (char) (res + '0');
        }
        if (b.length() > a.length()) {
            for (int i = b.length() - a.length() - 1; i >= 0; i--) {
                int res = b.charAt(i) - '0' + jin;
                if (res >= 2) {
                    jin = 1;
                    res -= 2;
                } else {
                    jin = 0;
                }
                sb[insertPos--] = (char) (res + '0');
            }
        }
        if (jin == 1) {
            sb[insertPos--] = (char) (jin + '0');
            return new String(sb);
        }
        StringBuilder sb1 = new StringBuilder();
        for (int i = 1; i < sb.length; i++) {
            sb1.append(sb[i]);
        }
        return sb1.toString();
    }

    public static void main(String[] args) {
        // 110110

        String s1 = addBinary("100", "110010");
        System.out.println(s1);
        String s2 = addBinary("1", "110");
        System.out.println(s2);

        String s3 = addBinary("1", "1111");
        System.out.println(s3);
    }
}
