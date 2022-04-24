package nowcoder.od.a20220424;

import java.util.Scanner;
import java.util.Stack;

public class Three {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextLine()) {
            return;
        }
        String s = scanner.nextLine();
        if (s == null || s.length() == 0) {
            return;
        }
        String[] data = s.split(" ");
        int len = data.length;
        int sum = 1;
        int index = 1;
        // 查找叶子节点开始索引
        while (sum + (index << 1) < len) {
            index = index << 1;
            sum += index;
        }
        // 当只有一个节点的特殊情况时
        if (len == 1) {
            sum--;
        }
        int minIndex = sum;
        String minVal = data[sum];
        // 查找叶子节点最小值及索引
        for (int i = sum + 1; i < len; i++) {
            if (!"-1".equals(data[i])) {
                if ("-1".equals(minVal) || minVal.compareTo(data[i]) > 0) {
                    minVal = data[i];
                    minIndex = i;
                }
            }
        }
        Stack<String> stack = new Stack<>();
        stack.push(minVal);

        minIndex++;

        while (minIndex > 1) {
            // 偶数
            if ((minIndex & 1) == 0) {
                minIndex /= 2;
                stack.push(data[minIndex - 1]);
            } else {
                minIndex = (minIndex - 1) / 2;
                stack.push(data[minIndex - 1]);
            }
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }

    }
}
