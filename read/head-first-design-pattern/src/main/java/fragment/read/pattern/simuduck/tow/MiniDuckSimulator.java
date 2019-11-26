package fragment.read.pattern.simuduck.tow;

import fragment.read.pattern.simuduck.tow.behavior.impl.FlyRocketPowered;
import fragment.read.pattern.simuduck.tow.behavior.impl.FlyWithWings;
import fragment.read.pattern.simuduck.tow.behavior.impl.Quack;
import fragment.read.pattern.simuduck.tow.duck.Duck;
import fragment.read.pattern.simuduck.tow.duck.MallardDuck;
import fragment.read.pattern.simuduck.tow.duck.ModelDuck;

public class MiniDuckSimulator {
    public static void main(String[] args) {
        Duck mallard = new MallardDuck();
        mallard.setFlyBehavior(new FlyWithWings());
        mallard.setQuackBehavior(new Quack());
        mallard.performQuack();
        mallard.performFly();

        Duck modelDuck = new ModelDuck();
        modelDuck.performFly();
        modelDuck.setFlyBehavior(new FlyRocketPowered());
        modelDuck.performFly();
    }
}
