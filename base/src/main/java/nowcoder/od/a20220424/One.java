package nowcoder.od.a20220424;

import java.util.Scanner;

public class One {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextLine()) {
            return;
        }
        String s = scanner.nextLine();
        if (s == null || s.length() == 0) {
            return;
        }
        String[] data = s.split(",");
        int len = data.length;
        int[] dp = new int[len];
        for (int i = 0; i < len; i++) {
            dp[i] = 1000;
        }

        for (int i = 0; i < len; i++) {
            if ("1".equals(data[i])) {
                dp[i] = 0;
            } else if (i > 0) {
                dp[i] = dp[i - 1] + 1;
            }
        }
        int max = 0;
        for (int i = len - 1; i >= 0; i--) {
            if ("0".equals(data[i]) && i < len - 1) {
                dp[i] = Math.min(dp[i + 1] + 1, dp[i]);
            }
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
    }


    public static void main1(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextLine()) {
            return;
        }
        String s = scanner.nextLine();
        if (s == null || s.length() == 0) {
            return;
        }
        String[] data = s.split(",");
        int len = data.length;
        int[] sub = new int[len];
        int lastCarIndex = 0;
        for (int i = 0; i < len; i++) {
            if ("1".equals(data[i])) {
                lastCarIndex = i;
            } else {
                sub[i] = i - lastCarIndex;
            }
        }
        lastCarIndex = len - 1;
        int max = 0;
        for (int i = len - 1; i >= 0; i--) {
            if ("1".equals(data[i])) {
                lastCarIndex = i;
            } else {
                sub[i] = Math.min(sub[i], lastCarIndex - i);
            }
            max = Math.max(max, sub[i]);
        }
        System.out.println(max);
    }
}
