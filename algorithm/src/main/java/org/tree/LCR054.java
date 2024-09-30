package org.tree;

import java.util.Stack;

public class LCR054 {

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

    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode head = root;

        Stack<TreeNode> stack = new Stack<>();
        int sum = 0;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.right;
            }
            if (!stack.isEmpty()) {
                TreeNode pop = stack.pop();
                sum += pop.val;
                pop.val = sum;
                root = pop.left;
            }
        }
        return head;
    }


}
