package nowcoder.od;

import java.io.IOException;
import java.util.Scanner;

public class A10 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        boolean[] ext = new boolean[128];
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            char it = s.charAt(i);
            if (it >= 0 && it <= 127 && !ext[it]) {
                ext[it] = true;
                res++;
            }
        }
        System.out.println(res);
    }
}

