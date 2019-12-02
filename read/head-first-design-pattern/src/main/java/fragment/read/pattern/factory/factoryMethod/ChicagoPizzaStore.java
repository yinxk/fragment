package fragment.read.pattern.factory.factoryMethod;

import java.util.Optional;

public class ChicagoPizzaStore extends PizzaStore {
    @Override
    protected Pizza createPizza(String type) {
        Pizza pizza;
        type = Optional.ofNullable(type).orElse("default");
        switch (type) {
            case "cheese":
                pizza = new ChicagoStyleCheesePizza();
                break;
            case "pepperoni":
                pizza = new ChicagoStylePepperoniPizza();
                break;
            case "clam":
                pizza = new ChicagoStyleClamPizza();
                break;
            case "veggie":
                pizza = new ChicagoStyleVeggiePizza();
                break;
            default:
                pizza = new DefaultPizza();
        }

        return pizza;
    }
}
