package org.queue;

import java.util.LinkedList;

public class LCR043 {

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

    public static class CBTInserter {
        TreeNode root = null;

        public CBTInserter(TreeNode root) {
            this.root = root;
        }

        public int insert(int v) {
            TreeNode insert = new TreeNode(v);
            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            TreeNode parent = null;
            int level = 1;
            l:
            while (!queue.isEmpty()) {
                int size = 2 << (level - 1);
                int i = 0;
                for (; i < size; i++) {

                    parent = queue.pop();
                    if (parent.left == null || parent.right == null) {
                        break l;
                    }
                    queue.add(parent.left);
                    queue.add(parent.right);
                }
                level++;
            }
            if (parent.left == null) {
                parent.left = insert;
            } else {
                parent.right = insert;
            }
            return parent.val;
        }

        public TreeNode get_root() {
            return root;
        }
    }

    public static void main(String[] args) {
        //[[[1,2,3,4,5,6]],[7],[8],[]]
        TreeNode treeNode1 = new TreeNode(1);

        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        TreeNode treeNode6 = new TreeNode(6);
        treeNode3.left = treeNode6;


        CBTInserter cbtInserter = new CBTInserter(treeNode1);
        cbtInserter.insert(7);
        cbtInserter.insert(8);
    }

}
