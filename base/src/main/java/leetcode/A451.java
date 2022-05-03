package leetcode;

import java.util.HashMap;
import java.util.Map;

public class A451 {

    public static void main(String[] args) {
        A451 obj = new A451();
        System.out.println(obj.frequencySort("tree"));
        System.out.println(obj.frequencySort("cccaaa"));
        System.out.println(obj.frequencySort("Aabb"));
    }

    public String frequencySort(String s) {
        Map<Character, Integer> char2CountMap = new HashMap<>();
        int maxCount = 0;
        for (char ch : s.toCharArray()) {
            int count = char2CountMap.getOrDefault(ch, 0) + 1;
            char2CountMap.put(ch, count);
            maxCount = Math.max(maxCount, count);
        }

        StringBuilder[] buckets = new StringBuilder[maxCount + 1];

        for (Map.Entry<Character, Integer> char2Count : char2CountMap.entrySet()) {
            if (buckets[char2Count.getValue()] == null) {
                buckets[char2Count.getValue()] = new StringBuilder();
            }
            buckets[char2Count.getValue()].append(char2Count.getKey());
        }
        StringBuilder resBuilder = new StringBuilder();
        for (int i = buckets.length - 1; i > 0; i--) {
            if (buckets[i] == null) {
                continue;
            }
            for (int j = 0; j < buckets[i].length(); j++) {
                for (int k = 0; k < i; k++) {
                    resBuilder.append(buckets[i].charAt(j));
                }
            }
        }
        return resBuilder.toString();
    }
}
