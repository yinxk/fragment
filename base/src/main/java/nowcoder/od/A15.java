package nowcoder.od;

import java.util.Scanner;

public class A15 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int a = in.nextInt();
            int it = 1;
            int res = 0;
            while (a > 0) {
                res += it & a;
                a = a >> 1;
            }
            System.out.println(res);
        }
    }
}
