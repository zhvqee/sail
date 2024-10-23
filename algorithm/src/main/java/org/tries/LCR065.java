package org.tries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LCR065 {

    class Node {
        private boolean word;
        Map<Character, Node> child;

        public boolean isInChild(Character ch) {
            if (child == null || !child.containsKey(ch)) {
                return false;
            }
            return true;
        }

        public Node getNext(Character ch) {
            return child.get(ch);
        }

        public Node addChar(Character ch, boolean isWord) {
            if (child == null) {
                child = new HashMap<>();
            }
            Node node = new Node();
            node.word = isWord;
            child.put(ch, node);
            return node;
        }

        public List<Node> getChilds() {
            if (child == null) {
                return new ArrayList<>();
            }
            return new ArrayList<>(child.values());
        }
    }


    private int insert(Node root, String word, int count) {
        int len = word.length();
        Node cur = root;
        boolean isInclude = false;
        for (int i = len - 1; i >= 0; i--) {
            char ch = word.charAt(i);
            if (cur.isInChild(ch)) {
                cur = cur.getNext(ch);
                if (i == 0) {
                    isInclude = true;
                }
            } else {
                cur = cur.addChar(ch, i == 0);
                count++;
            }
        }
        if (!isInclude) {
            count += 1;
        }
        cur.word = true;
        return count;
    }

    public int minimumLengthEncoding(String[] words) {
        Node root = new Node();
        int count = 0;
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.length() - o1.length();
            }
        });
        for (String word : words) {
            count = insert(root, word, count);
        }
        return count;
    }

    //words = ["time", "me", "bell"]
    //一组有效编码为 s = "time#bell#" 和 indices = [0, 2, 5] 。
    public static void main(String[] args) {
        LCR065 lcr065 = new LCR065();
        int minimumLengthEncoding = lcr065.minimumLengthEncoding(new String[]{"time","atime","btime"});
        System.out.println(minimumLengthEncoding);
    }
}
