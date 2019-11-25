package fragment.read.pattern.simuduck;

/**
 * 橡皮鸭
 */
public class RubberDuck extends Duck {
    @Override
    public void quack() {
        System.out.println("吱吱叫");
    }

    @Override
    public void display() {
        System.out.println("外观橡皮鸭");
    }
}
