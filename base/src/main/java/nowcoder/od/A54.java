package nowcoder.od;

import java.util.Scanner;
import java.util.Stack;

public class A54 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        // String s = "3+2*{1+2*[-4/(8-6)+7]}";
        char[] data = s.toCharArray();
        System.out.println(compute(data, 0)[0]);
    }

    private static int[] compute(char[] data, int startIndex) {
        int len = data.length;
        char flag = '+';
        Stack<Integer> numStack = new Stack<>();
        int i = startIndex;
        for (; i < len; i++) {
            int num = 0;
            if (data[i] == '(') {
                int[] num2Index = compute(data, i + 1);
                num = num2Index[0];
                i = num2Index[1];
            }
            while (i < len && Character.isDigit(data[i])) {
                num = num * 10 + data[i] - '0';
                i++;
            }
            switch (flag) {
                case '+':
                    numStack.push(num);
                    break;
                case '-':
                    numStack.push(-num);
                    break;
                case '*':
                    numStack.push(numStack.pop() * num);
                    break;
                case '/':
                    numStack.push(numStack.pop() / num);
                    break;
            }
            if (i >= len) {
                break;
            }
            if (data[i] == ')') {
                i++;
                break;
            }
            flag = data[i];
        }
        int sum = 0;
        while (!numStack.isEmpty()) {
            sum += numStack.pop();
        }
        return new int[]{sum, i};
    }


}
