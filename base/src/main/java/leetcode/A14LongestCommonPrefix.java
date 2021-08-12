package leetcode;

public class A14LongestCommonPrefix {


    public static void main(String[] args) {


        A14LongestCommonPrefix a14LongestCommonPrefix = new A14LongestCommonPrefix();
        System.out.println(a14LongestCommonPrefix.longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        System.out.println(a14LongestCommonPrefix.longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
    }

    public String longestCommonPrefix(String[] strs) {

        // 1 <= strs.length <= 200
        // 0 <= strs[i].length <= 200
        // strs[i] 仅由小写英文字母组成
        int minLen = 1000;
        for (String str : strs) {
            minLen = Math.min(minLen, str.length());
        }
        int i = 0;
        for (; i < minLen; i++) {
            char compare = strs[0].charAt(i);
            boolean compared = true;
            for (int j = 1; j < strs.length; j++) {
                char c = strs[j].charAt(i);
                if (c != compare) {
                    compared = false;
                    break;
                }
            }
            if (!compared) {
                break;
            }
        }

        return strs[0].substring(0, i);
    }
}
