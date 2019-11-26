package fragment.read.pattern.simuduck.tow.behavior.impl;

import fragment.read.pattern.simuduck.tow.behavior.QuackBehavior;

public class Squeak implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("吱吱叫");
    }
}
