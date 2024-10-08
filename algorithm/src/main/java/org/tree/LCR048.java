package org.tree;

public class LCR048 {

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

    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null && root.val == 0) {
            return null;
        }
        return postOrder(root);
    }

    private TreeNode postOrder(TreeNode root) {
        if (root == null) {
            return null;
        }
        root.left = postOrder(root.left);
        root.right = postOrder(root.right);

        if (root.left == null && root.right == null && root.val == 0) {
            return null;
        }
        return root;

    }
}
