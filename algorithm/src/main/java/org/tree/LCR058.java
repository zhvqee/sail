package org.tree;

import java.util.LinkedList;

public class LCR058 {

    public static class MyCalendar {

        class Node {
            public int start;
            public int end;

            public Node(int start, int end) {
                this.start = start;
                this.end = end;
            }

        }

        public LinkedList<Node> result = new LinkedList<>();

        public MyCalendar() {

        }

        public boolean book(int start, int end) {
            int insertIndex = 0;
            Node n2 = new Node(start, end);
            for (int i = 0; i < result.size(); i++) {
                Node n1 = result.get(i);
                if (intersection(n1, n2)) {
                    return false;
                }
                if (i == 0 && n2.end <= n1.end) {
                    break;
                }
                insertIndex = i + 1;
               /* if (i != result.size() - 1) {
                    Node after = result.get(i + 1);
                    if (after.start > n2.end) {
                        insertIndex = i + 1;
                        break;
                    }
                }*/
            }
            result.add(insertIndex, n2);
            return true;
        }


        public boolean intersection(Node n1, Node n2) {
            return !(n2.end <= n1.start || n2.start >= n1.end);
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
        //[true,false,true,true,false,true,false,true,false,false,false,false,false,false,false,false,true,false,false,false,false,false,false,false,false,false,false,false,false,false,
        //[true,false,true,true,false,true,false,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,
    }


}
