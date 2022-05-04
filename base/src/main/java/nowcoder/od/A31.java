package nowcoder.od;

import java.util.Scanner;
import java.util.Stack;

public class A31 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        Stack<Character> stack = new Stack<>();
        StringBuilder res = new StringBuilder();
        boolean flag = true;
        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            if ((ch >='a' && ch <='z') || (ch >='A' && ch <='Z')) {
                stack.push(ch);
                flag = true;
            } else {
                while (flag && !stack.isEmpty()) {
                    res.append(stack.pop());
                }
                flag = false;
                res.append(" ");
            }
        }
        while (!stack.isEmpty()) {
            res.append(stack.pop());
        }
        System.out.println(res.toString());
    }
}
