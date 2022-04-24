package leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class A554 {

    public static void main(String[] args) {

    }

    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (List<Integer> widths : wall) {
            int sum = 0;
            for (int i = 0; i < widths.size() - 1; i++) {
                sum += widths.get(i);
                cnt.put(sum, cnt.getOrDefault(sum, 0) +1);
            }
        }
        int maxCnt = 0;
        for (Integer count : cnt.values()) {
            maxCnt = Math.max(maxCnt, count);
        }
        return wall.size() - maxCnt;
    }
}
