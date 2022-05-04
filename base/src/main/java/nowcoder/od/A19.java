package nowcoder.od;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class A19 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Integer> data = new LinkedHashMap<>();
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            if (s == null || s.length() == 0) {
                break;
            }
            int idx1 = s.lastIndexOf(" ");
            int idx2 = s.lastIndexOf("\\");
            String key = idx1 - idx2 > 16 ? s.substring(idx1 - 16) : s.substring(idx2 + 1);
            data.put(key, data.getOrDefault(key, 0) + 1);
        }
        int count = 0;
        for (String key : data.keySet()) {
            count++;
            if (count > data.size() - 8) {
                System.out.printf("%s %s%n", key, data.get(key));
            }
        }

    }
}
