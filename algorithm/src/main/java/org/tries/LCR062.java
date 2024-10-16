package org.tries;

import java.util.HashMap;
import java.util.Map;

public class LCR062 {

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
        public boolean search(String word) {
            int len = word.length();
            Node cur = root;
            for (int i = 0; i < len; i++) {
                char ch = word.charAt(i);
                if (cur.isInChild(ch)) {
                    cur = cur.getNext(ch);
                } else {
                    return false;
                }
            }
            return cur.word;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            int len = prefix.length();
            Node cur = root;
            for (int i = 0; i < len; i++) {
                char ch = prefix.charAt(i);
                if (cur.isInChild(ch)) {
                    cur = cur.getNext(ch);
                } else {
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        //["Trie","insert","search","search","startsWith","insert","search"]
        //[[],  ["apple"],["apple"],["app"],["app"],["app"],["app"]]
        Trie trie = new Trie();
        trie.insert("apple");
        boolean apple = trie.search("apple");
        boolean app = trie.search("app");
        boolean app1 = trie.startsWith("app");
        trie.insert("app");
        boolean app3 = trie.search("app");
    }
}
