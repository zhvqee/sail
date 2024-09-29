package org.tree;

import java.util.ArrayList;
import java.util.List;

public class LCR049 {

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


    public int sumNumbers(TreeNode root) {
        List<Integer> path = new ArrayList<>();
        sumNumbers(root, 0, path);
        return path.stream().reduce(0, Integer::sum);
    }

    public void sumNumbers(TreeNode root, int sum, List<Integer> path) {
        if (root == null) {
            return;
        }

        sum = sum * 10 + root.val;
        if (root.left == null && root.right == null) {
            path.add(sum);
            return;
        }
        sumNumbers(root.left, sum, path);
        sumNumbers(root.right, sum, path);
    }

    public static void main(String[] args) {
        //输入：root = [1,2,3]
        //输出：25
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        LCR049 lcr049 = new LCR049();
        int sumNumbers = lcr049.sumNumbers(treeNode1);
        System.out.println(sumNumbers);
    }
}
