package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/string-to-integer-atoi/
 */
public class A8StringTIntegerAtoi {

    public static void main(String[] args) {

        A8StringTIntegerAtoi atoi = new A8StringTIntegerAtoi();
        System.out.println(atoi.myAtoi2("42"));
        System.out.println(atoi.myAtoi2("+1"));
        System.out.println(atoi.myAtoi2("   -42"));
        System.out.println(atoi.myAtoi2("4193 with words"));
        System.out.println(atoi.myAtoi2("words and 987"));
        System.out.println(atoi.myAtoi2("-91283472332"));

    }


    public int myAtoi1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        boolean isPreSpace = true;
        boolean ne = false;
        long res = 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (isPreSpace && c == ' ') {
                continue;
            }
            if (isPreSpace && c == '-') {
                isPreSpace = false;
                ne = true;
                continue;
            }
            if (isPreSpace && c == '+') {
                isPreSpace = false;
                continue;
            }
            if (48 <= c && c <= 57) {
                isPreSpace = false;
                if (ne) {
                    res = res * 10 - ((int) c - 48);
                } else {
                    res = res * 10 + ((int) c - 48);
                }
                if (res > Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                } else if (res < Integer.MIN_VALUE) {
                    return Integer.MIN_VALUE;
                }
            } else {
                break;
            }
        }
        return (int) res;
    }


    public int myAtoi2(String s){
        Automaton automaton = new Automaton();
        int length = s.length();
        for (int i = 0; i < length; ++i) {
            automaton.get(s.charAt(i));
        }
        return (int) (automaton.sign * automaton.ans);
    }

    /**
     * 来自题解
     * 字符串处理的题目往往涉及复杂的流程以及条件情况，如果直接上手写程序，一不小心就会写出极其臃肿的代码。
     *
     * 因此，为了有条理地分析每个输入字符的处理方法，我们可以使用自动机这个概念：
     *
     * 我们的程序在每个时刻有一个状态 s，每次从序列中输入一个字符 c，并根据字符 c 转移到下一个状态 s'。这样，我们只需要建立一个覆盖所有情况的从 s 与 c 映射到 s' 的表格即可解决题目中的问题。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/string-to-integer-atoi/solution/zi-fu-chuan-zhuan-huan-zheng-shu-atoi-by-leetcode-/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    static class Automaton {
        public int sign = 1;
        public long ans = 0;
        private String state = "start";
        private Map<String, String[]> table = new HashMap<String, String[]>() {{
            put("start", new String[]{"start", "signed", "in_number", "end"});
            put("signed", new String[]{"end", "end", "in_number", "end"});
            put("in_number", new String[]{"end", "end", "in_number", "end"});
            put("end", new String[]{"end", "end", "end", "end"});
        }};

        public void get(char c) {
            state = table.get(state)[get_col(c)];
            if ("in_number".equals(state)) {
                ans = ans * 10 + c - '0';
                ans = sign == 1 ? Math.min(ans, (long) Integer.MAX_VALUE) : Math.min(ans, -(long) Integer.MIN_VALUE);
            } else if ("signed".equals(state)) {
                sign = c == '+' ? 1 : -1;
            }
        }

        private int get_col(char c) {
            if (c == ' ') {
                return 0;
            }
            if (c == '+' || c == '-') {
                return 1;
            }
            if (Character.isDigit(c)) {
                return 2;
            }
            return 3;
        }
    }


}
