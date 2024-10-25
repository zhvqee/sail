package org.tries;

import java.util.*;

public class LCR064 {

    public static class MagicDictionary {

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
            public  List<Node> getChild(){
                if(child==null){
                    return  new ArrayList<>();
                }
                return new ArrayList<>(child.values());
            }
        }

        private Node root;

        /**
         * Initialize your data structure here.
         */
        public MagicDictionary() {
            root = new Node();
        }

        public void buildDict(String[] dictionary) {
            for (String s : dictionary) {
                insert(s);
            }
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
                    List<Node> values = cur.getChild();
                    if (values != null) {
                        for (Node value : values) {
                            cur = value;
                            if (searchAfter(cur, i + 1, word)) {
                                return true;
                            }
                        }
                        return false;
                    }
                }
            }
            return false;
        }

        private boolean searchAfter(Node cur, int i, String word) {
            int len = word.length();
            for (; i < len; i++) {
                char ch = word.charAt(i);
                if (cur.isInChild(ch)) {
                    cur = cur.getNext(ch);
                } else {
                    return false;
                }

            }
            return cur.word;
        }
    }


    public static void main(String[] args) {
        MagicDictionary magicDictionary = new MagicDictionary();
        magicDictionary.buildDict(new String[]{"hello","hallo","leetcode"});

        //inputs = [[], [["hello", "leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
        //输出
        //[null, null, false, true, false, false]
        boolean hello = magicDictionary.search("hello");
        boolean hello2 = magicDictionary.search("hhllo");
        boolean hello3 = magicDictionary.search("hell");
        boolean hello4 = magicDictionary.search("leetcoded");
        System.out.println();
    }
}
