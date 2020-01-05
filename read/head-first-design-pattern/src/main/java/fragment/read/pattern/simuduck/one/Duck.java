package fragment.read.pattern.simuduck.one;

/**
 * 鸭子类
 */
public abstract class Duck {

    /**
     * 所有的鸭子都会呱呱叫
     */
    public void quack(){
        System.out.println("呱呱呱");
    }

    /**
     * 所有的鸭子都会游泳
     */
    public void swim() {
        System.out.println("游啊游");
    }

    /**
     * 鸭子有不同的外观
     */
    public abstract void display();

    /**
     * 需求变化, 需要鸭子会飞
     */
    public void fly() {
        System.out.println("鸭子飞");
    }
}
