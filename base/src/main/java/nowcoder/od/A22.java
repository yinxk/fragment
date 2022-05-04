package nowcoder.od;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class A22 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> data = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            if ("0".equals(s)) {
                break;
            }
            data.add(Integer.parseInt(s));
        }

        for (int n : data) {
            int count = 0;
            int tmp = 0;
            while (n >= 2) {
                if (n == 2) {
                    n++;
                }
                tmp = n / 3;
                count += tmp;
                n = n % 3;
                n += tmp;
            }
            System.out.println(count);
        }
    }
}
