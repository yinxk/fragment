package nowcoder.od;

import java.util.Arrays;
import java.util.Scanner;

public class A45 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        String[] data = new String[n];
        for (int i = 0; i < n; i++) {
            data[i] = in.nextLine();
        }

        for (int i = 0; i < n; i++) {
            System.out.println(cal(data[i]));
        }

    }

    private static int cal(String str) {
        int[] count = new int[27];
        for (char ch : str.toCharArray()) {
            count[ch - 'a' + 1]++;
        }
        Arrays.sort(count);
        int sum = 0;
        for (int i = count.length - 1; i > 0; i--) {
            for (int j = 0; j < count[i]; j++) {
                sum += i;
            }
        }
        return sum;
    }

}
