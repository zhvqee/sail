package org.arrays;

import java.io.IOException;

public class LCR013 {

    //二维数组当做一维数组时，处理为，(j+1)*i+j;

    static class NumMatrix {

        private int[][] matrix;

        private int[] pre;

        public NumMatrix(int[][] matrix) {
            this.matrix = matrix;
            pre = new int[matrix.length * matrix[0].length];
            int ps = 0;
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    ps += matrix[i][j];
                    pre[matrix[0].length * i + j] = ps;
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            //(j+1)*i+j;
            int sum = 0;
            for (int rw = row1; rw <= row2; rw++) {
                int sub=0;
                if(matrix[0].length * rw + col1-1>=0){
                    sub=pre[matrix[0].length * rw + col1-1];
                }
                sum += pre[matrix[0].length * rw + col2] - sub;
            }
            return sum;
        }
    }

    public static void main(String[] args) throws IOException {
        NumMatrix numMatrix = new NumMatrix(new int[][]{
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        });
        int i1 = numMatrix.sumRegion(2, 1, 4, 3);// return 8 (红色矩形框的元素总和)
        System.out.println(i1);
        int i2 = numMatrix.sumRegion(1, 1, 2, 2); // return 11 (绿色矩形框的元素总和)
        System.out.println(i2);
        int i3 = numMatrix.sumRegion(1, 2, 2, 4); // return 12 (蓝色矩形框的元素总和)
        System.out.println(i3);

    }
}
