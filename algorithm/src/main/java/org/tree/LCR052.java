package org.tree;

import java.util.Stack;

public class LCR052 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode increasingBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode head = null;
        TreeNode next = null;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            if (!stack.isEmpty()) {
                TreeNode pop = stack.pop();
                pop.left = null;
                if (head == null) {
                    head = pop;
                    next = head;
                } else {
                    next.right = pop;
                    next = next.right;
                }

                root = pop.right;
                pop.right = null;
            }
        }
        return head;
    }
}
