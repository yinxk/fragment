package fragment.read.pattern.simuduck.tow.behavior.impl;

import fragment.read.pattern.simuduck.tow.behavior.QuackBehavior;

public class MuteQuack implements QuackBehavior {
    @Override
    public void quack() {
        // 什么都不做, 不会叫
    }
}
