package leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode-cn.com/problems/minimum-number-of-arrows-to-burst-balloons/
 */
public class A452MinimumNumberOfArrowsToBurstBalloons {


    public static void main(String[] args) {
        A452MinimumNumberOfArrowsToBurstBalloons obj = new A452MinimumNumberOfArrowsToBurstBalloons();

        System.out.println(obj.findMinArrowShots(new int[][]{new int[]{10, 16}, new int[]{2, 8}, new int[]{1, 6}, new int[]{7, 12}}));
        System.out.println(obj.findMinArrowShots(new int[][]{new int[]{1, 2}, new int[]{3, 4}, new int[]{5, 6}, new int[]{7, 8}}));
        System.out.println(obj.findMinArrowShots(new int[][]{new int[]{1, 2}, new int[]{2, 3}, new int[]{3, 4}, new int[]{4, 5}}));
        System.out.println(obj.findMinArrowShots(new int[][]{new int[]{1, 2}}));
        System.out.println(obj.findMinArrowShots(new int[][]{new int[]{2, 3}, new int[]{2, 3}}));

    }


    /**
     * 1. 贪心思想
     * 找交集, 找到一个交集就减少一次
     *
     * @param points -
     * @return -
     */
    public int findMinArrowShots(int[][] points) {
        if (points == null || points.length <= 0) {
            return 0;
        }
        if (points.length == 1) {
            return 1;
        }
        Arrays.sort(points, 0, points.length, Comparator.comparingInt(o -> o[0]));
        int count = points.length;
        int[] point = points[0];
        for (int i = 1; i < points.length; i++) {
            if (point[1] >= points[i][0]) {
                count--;
                point[0] = Math.max(point[0], points[i][0]);
                point[1] = Math.min(point[1], points[i][1]);
            } else {
                point = points[i];
            }
        }
        return count;
    }

}
