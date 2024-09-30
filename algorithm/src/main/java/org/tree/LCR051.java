package org.tree;

public class LCR051 {

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


    private int maxSize = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        pathSum(root);
        return maxSize;
    }


    private int pathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int v1 = pathSum(root.left);
        int v2 = pathSum(root.right);

        int pathSum = root.val;
        if (v1 > 0) {
            pathSum += v1;
        }
        if (v2 > 0) {
            pathSum += v2;
        }
        maxSize = Math.max(maxSize, pathSum);

        return root.val + Math.max(0,Math.max(v1, v2));
    }

    public static void main(String[] args) {

        //[9,6,-3,null,null,-6,2,null,null,2,null,-6,-6,-6]
        TreeNode t1 = new TreeNode(9);
        TreeNode t2 = new TreeNode(6);
        TreeNode t3 = new TreeNode(-3);
        t1.left = t2;
        t1.right = t3;

        TreeNode t4 = new TreeNode(-6);
        TreeNode t5 = new TreeNode(2);
        t3.left = t4;
        t3.right = t5;


        TreeNode t6 = new TreeNode(2);
        t5.left = t6;

        TreeNode t7 = new TreeNode(-6);
        TreeNode t8 = new TreeNode(-6);
        t6.left = t7;
        t6.right = t8;
        t7.left = new TreeNode(-6);


        LCR051 lcr051 = new LCR051();
        int pathSum = lcr051.maxPathSum(t1);
        System.out.println(pathSum);
    }
}
