package nowcoder.od;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A6 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = br.readLine()) != null) {
            int num = Integer.parseInt(str);
            StringBuilder sb = new StringBuilder();
            for (int i = 2; i <= Math.sqrt(num); ) {
                if (num % i == 0) {
                    sb.append(i).append(" ");
                    num = num / i;
                    continue;
                }
                i++;
            }
            sb.append(num).append(" ");
            System.out.println(sb.toString());
        }
    }
}

