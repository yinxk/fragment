package nowcoder.od;

import java.util.Arrays;
import java.util.Scanner;

public class A3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = scanner.nextInt();
        }
        Arrays.sort(data);
        int slow = 0;
        int fast = 1;
        boolean[] deleted = new boolean[n];
        while (fast < n) {
            if (data[slow] != data[fast]) {
                slow = fast;
            } else {
                deleted[fast] = true;
            }
            fast++;
        }
        for (int i = 0; i < n; i++) {
            if (!deleted[i]) {
                System.out.println(data[i]);
            }


        }
    }
}
