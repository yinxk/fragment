package fragment.read.pattern.simuduck.tow.behavior.impl;

import fragment.read.pattern.simuduck.tow.behavior.FlyBehavior;

/**
 * 火箭动力飞行
 */
public class FlyRocketPowered implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("以火箭的速度飞");
    }
}
