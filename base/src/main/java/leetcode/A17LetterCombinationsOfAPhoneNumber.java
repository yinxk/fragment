package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 */
public class A17LetterCombinationsOfAPhoneNumber {

    public static void main(String[] args) {
        A17LetterCombinationsOfAPhoneNumber phoneNumber = new A17LetterCombinationsOfAPhoneNumber();
        System.out.println(phoneNumber.letterCombinations("23"));
    }

    /**
     * 第二步做法
     */
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return Collections.emptyList();
        }


        return null;
    }



    /**
     * 第一步做法, 直接遍历累计结果完事
     */
    public List<String> letterCombinations1(String digits) {
        if (digits == null || digits.length() == 0) {
            return Collections.emptyList();
        }
        List<String> res = new ArrayList<>();
        char[] digitsChars = digits.toCharArray();
        int len = digitsChars.length;
        for (int i = 0; i < len; i++) {
            if (i == 0) {
                res.addAll(NUMBER_2_CHARS.get(digitsChars[i]));
            } else {
                List<String> temp = new ArrayList<>();
                for (String re : res) {
                    for (String s : NUMBER_2_CHARS.get(digitsChars[i])) {
                        temp.add(re + s);
                    }
                }
                res = temp;
            }
            
        }
        return res;
    }

    private static final Map<Character, List<String>> NUMBER_2_CHARS = new HashMap<>();

    static {
        NUMBER_2_CHARS.put('2', Arrays.asList("a", "b", "c"));
        NUMBER_2_CHARS.put('3', Arrays.asList("d", "e", "f"));
        NUMBER_2_CHARS.put('4', Arrays.asList("g", "h", "i"));
        NUMBER_2_CHARS.put('5', Arrays.asList("j", "k", "l"));
        NUMBER_2_CHARS.put('6', Arrays.asList("m", "n", "o"));
        NUMBER_2_CHARS.put('7', Arrays.asList("p", "q", "r", "s"));
        NUMBER_2_CHARS.put('8', Arrays.asList("t", "u", "v"));
        NUMBER_2_CHARS.put('9', Arrays.asList("w", "x", "y", "z"));
    }


}
