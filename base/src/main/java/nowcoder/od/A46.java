package nowcoder.od;

import java.util.Scanner;

public class A46 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        int k = Integer.parseInt(in.nextLine());
        String res;
        if (k < s.length()) {
            // res = new String(s.toCharArray(), 0, k);
            res = s.substring(0, k);
        }else {
            res = s;
        }
        System.out.println(res);
    }
}
