package fragment.read.pattern.simuduck.one;

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

    @Override
    public void fly() {
        // 覆盖, 变成什么事情也不要做
    }
}
