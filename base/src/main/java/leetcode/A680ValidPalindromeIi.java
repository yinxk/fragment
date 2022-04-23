package leetcode;

public class A680ValidPalindromeIi {

    public static void main(String[] args) {
        A680ValidPalindromeIi obj = new A680ValidPalindromeIi();
        System.out.println(obj.validPalindrome("aba"));
        System.out.println(obj.validPalindrome("abca"));
        System.out.println(obj.validPalindrome("abc"));
        System.out.println(obj.validPalindrome("aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga"));
    }

    /**
     * 双指针
     */
    public boolean validPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        int len = s.length();
        int left = 0;
        int right = len - 1;
        while (left <= right) {
            if (s.charAt(left) == s.charAt(right)) {
                ++left;
                --right;
            } else {
                return validPalindrome(s, left + 1, right) || validPalindrome(s, left, right - 1);
            }
        }
        return true;
    }

    public boolean validPalindrome(String s, int left, int right) {
        while (left <= right) {
            if (s.charAt(left) == s.charAt(right)) {
                ++left;
                --right;
            } else {
                return false;
            }
        }
        return true;
    }
}
