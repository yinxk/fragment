package genericity.wildcard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 假设我们想编写一个方法来统计某个List中的所有元素的总和
 */
public class Calculate {

    static double sum1(List<Number> list) {
        double sum = 0.0;
        for (Number number : list) {
            sum += number.doubleValue();
        }
        return sum;
    }

    /**
     * 有界通配符(bounded wildcard), 其中Number构成了我们所期望的类型的上届
     * 无论我们得到什么类型, 它必须至少是一个Number
     */
    static double sum2(List<? extends Number> list) {
        double sum = 0.0;
        for (Number number : list) {
            sum += number.doubleValue();
        }
        return sum;
    }

    /**
     * 我们也可以指定通配符类型必须与某个类或是其超类相同, 可以通过使用super关键字来实现这一目的
     * 在本方法中, 参数list的元素是Integer或是其任何超类
     */
    static void lowerBound(List<? super Integer> list) {
    }

    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(4);
        integers.add(9);
        // Calculate.sum1(integers); // 不能编译
        // 问题在于即使Integer是Number的子类型, List<Integer>也不是List<Number>的子类型
        // 这与数组相反, 在数组中Integer[]是Number[]的子类型

        // 所以我们必须找到一种方法来声明一个List, 使其元素类型是与Number相兼容的任意类型,
        // 能够实现这一目的的方式就是使用通配符"?"  ---> sum2
        System.out.println(Calculate.sum2(integers));


        // 下界
        Calculate.lowerBound(new ArrayList<Integer>());
        Calculate.lowerBound(new ArrayList<Number>());
        Calculate.lowerBound(new ArrayList<Serializable>());
        Calculate.lowerBound(new ArrayList<Comparable<Integer>>());
        Calculate.lowerBound(new ArrayList<Object>());
        // 都是可以的

        // 有界通配符与有界类型变量不一样, 它最多只可以拥有一个单一的边界,
        // 要么是上界, 要么是下界

        // 无界通配符, 如: List<?>表示任意类型的列表, 其隐含的上届是Object
        // List<?>并不是List<Object>的另一种写法, 而是List<? extends Object>的另一种写法


        // 通配符可以在大多数的声明中使用: 字段, 局部变量, 参数类型和返回类型
    }
}
