package org.tree;

public class LCR050 {

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


    private int count;

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        calSum(root, 0, targetSum);
        pathSum(root.left, targetSum);
        pathSum(root.right, targetSum);
        return count;
    }


    private void calSum(TreeNode root, long curValue, int targetSum) {
        if (root == null) {
            return;
        }
        curValue += root.val;

        if (targetSum == curValue) {
            count++;
        }
        calSum(root.left, curValue, targetSum);
        calSum(root.right, curValue, targetSum);

    }

    public static void main(String[] args) {
       // [715827882,715827882,null,715827882,null,1,null,715827882,null,715827882,null,715827882,null]
        System.out.println(Integer.MAX_VALUE);

    }
}
