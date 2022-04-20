package nowcoder.od;

import java.util.Scanner;

public class A16 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] s = scanner.nextLine().split(" ");
        int money = Integer.parseInt(s[0]);
        int n = Integer.parseInt(s[1]);
        int[][] data = new int[n][];
        for (int i = 0; i < n; i++) {
            s = scanner.nextLine().split(" ");
            data[i] = new int[]{Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2])};
        }
        int[] dp = new int[n];
        boolean[] isBuy = new boolean[n];





    }
}
