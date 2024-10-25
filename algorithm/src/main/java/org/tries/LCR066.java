package org.tries;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LCR066 {
    public static class MapSum {
        class Node {
            private int value;
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

            public Node addChar(Character ch, int value) {
                if (child == null) {
                    child = new HashMap<>();
                }
                Node node = new Node();
                node.value = value;
                child.put(ch, node);
                return node;
            }


        }

        Node root;
        Map<String, Set<Node>> prefixNodeMap;

        public MapSum() {
            root = new Node();
            prefixNodeMap = new HashMap<>();
        }

        public void insert(String key, int val) {
            int len = key.length();
            Node cur = root;
            for (int i = 0; i<len; i++) {
                char ch = key.charAt(i);
                if (cur.isInChild(ch)) {
                    cur = cur.getNext(ch);
                } else {
                    cur = cur.addChar(ch, 0);
                }
            }
            cur.value = val;
            for (int i = 1; i < key.length(); i++) {
                Set<Node> nodes = prefixNodeMap.computeIfAbsent(key.substring(0, i), k -> new HashSet<>());
                nodes.add(cur);
            }
        }

        public int sum(String prefix) {
            Set<Node> orDefault = prefixNodeMap.getOrDefault(prefix, new HashSet<>());
            int cnt = 0;
            for (Node node : orDefault) {
                cnt += node.value;
            }
            return cnt;
        }
    }


    public static void main(String[] args) {
        //[[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
        MapSum mapSum= new MapSum();
        mapSum.insert("apple",3);
        int app = mapSum.sum("app");
        System.out.println(app);

        mapSum.insert("app",2);
        int ap = mapSum.sum("ap");
        System.out.println(ap);


    }
}
