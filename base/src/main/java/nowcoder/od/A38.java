package nowcoder.od;

import java.util.Scanner;

public class A38 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        double tmp = n;
        double sum = 0;
        for (int i = 0; i < 5; i++) {
            sum += tmp + tmp;
            tmp = tmp / 2;
        }
        System.out.println(sum - n);
        System.out.println(tmp);
    }
}
