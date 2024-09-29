package org.tree;

import java.util.LinkedList;

public class LCR047 {

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

    //输入：root = [1,2,3,null,null,4,5]
    //输出：[1,2,3,null,null,4,5]
    public static class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) {
                return "null";
            }
            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            StringBuilder sb = new StringBuilder();
            while (!queue.isEmpty()) {
                TreeNode treeNode = queue.pop();
                if (treeNode != null) {
                    sb.append(treeNode.val + ",");
                    queue.add(treeNode.left);
                    queue.add(treeNode.right);
                } else {
                    sb.append("null" + ",");
                }
            }
            return sb.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.equals("null")) {
                return null;
            }
            String[] split = data.split(",");
            int i = 1;
            TreeNode root = new TreeNode(Integer.parseInt(split[0]));
            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                TreeNode pop = queue.pop();
                if (pop != null) {
                    String left = split[i++];
                    String right = split[i++];

                    if (!"null".equals(left)) {
                        pop.left = new TreeNode(Integer.parseInt(left));
                    }
                    if (!"null".equals(right)) {
                        pop.right = new TreeNode(Integer.parseInt(right));
                    }
                    queue.add(pop.left);
                    queue.add(pop.right);
                }
            }
            return root;
        }
    }

    public static void main(String[] args) {
        Codec codec = new Codec();
        TreeNode deserialize = codec.deserialize("1,2,3,null,null,4,5,null,null,null,null");
        String serialize = codec.serialize(deserialize);
        System.out.println(serialize);
    }
}
