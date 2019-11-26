package fragment.read.pattern.simuduck.tow.duck;

import fragment.read.pattern.simuduck.tow.behavior.impl.FlyNoWay;
import fragment.read.pattern.simuduck.tow.behavior.impl.Quack;

public class ModelDuck extends Duck {
    public ModelDuck() {
        flyBehavior = new FlyNoWay();
        quackBehavior = new Quack();
    }

    @Override
    public void display() {
        System.out.println("I'm a model duck");
    }
}
