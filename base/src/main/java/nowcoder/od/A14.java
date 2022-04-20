package nowcoder.od;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class A14 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String[] data = new String[n];
        for (int i = 0; i < n; i++) {
            data[i] = scanner.nextLine();
        }
        Arrays.sort(data);
        for (String it : data) {
            System.out.println(it);
        }
    }
}

