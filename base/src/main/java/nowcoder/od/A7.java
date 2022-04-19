package nowcoder.od;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A7 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int index = str.indexOf(".");
        int a = Integer.parseInt(str.substring(0, index));
        char b = index + 1 < str.length() ? str.charAt(index + 1) : '0';
        if (b >= '5') {
            a++;
        }
        System.out.println(a);
    }

}

