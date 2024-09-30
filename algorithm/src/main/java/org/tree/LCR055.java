package org.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LCR055 {

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

    class BSTIterator {

        private List<TreeNode> queue = new ArrayList<>();
        private int next = 0;

        public BSTIterator(TreeNode root) {
            convertBST(root);
        }

        public int next() {
            return queue.get(next++).val;
        }

        public boolean hasNext() {
            return next < queue.size();
        }

        private TreeNode convertBST(TreeNode root) {
            if (root == null) {
                return null;
            }
            TreeNode head = root;

            Stack<TreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || root != null) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                if (!stack.isEmpty()) {
                    TreeNode pop = stack.pop();
                    queue.add(pop);
                    root = pop.right;
                }
            }
            return head;
        }
    }


}
