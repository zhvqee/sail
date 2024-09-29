package org.queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LCR045 {

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

    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<Integer> result = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode pop = queue.pop();
                if (pop.right != null) {
                    queue.add(pop.right);
                }
                if (pop.left != null) {
                    queue.add(pop.left);
                }
                if (i == 0) {
                    result.add(pop.val);
                }
            }
        }
        return result;
    }

}
