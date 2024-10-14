package org.tree;

import java.util.HashMap;
import java.util.Map;

public class LCR056 {

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

    private Boolean isFind = false;

    public boolean findTarget(TreeNode root, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        orderSave(root, map, k);
        return isFind;
    }

    private void orderSave(TreeNode root, Map<Integer, Integer> map, int k) {
        if (root == null || isFind) {
            return;
        }
        orderSave(root.left, map, k);
        Integer integer = map.get(k - root.val);
        if (integer != null) {
            isFind = true;
            return;
        }
        map.put(root.val, root.val);
        orderSave(root.right, map, k);
    }

}
