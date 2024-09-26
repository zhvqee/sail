package org.stack;

import java.util.Stack;

public class LCR036 {
    //输入：tokens = ["4","13","5","/","+"]
    //输出：6
    //解释：该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        for (String s : tokens) {
            switch (s) {
                case "+":
                    int s1 = Integer.parseInt(stack.pop());
                    int s2 = Integer.parseInt(stack.pop());
                    stack.push(s2 + s1 + "");
                    break;
                case "-":
                    s1 = Integer.parseInt(stack.pop());
                    s2 = Integer.parseInt(stack.pop());
                    stack.push(s2 - s1 + "");
                    break;
                case "*":
                    s1 = Integer.parseInt(stack.pop());
                    s2 = Integer.parseInt(stack.pop());
                    stack.push(s2 * s1 + "");
                    break;
                case "/":
                    s1 = Integer.parseInt(stack.pop());
                    s2 = Integer.parseInt(stack.pop());
                    stack.push(s2 / s1 + "");
                    break;
                default:
                    stack.push(s);
            }
        }
        return Integer.parseInt(stack.pop());
    }

    public static void main(String[] args) {
        LCR036 lcr036 = new LCR036();
        int evalRPN = lcr036.evalRPN(new String[]{"4", "13", "5", "/", "+"});
        System.out.println(evalRPN);
    }
}
