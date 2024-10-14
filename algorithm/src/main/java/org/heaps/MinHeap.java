package org.heaps;

public class MinHeap {
    private int capacity;
    private transient int[] array;
    private int size = 0;

    public MinHeap(int n) {
        this(n, null);
    }

    public MinHeap(int n, int[] array) {
        this.capacity = n;
        this.array = new int[n];
        if (array != null) {
            int len = Math.min(capacity, array.length);
            for (int i = 0; i < len; i++) {
                this.array[i] = array[i];
                size++;
            }
            heapify();
            for (int i = capacity; i < array.length; i++) {
                add(array[i]);
            }
        }
    }

    public int add(int value) {
        //首节点
        if (size < capacity) {
            array[size] = value;
            heapify();
        } else {
            //把最小的剔除
            if (array[0] < value) {
                array[0] = value;
                heapify();
            }
        }
        return array[0];
    }

    private void heapify() {
        int start = 1;
        do {
            int left = start * 2;
            int right = left + 1;

            if (array[start - 1] > array[left - 1]) {
                swap(start - 1, left - 1);
                start = left;
            } else if (right - 1 < size && array[start - 1] > array[right - 1]) {
                swap(start, right - 1);
                start = right;
            }
            start = start / 2;
        } while (start != 0);
    }

    private void swap(int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap(3, new int[]{4, 5, 8, 2});
        System.out.println(minHeap.add(3));
        System.out.println(minHeap.add(5));
        System.out.println(minHeap.add(10));
        System.out.println(minHeap.add(9));
        System.out.println(minHeap.add(4));

        //["KthLargest", "add", "add", "add", "add", "add"]
        //[[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
        //输出：
        //[null, 4, 5, 5, 8, 8]
    }

}
