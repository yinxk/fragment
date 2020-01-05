package fragment.read.pattern.factory.abstractFactory;

import fragment.read.pattern.factory.abstractFactory.pizzaIngredient.NYPizzaIngredientFactory;
import fragment.read.pattern.factory.abstractFactory.pizzaIngredient.PizzaIngredientFactory;

import java.util.Optional;

public class NYPizzaStore extends PizzaStore {
    @Override
    protected Pizza createPizza(String type) {
        Pizza pizza;
        PizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory();
        type = Optional.ofNullable(type).orElse("default");
        switch (type) {
            case "cheese":
                pizza = new CheesePizza(ingredientFactory);
                break;
            case "pepperoni":
                pizza = new PepperoniPizza(ingredientFactory);
                break;
            case "clam":
                pizza = new ClamPizza(ingredientFactory);
                break;
            case "veggie":
                pizza = new VeggiePizza(ingredientFactory);
                break;
            default:
                pizza = new DefaultPizza(ingredientFactory);
        }
        return pizza;
    }
}
