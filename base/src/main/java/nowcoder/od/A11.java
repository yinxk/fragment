package nowcoder.od;

import java.io.IOException;
import java.util.Scanner;

public class A11 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        char[] data = new char[s.length()];
        for (int i = s.length() - 1; i >= 0; i--) {
            data[i] = s.charAt(s.length() - 1 - i);
        }
        System.out.println(String.valueOf(data));
    }
}

