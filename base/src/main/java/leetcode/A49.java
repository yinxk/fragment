package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class A49 {
    public static void main(String[] args) {
        A49 obj = new A49();
        System.out.println(obj.groupAnagrams4(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
        System.out.println(obj.groupAnagrams4(new String[]{""}));
        System.out.println(obj.groupAnagrams4(new String[]{"a"}));
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] sChars = s.toCharArray();
            Arrays.sort(sChars);
            String key = new String(sChars);
            List<String> group = map.getOrDefault(key, new ArrayList<>());
            group.add(s);
            map.put(key, group);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 超时
     */
    public List<List<String>> groupAnagrams1(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            res.add(new ArrayList<>());
            return res;
        }
        int len = strs.length;
        boolean[] grouped = new boolean[len];
        for (int i = 0; i < len; i++) {
            if (grouped[i]) {
                continue;
            }
            String each = strs[i];
            grouped[i] = true;
            List<String> group = new ArrayList<>();
            res.add(group);
            group.add(each);
            for (int j = i; j < len; j++) {
                if (!grouped[j]) {
                    if (match(each, strs[j])) {
                        grouped[j] = true;
                        group.add(strs[j]);
                    }
                }
            }
        }
        return res;
    }

    public boolean match(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }
        int len = a.length();
        Map<Character, Integer> aCount = new HashMap<>();
        Map<Character, Integer> bCount = new HashMap<>();
        for (int i = 0; i < len; i++) {
            aCount.put(a.charAt(i), aCount.getOrDefault(a.charAt(i), 0) + 1);
            bCount.put(b.charAt(i), bCount.getOrDefault(b.charAt(i), 0) + 1);
        }
        for (int i = 0; i < len; i++) {
            if (!aCount.get(a.charAt(i)).equals(bCount.get(a.charAt(i)))) {
                return false;
            }
        }
        return true;
    }



    public List<List<String>> groupAnagrams4(String[] strs) {
        Map<String, List<String>> res = new HashMap<>();
        for(String s : strs){
            char[] sChars = s.toCharArray();
            Arrays.sort(sChars);
            String key = new String(sChars);
            res.putIfAbsent(key, new ArrayList<>());
            res.get(key).add(s);
        }
        return new ArrayList<>(res.values());
    }
}
