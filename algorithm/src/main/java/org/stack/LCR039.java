package org.stack;

import java.util.Stack;

public class LCR039 {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int[] left = new int[heights.length];
        for (int i = 0; i < heights.length; i++) {
            if (stack.isEmpty() || heights[stack.peek()] < heights[i]) {
                left[i] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(i);
            } else {
                while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                    stack.pop();
                }
                left[i] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(i);
            }
        }

        stack.clear();
        int[] right = new int[heights.length];
        for (int i = heights.length - 1; i >= 0; i--) {
            if (stack.isEmpty() || heights[stack.peek()] < heights[i]) {
                right[i] = stack.isEmpty() ? heights.length : stack.peek();
                stack.push(i);
            } else {
                while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                    stack.pop();
                }
                right[i] = stack.isEmpty() ? heights.length : stack.peek();
                stack.push(i);
            }
        }
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            max = Math.max(max, (right[i] - left[i] - 1) * heights[i]);
        }

        return max;
    }


}
