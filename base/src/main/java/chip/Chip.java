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
    
    public static void main(String[] args) {
        
        
        // testHashMapPut();
        for (int i = 0; i < 16; i++) {
            System.out.printf("DMux(in=in[%s], sel=sel, a=a[%s] ,b=b[%s]);\n", i, i, i);
        }
        
    }
}
