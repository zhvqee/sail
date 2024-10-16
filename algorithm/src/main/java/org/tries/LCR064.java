package org.tries;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LCR064 {

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
            int modifyCount = 1;
            for (int i = 0; i < len; i++) {
                char ch = word.charAt(i);
                if (cur.isInChild(ch)) {
                    cur = cur.getNext(ch);
                } else {
                    if (modifyCount == 0) {
                        return false;
                    }
                    Set<Character> characters = cur.child.keySet();
                    if(characters!=null) {
                        modifyCount--;

                    }
                }
            }
            return cur.word && modifyCount==0;
        }
    }


    public static void main(String[] args) {

    }
}
