package org.stack;

import java.util.Stack;

public class LCR037 {
    //给定一个整数数组 asteroids，表示在同一行的小行星。
//
//对于数组中的每一个元素，其绝对值表示小行星的大小，正负表示小行星的移动方向（正表示向右移动，负表示向左移动）。每一颗小行星以相同的速度移动。
//
//找出碰撞后剩下的所有小行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。
    //输入：asteroids = [5,10,-5]
    //输出：[5,10]
    //解释：10 和 -5 碰撞后只剩下 10 。 5 和 10 永远不会发生碰撞。
    //示例 2：
    //
    //输入：asteroids = [8,-8]
    //输出：[]
    //解释：8 和 -8 碰撞后，两者都发生爆炸。

    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int n : asteroids) {
            if (stack.isEmpty()) {
                stack.push(n);
            } else {
                if ((stack.peek() ^ n) >= 0) { //符号相同
                    stack.push(n);
                } else {
                    // 这里碰撞
                    int tmp = n;
                    boolean need = true;
                    while (!stack.isEmpty() && (stack.peek() ^ tmp) < 0) {
                        Integer pop = stack.pop();
                        if (pop + tmp > 0) {
                            tmp = Math.max(pop, tmp);
                        } else if (pop + tmp == 0) {
                            need = false;
                            break;
                        } else { //
                            tmp = -Math.max(Math.abs(pop), Math.abs(tmp));
                        }
                    }
                    if (need) {
                        stack.push(tmp);
                    }

                }
            }
        }
        if (stack.isEmpty()) {
            return new int[0];
        }
        int[] ret = new int[stack.size()];
        int i = 0;
        while (!stack.isEmpty()) {
            ret[i++] = stack.pop();
        }
        return ret;
    }

    public static void main(String[] args) {
        LCR037 lcr037 = new LCR037();
        int[] ints = lcr037.asteroidCollision(new int[]{-2,-1,1,2});
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }
}
