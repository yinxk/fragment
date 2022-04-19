package nowcoder.od;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = br.readLine()) != null) {
            int res = 0;
            int mul = 0;
            int len = str.length();
            for (int i = len - 1; i >= 0; i--) {
                char it = str.charAt(i);
                if (it <= '9') {
                    mul = it - 48;
                } else if (it <= 'F') {
                    mul = it - 55;
                } else if (it <= 'f') {
                    mul = it - 87;
                }
                if (it == 'x') {
                    break;
                }
                res = res + mul * (int) Math.pow(16, (len - 1 - i));
            }
            System.out.println(res);
        }
    }
}

