package org.hash;

import java.util.LinkedHashMap;
import java.util.Map;

public class LCR031 {

    class LRUCache extends LinkedHashMap<Integer, Integer> {

        private int capacity;

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }

        public LRUCache(int capacity) {
            super(capacity, 0.75f, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            return get(key);
        }

        public void put(int key, int value) {
            put(key, value);
        }
    }
}
