package chip;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {

    public static void main(String[] args) {

        List<String> words = Arrays.asList("GoodBye", "World");
        // List<String[]> collect = words.stream()
        //         .map(w -> w.split(""))
        //         .distinct()
        //         .collect(Collectors.toList());
        // System.out.println(collect);
        // List<Stream<String>> collect = words.stream()
        //         .map(w -> w.split(""))
        //         .map(Arrays::stream)
        //         .distinct()
        //         .collect(Collectors.toList());
        // System.out.println(collect);

        List<String> collect = words.stream()
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        // System.out.println(collect);

        List<Integer> number1 = Arrays.asList(1, 2, 3);
        List<Integer> number2 = Arrays.asList(4, 5);
        List<int[]> pairs = number1.stream()
                .flatMap(i -> number2.stream().map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        // pairs.forEach( a -> System.out.println(a[0] + ", " + a[1]));


        Long reduce = Stream.iterate(1L, i -> i + 1)
                .limit(1000000)
                .parallel()
                .reduce(0L, Long::sum);
        System.out.println(reduce);


    }
}
