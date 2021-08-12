package leetcode;

import java.util.HashMap;
import java.util.Map;

public class A13RomanToInteger {


    public static void main(String[] args) {


        A13RomanToInteger a13RomanToInteger = new A13RomanToInteger();
        System.out.println(a13RomanToInteger.romanToInt2("III"));
        System.out.println(a13RomanToInteger.romanToInt2("IV"));
        System.out.println(a13RomanToInteger.romanToInt2("IX"));
        System.out.println(a13RomanToInteger.romanToInt2("LVIII"));
        System.out.println(a13RomanToInteger.romanToInt2("MCMXCIV"));
    }

    /**
     * 看了一眼提示, 从右到左, 忽然发现, 确实更简单;
     * 遍历一遍, 每次取一个字符, 比对与前一个字符对应的数字的大小, 如果 < 上次的值, 减去该值, 否则, 相加
     */
    public int romanToInt2(String s) {
        int sum = 0;
        int lastVal = 0;
        int val;
        for (int i = s.length() - 1; i >= 0; i--) {
            val = charSymbol.get(s.charAt(i));
            if (lastVal > val) {
                sum -= val;
            } else {
                sum += val;
            }
            lastVal = val;
        }
        return sum;
    }

    Map<Character, Integer> charSymbol = new HashMap<>();

    {
        charSymbol.put('I', 1);
        charSymbol.put('V', 5);
        charSymbol.put('X', 10);
        charSymbol.put('L', 50);
        charSymbol.put('C', 100);
        charSymbol.put('D', 500);
        charSymbol.put('M', 1000);
    }

    /**
     * 方法一: 总共存在单字符或者两个字符的情况, 试着进行两种情况的比较, 得到val
     */
    public int romanToInt1(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); ) {
            char ch1 = s.charAt(i);
            Integer oneCharVal = symbol.get("" + ch1);
            if (i + 1 < s.length()) {
                char ch2 = s.charAt(i + 1);
                Integer twoCharVal = symbol.get("" + ch1 + ch2);
                twoCharVal = twoCharVal == null ? 0 : twoCharVal;
                if (oneCharVal < twoCharVal) {
                    sum += twoCharVal;
                    i += 2;
                    continue;
                }
            }
            sum += oneCharVal;
            i += 1;

        }
        return sum;
    }

    private final Map<String, Integer> symbol = new HashMap<>();

    {
        symbol.put("I", 1);
        symbol.put("II", 2);
        symbol.put("III", 3);
        symbol.put("IV", 4);
        symbol.put("V", 5);
        symbol.put("IX", 9);
        symbol.put("X", 10);
        symbol.put("XL", 40);
        symbol.put("L", 50);
        symbol.put("XC", 90);
        symbol.put("C", 100);
        symbol.put("CD", 400);
        symbol.put("D", 500);
        symbol.put("CM", 900);
        symbol.put("M", 1000);
    }

}
