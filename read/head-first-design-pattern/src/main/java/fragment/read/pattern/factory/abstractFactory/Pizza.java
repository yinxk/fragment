package fragment.read.pattern.factory.abstractFactory;

import fragment.read.pattern.factory.abstractFactory.pizzaIngredient.Cheese;
import fragment.read.pattern.factory.abstractFactory.pizzaIngredient.Clams;
import fragment.read.pattern.factory.abstractFactory.pizzaIngredient.Dough;
import fragment.read.pattern.factory.abstractFactory.pizzaIngredient.Sauce;

/**
 * 披萨
 */
public abstract class Pizza {

    Dough dough;
    Sauce sauce;
    Cheese cheese;
    Clams clam;

    public abstract void prepare();

    public void bake() {
        System.out.println("bake");
    }

    public void cut() {
        System.out.println("cut");
    }

    public void box() {
        System.out.println("box");
    }

}
