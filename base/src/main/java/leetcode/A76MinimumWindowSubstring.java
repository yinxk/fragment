package leetcode;

/**
 * https://leetcode-cn.com/problems/minimum-window-substring/
 */
public class A76MinimumWindowSubstring {

    public static void main(String[] args) {
        A76MinimumWindowSubstring obj = new A76MinimumWindowSubstring();
        System.out.println(obj.minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(obj.minWindow("a", "a"));
        System.out.println(obj.minWindow("a", "aa"));
    }

    /**
     * 双指针, 滑动窗口
     * 这里book中的写法
     * <p>
     * 1. 统计T中的每个字符的数量以及是否存在
     * 2. right从0遍历s到结束
     * 3. 用cnt记录窗口中包含的子串包含T中字符的数量
     * 4. 当窗口中包含T中所有字符并且数量足够时
     * 5. 移动l, 尝试子串长度
     * 记录最小size以及最小left
     * 当l指向的字符是T中包含的字符时, 更新cnt, 每个字符数量
     * 6.回到第2步, right继续遍历
     */
    public String minWindow(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0) {
            return "";
        }
        int[] charCounts = new int[128];
        boolean[] flag = new boolean[128];
        // 先统计T中字符情况
        for (int i = 0; i < t.length(); i++) {
            charCounts[t.charAt(i)]++;
            flag[t.charAt(i)] = true;
        }
        int sLen = s.length();
        // 窗口中包含T中的字符的数量
        int cnt = 0;
        int left = 0;
        int right = 0;
        int minLeft = 0;
        int minSize = sLen + 1;
        for (; right < sLen; right++) {
            if (flag[s.charAt(right)]) {
                if (--charCounts[s.charAt(right)] >= 0) {
                    ++cnt;
                }
                // 若目前滑动窗口已包含T中全部字符，
                // 则尝试将l右移，在不影响结果的情况下获得最短子字符串
                while (cnt == t.length()) {
                    // 更新记录最新的 最小size和left索引
                    if (right - left + 1 < minSize) {
                        minLeft = left;
                        minSize = right - left + 1;
                    }
                    // left左移的过程中, 遇到T中包含的字符时
                    if (flag[s.charAt(left)] && ++charCounts[s.charAt(left)] > 0) {
                        --cnt;
                    }
                    ++left;
                }
            }
        }
        return minSize > s.length() ? "" : s.substring(minLeft, minLeft + minSize);
    }


    public String minWindow1(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0) {
            return "";
        }
        // 先统计T中字符情况, 记录存在情况
        int[] needCounts = new int[128];
        boolean[] exits = new boolean[128];
        for (int i = 0; i < t.length(); i++) {
            needCounts[t.charAt(i)]++;
            exits[t.charAt(i)] = true;
        }
        int left = 0, right = 0, minLeft = 0, minSize = s.length() + 1;
        // 记录窗口中包含T中字符的数量
        int cnt = 0;
        for (; right < s.length(); right++) {
            // 当前字符不存在于T中, 跳过
            if (!exits[s.charAt(right)]) {
                continue;
            }
            // 当前字符存在于T中
            if (--needCounts[s.charAt(right)] >= 0) {
                cnt++;
            }
            // 若目前滑动窗口已包含T中全部字符
            // 则尝试将left右移，在不影响结果的情况下获得最短子字符串
            while (cnt == t.length()) {
                // 记录最小size以及left
                if (right - left + 1 < minSize) {
                    minLeft = left;
                    minSize = right - left + 1;
                }
                // 当前left指向字符 被 T包含时, 更新cnt和needCounts
                if (exits[s.charAt(left)] && ++needCounts[s.charAt(left)] > 0) {
                    cnt--;
                }
                left++;
            }

        }
        return minSize > s.length() ? "" : s.substring(minLeft, minLeft + minSize);
    }
}
