package nowcoder.od;

import java.util.Scanner;

public class A17 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] data = scanner.nextLine().split(";");
        int x = 0;
        int y = 0;
        for (String item : data) {
            if (item == null || item.length() == 0) {
                continue;
            }
            char moveDirection = item.charAt(0);
            int num = 0;
            for (int i = 1; i < item.length(); i++) {
                if (item.charAt(i) < '0' || item.charAt(i) > '9') {
                    num = 0;
                    break;
                }
                num = num * 10 + item.charAt(i) - '0';
            }
            if (num > 99) {
                continue;
            }
            switch (moveDirection) {
                case 'W':
                    y += num;
                    break;
                case 'S':
                    y -= num;
                    break;
                case 'A':
                    x -= num;
                    break;
                case 'D':
                    x += num;
                    break;
            }
        }
        System.out.printf("%s,%s%n", x, y);

    }
}
