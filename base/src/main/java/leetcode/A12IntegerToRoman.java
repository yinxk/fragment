package leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
https://leetcode-cn.com/problems/integer-to-roman/
 */
public class A12IntegerToRoman {


    public static void main(String[] args) {
        A12IntegerToRoman a12IntegerToRoman = new A12IntegerToRoman();
        System.out.println(a12IntegerToRoman.intToRoman(3));
        System.out.println(a12IntegerToRoman.intToRoman(4));
        System.out.println(a12IntegerToRoman.intToRoman(9));
        System.out.println(a12IntegerToRoman.intToRoman(58));
        System.out.println(a12IntegerToRoman.intToRoman(1994));
    }

    String[] thousands = {"", "M", "MM", "MMM"};
    String[] hundreds  = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    String[] tens      = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    String[] ones      = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

    /**
     * 查看解答, 发现一种硬编码方法, 将个位, 十位, 百位, 千位 分别 罗列出来
     */
    public String intToRoman(int num) {
        StringBuffer roman = new StringBuffer();
        roman.append(thousands[num / 1000]);
        roman.append(hundreds[num % 1000 / 100]);
        roman.append(tens[num % 100 / 10]);
        roman.append(ones[num % 10]);
        return roman.toString();
    }

    /**
     * 第二种方法, 只是将第一种的方法一点儿优化
     */
    public String intToRoman2(int num) {
        String[] keys = {"I", "II", "III", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
        int[] vals = {1, 2, 3, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
        StringBuilder sb = new StringBuilder();
        for (int i = vals.length - 1; i >= 0; i--) {
            int x = num / vals[i];
            num = num % vals[i];
            if (x > 0) {
                for (int j = 0; j < x; j++) {
                    sb.append(keys[i]);
                }
            }
        }
        return sb.toString();
    }

    /**
     * 想到的第一种方法, 使用符号表, 并从大到小将数字转换
     */
    public String intToRoman1(int num) {
        if (num < 1 || num > 3999) {
            throw new IllegalArgumentException(String.format("num必须属于区间 [%s, %s]", 1, 3999));
        }
        StringBuilder sb = new StringBuilder();
        Integer key;
        while (num > 0) {
            key = getKey(num);
            if (key == null) {
                throw new RuntimeException("cal fail");
            }
            sb.append(symbol.get(key));
            num -= key;
        }
        return sb.toString();
    }

    private Integer getKey(int num) {
        int lastKey = 4000;
        Integer key;
        for (Integer integer : keys) {
            key = integer;
            if (num < lastKey && num >= key) {
                return key;
            }
            lastKey = key;
        }
        return null;
    }

    private final Map<Integer, String> symbol = new HashMap<>();
    private final List<Integer> keys;

    {
        symbol.put(1, "I");
        symbol.put(2, "II");
        symbol.put(3, "III");
        symbol.put(4, "IV");
        symbol.put(5, "V");
        symbol.put(9, "IX");
        symbol.put(10, "X");
        symbol.put(40, "XL");
        symbol.put(50, "L");
        symbol.put(90, "XC");
        symbol.put(100, "C");
        symbol.put(400, "CD");
        symbol.put(500, "D");
        symbol.put(900, "CM");
        symbol.put(1000, "M");
        keys = new ArrayList<>(symbol.keySet());
        keys.sort(Comparator.comparing(Integer::intValue).reversed());
    }


}
