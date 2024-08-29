package com.yungu.expression;

import com.yungu.expression.exceptions.LexemeException;

public class NumberUtils {

    //only 10进制
    public static Number parseNumber(String numberStr) {
        if (numberStr == null || numberStr.isEmpty()) {
            throw new LexemeException("转化数字失败");
        }
        boolean hasDot = false;
        long lval = 0L;
        double dval = 0D;
        boolean negative = false;
        int i = 0;
        long limit = -Long.MAX_VALUE;
        long mulitmin = limit / 10;
        int len = numberStr.length();
        if (numberStr.charAt(i) == '-') {
            negative = true;
            limit = Long.MIN_VALUE;
            mulitmin = limit / 10;
            i++;
        } else if (numberStr.charAt(i) == '+') {
            i++;
        }
        double dotIdx = 10.0;
        while (i < len) {
            char ch = numberStr.charAt(i);
            if (ch == '.') {
                hasDot = true;
            } else {
                int v = Character.digit(ch, 10);

                if (hasDot) {
                    double dv = v / dotIdx;
                    dval -= dv;
                    dotIdx *= 10;
                } else {
                    if (lval < mulitmin) {
                        throw new LexemeException("数据溢出" + numberStr);
                    }

                    lval *= 10;
                    dval *= 10;
                    if (lval < limit + v) {
                        throw new LexemeException("数据溢出" + numberStr);
                    }
                    lval -= v;
                    dval -= v;
                }
            }
            i++;
        }

        if (!hasDot) {
            return !negative ? -lval : lval;
        }
        return !negative ? -dval : dval;
    }

    public static void main(String[] args) {
     /*   System.out.println(NumberUtils.parseNumber("10"));
        System.out.println(NumberUtils.parseNumber("-100"));
        System.out.println(NumberUtils.parseNumber("+100"));
        System.out.println(NumberUtils.parseNumber("+100.123"));
        System.out.println(NumberUtils.parseNumber("-100.123"));
        System.out.println(NumberUtils.parseNumber(Long.MAX_VALUE + ""));
        System.out.println(Long.MAX_VALUE);*/
        // System.out.println(NumberUtils.parseNumber("9223372036854775808"));
        // System.out.println(NumberUtils.parseNumber("-9223372036854775808"));
        //  System.out.println(NumberUtils.parseNumber("-9223372036854775809"));

        double ss = -9223372036854775807.1234D;
        System.out.println(ss);
        System.out.println(NumberUtils.parseNumber("-9223372036854775807.1234"));

    }
}
