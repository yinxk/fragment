package fragment.read.pattern.factory.SimpleFactory;

import java.util.Optional;

/**
 * 简单工厂
 */
public class SimplePizzaFactory {
    public Pizza createPizza(String type) {
        Pizza pizza = null;
        type = Optional.ofNullable(type).orElse("default");
        switch (type) {
            case "cheese":
                pizza = new CheesePizza();
                break;
            case "pepperoni":
                pizza = new PepperoniPizza();
                break;
            case "clam":
                pizza = new ClamPizza();
                break;
            case "veggie":
                pizza = new VeggiePizza();
                break;
            default:
                pizza = new DefaultPizza();
        }

        return pizza;
    }
}
