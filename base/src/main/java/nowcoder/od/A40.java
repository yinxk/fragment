package nowcoder.od;

import java.util.Scanner;

public class A40 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        int[] count = new int[4];

        for (char ch : s.toCharArray()) {
            count[getIndex(ch)]++;
        }
        for (int ct : count) {
            System.out.println(ct);
        }
    }

    public static int getIndex(char ch) {
        if (Character.isLetter(ch)) {
            return 0;
        } else if (ch == ' ') {
            return 1;
        } else if (Character.isDigit(ch)) {
            return 2;
        } else {
            return 3;
        }
    }
}
