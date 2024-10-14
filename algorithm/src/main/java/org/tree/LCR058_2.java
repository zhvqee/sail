package org.tree;

import java.util.TreeSet;

public class LCR058_2 {

    public static class MyCalendar {


        private TreeSet<int[]> tree = new TreeSet<>((a, b) -> a[0] - b[0]);

        public MyCalendar() {

        }

        public boolean book(int start, int end) {
            if (tree.isEmpty()) {
                tree.add(new int[]{start, end});
                return true;
            }
            int[] tmp = {start, 0};
            int[] right = tree.ceiling(tmp);
            if (right == null) {
                int[] lower = tree.lower(tmp);
                if (lower == null || lower[1] <= start) {
                    tree.add(new int[]{start, end});
                    return true;
                }
            } else {
                int[] lower = tree.lower(tmp);
                if (lower == null) {
                    if (end <= right[0]) {
                        tree.add(new int[]{start, end});
                        return true;
                    }
                } else {
                    if (lower[1] <= start && end <= right[0]) {
                        tree.add(new int[]{start, end});
                        return true;
                    }
                }
            }
            return false;
        }


    }

    public static void main(String[] args) {
        MyCalendar myCalendar = new MyCalendar();
        //输入:
        //["MyCalendar","book","book","book"]
        //[[],[10,20],[15,25],[20,30]]
        //输出: [null,true,false,true]
       /* System.out.println(myCalendar.book(10, 20));
        System.out.println(myCalendar.book(15, 25));
        System.out.println(myCalendar.book(20, 30));*/
        System.out.println("===========");
        //[[],[47,50],[33,41],[39,45],[33,42],[25,32],[26,35],[19,25],[3,8],[8,13],[18,27]]
        //[true,true,false,false,true,false,false,true,true,false] err
        //[true,true,false,false,true,false,true,true,true,false]


        int[][] arr = {
                {20, 29}, {13, 22}, {44, 50}, {1, 7}, {2, 10},
                {14, 20}, {19, 25}, {36, 42}, {45, 50}, {47, 50},
                {39, 45}, {44, 50}, {16, 25}, {45, 50}, {45, 50}, {12, 20},
                {21, 29}, {11, 20}, {12, 17}, {34, 40}, {10, 18}, {38, 44},
                {23, 32}, {38, 44}, {15, 20}, {27, 33}, {34, 42}, {44, 50},
                {35, 40}, {24, 31}
        };
        for (int[] ints : arr) {
            System.out.print(myCalendar.book(ints[0], ints[1]) + ",");
        }

        //[true,false,true,true,false,true,false,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false]
        //[true,false,true,true,false,true,false,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,
    }


}
