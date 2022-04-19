package nowcoder.od;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class A4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int step = 8;
        String s;
        while ((s = scanner.nextLine()) != null && s.length() > 0) {
            int len = s.length();
            int start = 0;
            while (len >= step) {
                System.out.println(s.substring(start, start + step));
                start += step;
                len -= step;
            }

            if (len > 0) {
                char[] tmp = new char[step];
                for (int i = 0; i < step; i++) {
                    if (start < s.length()) {
                        tmp[i] = s.charAt(start++);
                    } else {
                        tmp[i] = '0';
                    }
                }
                System.out.println(String.valueOf(tmp));
            }
        }
    }

    public static void main1(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = br.readLine()) != null) {
            int len = str.length();
            int start = 0;
            while (len >= 8) {
                System.out.println(str.substring(start, start + 8));
                start += 8;
                len -= 8;
            }
            if (len > 0) {
                char[] tmp = new char[8];
                for (int i = 0; i < 8; i++) {
                    tmp[i] = '0';
                }
                for (int i = 0; start < str.length(); i++) {
                    tmp[i] = str.charAt(start++);
                }
                System.out.println(String.valueOf(tmp));
            }
        }
    }
}

