package nowcoder.od;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class A41 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            HashSet<Integer> set = new HashSet<>();
            set.add(0);
            int n = in.nextInt();
            int[] w = new int[n];
            int[] nums = new int[n];
            for(int i=0;i<n;i++){
                w[i] = in.nextInt();
            }
            for(int i=0;i<n;i++){
                nums[i] = in.nextInt();
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < nums[i]; j++) {
                    for (Integer exist : new ArrayList<>(set)) {
                        set.add(w[i] + exist);
                    }
                    set.add(w[i]);
                }
            }
            System.out.println(set.size());
        }
    }
}
