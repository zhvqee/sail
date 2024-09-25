package org.hash;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

public class LCR030 {

    //insert(val)：当元素 val 不存在时返回 true ，并向集合中插入该项，否则返回 false 。
    //remove(val)：当元素 val 存在时返回 true ，并从集合中移除该项，否则返回 false 。
    //getRandom：随机返回现有集合中的一项。每个元素应该有 相同的概率 被返回。

    public static class RandomizedSet {
        private List<Integer> array;
        private TreeMap<Integer, Integer> map;

        private Random random = new Random();

        public RandomizedSet() {
            map = new TreeMap<>();
            array = new ArrayList<>();
        }


        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            }
            int size = array.size();
            map.put(val, size);
            array.add(val);
            return true;
        }

        //["RandomizedSet","remove","remove","insert","getRandom","remove","insert"]
        //[[],              [0],     [0],     [0],     [],         [0],     [0]]
        //[null,            false,    false,   true,   0,          true,      false]
        public boolean remove(int val) {
            if (map.containsKey(val)) {
                Integer index = map.get(val);
                Integer integer = array.get(array.size() - 1);
                map.put(integer, index);
                array.set(index, integer);
                array.remove(array.size() - 1);
                map.remove(val);
                return true;
            }
            return false;
        }


        public int getRandom() {
            int nextInt = random.nextInt(array.size());
            return array.get(nextInt);
        }
    }
}
