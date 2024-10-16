package org.tries;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LCR063 {

    public static class Trie {

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

        private Node root;

        /**
         * Initialize your data structure here.
         */
        public Trie() {
            root = new Node();
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            int len = word.length();
            Node cur = root;
            for (int i = 0; i < len; i++) {
                char ch = word.charAt(i);
                if (cur.isInChild(ch)) {
                    cur = cur.getNext(ch);
                } else {
                    cur = cur.addChar(ch, i == len - 1);
                }
            }
            cur.word = true;
        }

        /**
         * Returns if the word is in the trie.
         */
        public String searchPrefix(String word) {
            int len = word.length();
            Node cur = root;
            String sb = "";
            for (int i = 0; i < len; i++) {
                char ch = word.charAt(i);
                if (cur.isInChild(ch)) {
                    sb += ch;
                    cur = cur.getNext(ch);
                    if (cur.word) {
                        break;
                    }
                } else {
                    break;
                }
            }
            return sb;
        }
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        Trie trie = new Trie();
        for (String s : dictionary) {
            trie.insert(s);
        }
        StringBuilder sb = new StringBuilder();
        String[] split = sentence.split(" ");
        for (int i=0;i<split.length;i++) {
            String s1 = trie.searchPrefix(split[i]);
            if(s1.equals("")){
                s1=split[i];
            }
            sb.append(s1);
            if(i!=split.length-1){
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LCR063 lcr063 = new LCR063();
        String replaceWords = lcr063.replaceWords(Arrays.asList("cat", "bat", "rat"), "the cattle was rattled by the battery");
        System.out.println(replaceWords);
    }
}
