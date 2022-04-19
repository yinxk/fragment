package nowcoder.od;

import java.io.IOException;
import java.util.Scanner;

public class A9 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        boolean[] ext = new boolean[10];
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            char it = s.charAt(i);
            if (!ext[it - '0']) {
                sb.append(it);
                ext[it - '0'] = true;
            }
        }
        System.out.println(Integer.parseInt(sb.toString()));
    }
}

