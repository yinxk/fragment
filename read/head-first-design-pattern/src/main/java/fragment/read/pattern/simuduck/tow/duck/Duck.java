package fragment.read.pattern.simuduck.tow.duck;

import fragment.read.pattern.simuduck.tow.behavior.FlyBehavior;
import fragment.read.pattern.simuduck.tow.behavior.QuackBehavior;

/**
 * 鸭子类
 */
public abstract class Duck {

    protected FlyBehavior flyBehavior;

    protected QuackBehavior quackBehavior;

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }

    public void performQuack() {
        quackBehavior.quack();
    }

    /**
     * 所有的鸭子都会游泳
     */
    public void swim() {
        System.out.println("所有的鸭子都会游泳");
    }

    /**
     * 鸭子有不同的外观
     */
    public abstract void display();

    public void performFly() {
        flyBehavior.fly();
    }

}
