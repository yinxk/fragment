package nowcoder.od.a20220424;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Two {

    public static void main1(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextLine()) {
            return;
        }
        String s = scanner.nextLine();
        if (s == null || s.length() == 0) {
            return;
        }
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
        System.out.print(res);
    }
}
