package leetcode;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/valid-parentheses/
 */
public class A20ValidParentheses {
    public static void main(String[] args) {
        A20ValidParentheses a20ValidParentheses = new A20ValidParentheses();
        System.out.println(a20ValidParentheses.isValid("()[]{}"));
        System.out.println(a20ValidParentheses.isValid("()"));
        System.out.println(a20ValidParentheses.isValid("(]"));
        System.out.println(a20ValidParentheses.isValid("([)]"));
        System.out.println(a20ValidParentheses.isValid("{[]}"));

    }


    /**
     * 第一步, 利用栈进行匹配
     */
    public boolean isValid(String s) {
        int len = s.length();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            }
            if (ch == ')' || ch == '}' || ch == ']') {
                if (stack.isEmpty()) {
                    return false;
                }
                Character pop = stack.pop();
                if (ch == ')' && pop != '(') {
                    return false;
                }
                if (ch == ']' && pop != '[') {
                    return false;
                }
                if (ch == '}' && pop != '{') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
