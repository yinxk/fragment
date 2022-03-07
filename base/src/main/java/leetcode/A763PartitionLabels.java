package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/partition-labels/
 */
public class A763PartitionLabels {

    public static void main(String[] args) {
        A763PartitionLabels obj = new A763PartitionLabels();
        System.out.println(obj.partitionLabels("ababcbacadefegdehijhklij"));
    }

    /**
     * 看了题解, 都是贪心思想, 但更加简洁, 存储空间也更少
     * @param s -
     * @return -
     */
    public List<Integer> partitionLabels(String s) {
        if (s == null || s.length() == 0) {
            return Collections.emptyList();
        }
        //  26个小写字母对应的结束位置
        int[] last = new int[26];
        int length = s.length();
        for (int i = 0; i < length; i++) {
            last[s.charAt(i) - 'a'] = i;
        }
        List<Integer> partition = new ArrayList<>();
        int start = 0, end = 0;
        for (int i = 0; i < length; i++) {
            end = Math.max(end, last[s.charAt(i) - 'a']);
            // 正好扫描到「已扫描的字符的最远位置」，到达切割点
            if (i == end) {
                partition.add(end - start + 1);
                // 更新，下一个待切割的字符串的起始位置
                start = end + 1;
            }
        }
        return partition;
    }

    /**
     * (同一字母最多出现在一个片段中)
     * 1. 先计算出每个字符的起始位置和结束位置
     * 2. 转换为集合问题
     * 3. 贪心策略, 交集问题
     *
     * @param s -
     * @return -
     */
    public List<Integer> partitionLabels1(String s) {
        if (s == null || s.length() ==0) {
            return Collections.emptyList();
        }
        char[] chars = s.toCharArray();
        List<Integer> ans = new ArrayList<>();
        // 26个小写字母对应的起始位置和结束位置的数组
        int[][] cIndex = new int[26][];
        // 初始化
        for (int i = 0; i < cIndex.length; i++) {
            cIndex[i] = new int[]{-1, -1};
        }
        int eleInd;
        char ele;
        for (int i = 0; i < chars.length; i++) {
            ele = chars[i];
            eleInd = ele - 'a';
            cIndex[eleInd][0] = cIndex[eleInd][0] < 0 ? i : Math.min(cIndex[eleInd][0], i);
            // 这里写的太复杂而且没有必要了, 结束位置直接每次赋值就可以了
            cIndex[eleInd][1] = cIndex[eleInd][1] < 0 ? i : Math.max(cIndex[eleInd][1], i);
        }
        Arrays.sort(cIndex, 0, cIndex.length, Comparator.comparingInt(o -> o[0]));
        int[] pre = null;
        for (int[] index : cIndex) {
            if (index[1] < 0) {
                continue;
            }
            if (pre == null) {
                pre = index;
                continue;
            }
            // 相交
            if (pre[1] >= index[0]) {
                pre[0] = Math.min(pre[0], index[0]);
                pre[1] = Math.max(pre[1], index[1]);
            }
            // 当不相交时
            else {
                ans.add(pre[1] - pre[0] + 1);
                pre = index;
            }
        }
        if (pre != null) {
            // 结束
            ans.add(pre[1] - pre[0] + 1);
        }
        return ans;
    }
}
