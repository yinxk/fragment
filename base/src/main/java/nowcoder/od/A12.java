package nowcoder.od;

import java.io.IOException;
import java.util.Scanner;

public class A12 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        StringBuilder sb = new StringBuilder(1000);
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        System.out.println(sb.toString());
    }
}

