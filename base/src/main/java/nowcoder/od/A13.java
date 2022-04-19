package nowcoder.od;

import java.io.IOException;
import java.util.Scanner;

public class A13 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String s ;
        while (scanner.hasNextLine()) {
            s = scanner.nextLine();
            StringBuilder sb = new StringBuilder(1000);
            String[] data = s.split(" ");
            for (int i = data.length - 1; i >= 0; i--) {
                sb.append(data[i]);
                if (i != 0) {
                    sb.append(" ");
                }
            }
            System.out.println(sb.toString());
        }
    }
}

