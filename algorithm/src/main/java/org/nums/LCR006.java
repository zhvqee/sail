package org.nums;

public class LCR006 {
    public static int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;
        int sum = 0;
        do {
            sum = numbers[i] + numbers[j];
            if (sum == target) {
                return new int[]{i, j};
            }
            if (sum > target) {
                j = j - 1;
            } else {
                i = i + 1;
            }
        } while (i < j);
        return new int[0];
    }

    public static void main(String[] args) {
        int[] ints1 = twoSum(new int[]{1, 2, 4, 6, 10}, 8);
        int[] ints2 = twoSum(new int[]{2, 3, 4}, 6);
        int[] ints3 = twoSum(new int[]{-1, 0}, -1);
        print(ints1);
        print(ints2);
        print(ints3);

    }

    public static void print(int[] nums) {
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
