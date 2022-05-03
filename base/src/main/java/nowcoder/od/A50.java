package nowcoder.od;

import java.util.Scanner;
import java.util.Stack;

public class A50 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        // String s = "3+2*{1+2*[-4/(8-6)+7]}";
        char[] data = s.toCharArray();
        System.out.println(compute(data));
        System.out.println(comp(data));
        System.out.println(compInt(data, 0)[0]);
    }


    public static int[] compInt(char[] data, int startIndex) {
        int len = data.length;
        char flag = '+';
        Stack<Integer> st = new Stack<>();
        int num;
        int i = startIndex;
        for (; i < len; i++) {
            num = 0;
            if (data[i] == '(' || data[i] == '[' || data[i] == '{') {
                int[] tmp = compInt(data, ++i);
                num = tmp[0];
                i = tmp[1];
            }
            while (i < len && data[i] >= '0' && data[i] <= '9') {
                num = num * 10 + data[i] - '0';
                i++;
            }
            switch (flag) {
                case '+':
                    st.push(num);
                    break;
                case '-':
                    st.push(-num);
                    break;
                case '*':
                    st.push(st.pop() * num);
                    break;
                case '/':
                    st.push(st.pop() / num);
                    break;
            }

            if (i >= len) {
                break;
            }
            flag = data[i];
            if (flag == ')' || flag == ']' || flag == '}') {
                i++;
                break;
            }
        }
        int sum = 0;
        while (!st.isEmpty()) {
            sum += st.pop();
        }
        return new int[]{sum, i};
    }


    public static int p = 0;

    public static double comp(char[] data) {
        int len = data.length;
        char flag = '+';
        Stack<Double> st = new Stack<>();
        double num;
        while (p < len) {
            num = 0;
            if (data[p] == '(' || data[p] == '[' || data[p] == '{') {
                p++;
                num = comp(data);
            }
            while (p < len && data[p] >= '0' && data[p] <= '9') {
                num = num * 10 + data[p] - '0';
                p++;
            }
            switch (flag) {
                case '+':
                    st.push(num);
                    break;
                case '-':
                    st.push(-num);
                    break;
                case '*':
                    st.push(st.pop() * num);
                    break;
                case '/':
                    st.push(st.pop() / num);
                    break;
            }
            if (p >= len) {
                break;
            }
            flag = data[p];
            if (flag == ')' || flag == ']' || flag == '}') {
                p++;
                break;
            }
            p++;
        }
        int sum = 0;
        while (!st.isEmpty()) {
            sum += st.pop();
        }
        return sum;
    }

    public static int pos = 0;

    public static double compute(char[] data) {
        int len = data.length;
        double num = 0;
        char flag = '+';
        Stack<Double> st = new Stack<>();

        while (pos < len) {
            if (data[pos] == '{' || data[pos] == '[' || data[pos] == '(') {
                pos++;
                num = compute(data);
            }

            while (pos < len && data[pos] >= '0' && data[pos] <= '9') {
                num = num * 10 + data[pos] - '0';
                pos++;
            }

            switch (flag) {
                case '+':
                    st.push(num);
                    break;
                case '-':
                    st.push(-num);
                    break;
                case '*': {
                    double temp = st.pop();
                    temp *= num;
                    st.push(temp);
                    break;
                }
                case '/': {
                    double temp = st.pop();
                    temp /= num;
                    st.push(temp);
                    break;
                }
            }

            num = 0;
            if (pos == len) {
                break;
            }
            flag = data[pos];
            if (flag == '}' || flag == ']' || flag == ')') {
                pos++;
                break;
            }
            pos++;
        }

        int sum = 0;
        while (!st.isEmpty()) {
            sum += st.pop();
        }
        return sum;
    }
}
