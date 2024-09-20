package org.arrays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LCR011 {

    //前缀和，mp， key：为 和为x 时，，value:达到该和x时，最近的位置j,
    //则 pre[i]-pre[j]=0时，用i-j+1 为最大长度
    public int findMaxLength(int[] nums) {
        int max = 0;
        Map<Integer, Integer> mp = new HashMap<>();
        int count = 0;
        mp.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                count++;
            } else {
                count--;
            }
            if (mp.containsKey(count)) {
                max = Math.max(max, i - mp.get(count));
            } else {
                mp.put(count, i);
            }
        }

        return max;

    }

    // [j,i] 使得
    public int findMaxLengthV2(int[] nums) {
        int max = 0;
        // 位置i 为止有多少个1
        Map<Integer, Integer> mp1 = new HashMap<>();
        Map<Integer, Integer> mp0 = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] == 1) {
                mp1.put(i, mp1.getOrDefault(i - 1, 0) + 1);
                mp0.put(i, mp0.getOrDefault(i - 1, 0));
            } else {
                mp0.put(i, mp0.getOrDefault(i - 1, 0) + 1);
                mp1.put(i, mp1.getOrDefault(i - 1, 0));
            }
            for (int j = i - 1; j >= 0; j--) {
                if ((i - j + 1) % 2 == 0) {

                    int c1 = mp1.get(i) - mp1.get(j) + (nums[j] == 1 ? 1 : 0);
                    int c0 = mp0.get(i) - mp0.get(j) + (nums[j] == 0 ? 1 : 0);
                    if (c1 == c0) {
                        max = Math.max(max, c1 * 2);
                    }
                }

            }

        }

        return max;
    }

    public static void main(String[] args) throws IOException {
        LCR011 lcr011 = new LCR011();
        int maxLength1 = lcr011.findMaxLength(new int[]{0, 0, 1, 0, 0, 0, 1, 1});
        System.out.println(maxLength1);
        int maxLength2 = lcr011.findMaxLength(new int[]{0, 1, 0});
        System.out.println(maxLength2);
        int maxLength3 = lcr011.findMaxLength(new int[]{0, 1});
        System.out.println(maxLength3);

        //44578
        URL resource = Thread.currentThread().getContextClassLoader().getResource("lcr011.txt");
        InputStreamReader reader = new InputStreamReader(resource.openStream());
        BufferedReader br = new BufferedReader(reader);
        String rd = br.readLine();
        String[] split = rd.split(",");
        int[] array = Arrays.stream(split).map(Integer::valueOf).mapToInt(Integer::valueOf).toArray();
        int maxLength4 = lcr011.findMaxLength(array);
        System.out.println(maxLength4);
    }
}
