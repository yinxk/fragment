package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/generate-parentheses/
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * <p>
 * 有效括号组合需满足：左括号必须以正确的顺序闭合。
 * <p>
 * 1 <= n <= 8
 */
public class A22GenerateParentheses {

    public static void main(String[] args) {

        A22GenerateParentheses a22GenerateParentheses = new A22GenerateParentheses();
        for (int i = 1; i <= 8; i++) {
            System.out.println(a22GenerateParentheses.generateParenthesis(i));
            System.out.println("===============");
        }
    }

    /**
     * 这里自己想了很久, 想过使用动态规划的思想, 但是没有完全离清 n n-1之间递推的关联情况
     */
    public List<String> generateParenthesis(int n) {
        Map<Integer, List<String>> map = new HashMap<>();
        map.put(0, Collections.singletonList(""));
        map.put(1, Collections.singletonList("()"));
        for (int i = 2; i <= n; i++) {
            List<String> temp = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                List<String> strList1 = map.get(j);
                List<String> strList2 = map.get(i - 1 - j);
                for (String s1 : strList1) {
                    for (String s2 : strList2) {
                        String el = "(" + s1 + ")" + s2;
                        temp.add(el);
                    }
                }
            }
            map.put(i, temp);
        }
        return map.get(n);
    }
}
