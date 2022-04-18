package nowcoder.od;

import java.util.Scanner;

public class A2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String ch = scanner.nextLine();
        int len = s.length();
        int res = 0;
        for (int i = 0; i < len; i++) {
            if (ch.charAt(0) == s.charAt(i)) {
                res++;
            }
            if ((int) ch.charAt(0) >= 65) {
                if (ch.charAt(0) == (char) (s.charAt(i) - 32) ||
                        ch.charAt(0) == (char) (s.charAt(i) + 32)) {
                    res++;
                }
            }
        }
        System.out.println(res);
    }
}
