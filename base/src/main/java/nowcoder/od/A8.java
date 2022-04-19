package nowcoder.od;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class A8 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        KV[] data = new KV[n];
        for (int i = 0; i < n; i++) {
            String s = scanner.nextLine();
            String[] kv = s.split(" ");
            data[i] = new KV(Integer.parseInt(kv[0]), Integer.parseInt(kv[1]));
        }
        Arrays.sort(data, Comparator.comparingInt(o -> o.key));
        int slow = 0;
        int fast = 1;
        boolean[] deleted = new boolean[n];
        while (fast < n) {
            if (data[slow].key == data[fast].key) {
                data[slow].val += data[fast].val;
                deleted[fast] = true;
            } else {
                slow = fast;
            }
            fast++;
        }
        for (int i = 0; i < n; i++) {
            if (!deleted[i]) {
                System.out.println(data[i].key + " " + data[i].val);
            }
        }

    }

    public static class KV {
        public int key;
        public int val;

        public KV(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

}

