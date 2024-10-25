package org.tries;

import java.util.HashMap;
import java.util.Map;

public  class MapSum {
    class TrieNode {
        int val = 0;
        TrieNode[] next = new TrieNode[26];
    }

    TrieNode root;
    Map<String, Integer> map;

    public MapSum() {
        root = new TrieNode();
        map = new HashMap<>();
    }

    public void insert(String key, int val) {
        int delta = val - map.getOrDefault(key, 0);
        map.put(key, val);
        TrieNode node = root;
        for (char c : key.toCharArray()) {
            if (node.next[c - 'a'] == null) {
                node.next[c - 'a'] = new TrieNode();
            }
            node = node.next[c - 'a'];
            node.val += delta;
        }
    }

    public int sum(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            if (node.next[c - 'a'] == null) {
                return 0;
            }
            node = node.next[c - 'a'];
        }
        return node.val;
    }

    public static void main(String[] args) {
         MapSum mapSum= new  MapSum();
        mapSum.insert("apple",3);
        int app = mapSum.sum("app");
        System.out.println(app);

        mapSum.insert("app",2);
        int ap = mapSum.sum("ap");
        mapSum.insert("apple",2);
        System.out.println(ap);
    }
}

