package org.tries;

import java.util.ArrayList;
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


    }


    private void insert(Node root, String word, Map<Node, Integer> cntMap) {
        int len = word.length();
        Node cur = root;
        for (int i = len - 1; i >= 0; i--) {
            char ch = word.charAt(i);
            if (cur.isInChild(ch)) {
                cur = cur.getNext(ch);
            } else {
                cur = cur.addChar(ch, i == 0);
            }
        }
        cntMap.put(cur, len);

        cur.word = true;
    }

    public int minimumLengthEncoding(String[] words) {
        Node root = new Node();
        Map<Node, Integer> cntMap = new HashMap<>();
        for (String word : words) {
            insert(root, word, cntMap);
        }
        int cnt=0;
        for (Node node : cntMap.keySet()) {
            if(node.child==null || node.child.size()==0){
                cnt+=cntMap.get(node)+1;
            }
        }
        return cnt;

    }


    public static void main(String[] args) {
        LCR065 lcr065 = new LCR065();

        int minimumLengthEncoding = lcr065.minimumLengthEncoding(new String[]{"time", "atime", "btime"});
        System.out.println(minimumLengthEncoding);
    }
}
