package fragment.read.pattern.simuduck.tow.behavior.impl;

import fragment.read.pattern.simuduck.tow.behavior.FlyBehavior;

public class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("鸭子飞行");
    }
}
