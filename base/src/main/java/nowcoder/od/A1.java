package nowcoder.od;

import java.util.Scanner;

/**
 * https://www.nowcoder.com/exam/oj/ta?tpId=37
 */
public class A1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = 0;
        if (in.hasNextLine()) {
            String s = in.nextLine();
            int i = s.length() - 1;
            for (; i >= 0; i--) {
                char it = s.charAt(i);
                if (it >= 'A' && it <= 'z') {
                    count++;
                } else if (it == ' ') {
                    break;
                }
            }
        }
        System.out.println(count);
    }


}
