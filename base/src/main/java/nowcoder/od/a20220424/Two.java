package nowcoder.od.a20220424;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Two {

    public static void main(String[] args) {
        String s = "3#4$2";
        System.out.println(com(s));
        System.out.println(compute1(s));


    }

    public static void main1(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextLine()) {
            return;
        }
        String s = scanner.nextLine();
        int res = com(s);
        System.out.print(res);
    }

    private static int com(String s) {
        int len = s.length();
        List<String> data = new ArrayList<>();
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            char it = s.charAt(i);
            if (it == '$' || it == '#') {
                data.add(strBuilder.toString());
                strBuilder = new StringBuilder();
                data.add(String.valueOf(it));
                continue;
            }
            strBuilder.append(it);
        }
        data.add(strBuilder.toString());

        Stack<String> stack = new Stack<>();
        for (int i = 0; i < data.size(); i++) {
            String it = data.get(i);
            if ("$".equals(it)) {
                int x = Integer.parseInt(stack.pop());
                int y = Integer.parseInt(data.get(i + 1));
                stack.push(String.valueOf(3 * x + y + 2));
                i++;
                continue;
            }
            stack.push(it);
        }
        Stack<String> tmp = new Stack<>();
        while (!stack.isEmpty()) {
            tmp.push(stack.pop());
        }
        int res = 0;
        while (!tmp.isEmpty()) {
            String pop = tmp.pop();
            if ("#".equals(pop)) {
                int x = Integer.parseInt(stack.pop());
                int y = Integer.parseInt(tmp.pop());
                res = 2 * x + 3 * y + 4;
                stack.push(String.valueOf(res));
                continue;
            }
            stack.push(pop);
        }
        return res;
    }


    public static int compute1(String s) {
        int len = s.length();
        char flag = '#';
        Stack<Integer> st = new Stack<>();
        int num;
        for (int i = 0; i < len; i++) {
            num = 0;
            // 当前数字
            while (i < len && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                num = num * 10 + s.charAt(i) - '0';
                i++;
            }

            switch (flag) {
                case '#':
                    st.push(num);
                    break;
                case '$':
                    int temp = st.pop();
                    temp = 3 * temp + num + 2;
                    st.push(temp);
                    break;
            }
            if (i >= len) {
                break;
            }
            flag = s.charAt(i);
        }
        Stack<Integer> stTmp = new Stack<>();
        while (!st.isEmpty()) {
            stTmp.push(st.pop());
        }
        while (stTmp.size() >= 2) {
            int x = stTmp.pop();
            int y = stTmp.pop();
            stTmp.push(2 * x + 3 * y + 4);
        }
        return stTmp.pop();
    }
}
