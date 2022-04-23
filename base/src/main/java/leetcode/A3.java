package leetcode;

public class A3 {

    public static void main(String[] args) {
        A3 obj = new A3();
        System.out.println(obj.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(obj.lengthOfLongestSubstring("bbbbb"));
        System.out.println(obj.lengthOfLongestSubstring("pwwkew"));
        System.out.println(obj.lengthOfLongestSubstring("au"));
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int max = 1;
        int left = 0;
        int right = 1;
        int[] count = new int[128];
        count[s.charAt(0)]++;
        while (right < s.length()) {
            char it = s.charAt(right);
            count[it]++;
            while (count[it] == 2) {
                max = Math.max(max, right - left);
                count[s.charAt(left)]--;
                left++;
            }
            right++;
        }
        max = Math.max(max, right - left);
        return max;
    }


}
