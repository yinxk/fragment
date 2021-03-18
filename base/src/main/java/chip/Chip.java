package chip;

import java.util.HashMap;
import java.util.Map;

public class Chip {


    public static void testHashMapPut() {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            map.put(i & 15, i);
        }

        System.out.println(map);

    }

    public static void testSwapByCal() {
        int a = 10;
        int b = 100;
        System.out.printf("交换前: a = %s, b = %s %n", a, b);
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.printf("通过和交换后: a = %s, b = %s %n", a, b);

        char ac = '我';
        char bc = '你';
        System.out.printf("交换前: a = %s, b = %s %n", ac, bc);
        ac = (char) (ac + bc);
        bc = (char) (ac - bc);
        ac = (char) (ac - bc);
        System.out.printf("通过和交换后: a = %s, b = %s %n", ac, bc);


    }

    public static void main(String[] args) {


        // testHashMapPut();
        testSwapByCal();
    }
}
