package nowcoder.od;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class A33 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> data = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            if (s == null || s.length() == 0) {
                break;
            }
            data.add(s);
        }

        for (String item : data) {
            if (item.contains(".")) {
                System.out.println(encode(item));
            } else {
                System.out.println(decode(item));
            }
        }

    }

    private static long encode(String item) {
        String[] ip = item.split("\\.");
        long res = 0;
        for (String s : ip) {
            res <<= 8;
            res = res | Integer.parseInt(s);
        }
        return res;
    }

    private static String decode(String item) {
        long ip = Long.parseLong(item);
        int mask = (1 << 8) - 1;
        Stack<Integer> stack = new Stack<>();
        while (ip > 0) {
            stack.push((int) (ip & mask));
            ip = ip >>> 8;
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.append(".");
            res.append(stack.pop());
        }
        res.deleteCharAt(0);
        return res.toString();
    }
}
