package org.tries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

            public List<Node> getChilds() {
                if (child == null) {
                    return new ArrayList<>();
                }
                return new ArrayList<>(child.values());
            }
        }

        private Node root;

        public MagicDictionary() {
            root = new Node();
        }

        public void buildDict(String[] dictionary) {
            for (String dic : dictionary) {
                insert(dic);
            }
        }

        private void insert(String word) {
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

        public boolean search(String searchWord) {
            return search(root, searchWord, 0, 0);
        }

        private boolean search(Node cur, String searchWord, int pos, int modifyCount) {
            int len = searchWord.length();
            if (pos == len && modifyCount == 1) {
                return  cur.word;
            }
            if (pos == len) {
                return false;
            }
            char ch = searchWord.charAt(pos);
            if(cur.isInChild(ch)){
                if(search(cur.getNext(ch),searchWord,pos+1,modifyCount)){
                    return true;
                }
            }
            Map<Character, Node> child = cur.child;
            if (child == null || child.size() == 0) {
                return false;
            }
            Set<Character> chs = child.keySet();
            for (char character : chs) {
                if (character != ch) {
                    if(search(child.get(character),searchWord,pos+1,modifyCount+1)){
                        return true;
                    }
                }
            }
            return false;
        }
    }

    //[[], [["hello","leetcode","hella"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
    public static void main(String[] args) {
        MagicDictionary dictionary = new MagicDictionary();
        dictionary.buildDict(new String[]{"hello", "leetcode", "hella"});
        boolean hello1 = dictionary.search("hello");
        System.out.println(hello1);
        boolean hello2 = dictionary.search("hhllo");
        System.out.println(hello2);
        boolean hello3 = dictionary.search("hell");
        System.out.println(hello3);
        boolean hello4 = dictionary.search("leetcoded");
        System.out.println(hello4);
    }
}
