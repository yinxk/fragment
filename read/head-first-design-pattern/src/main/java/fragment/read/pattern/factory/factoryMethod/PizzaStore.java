package fragment.read.pattern.factory.factoryMethod;

public abstract class PizzaStore {

    protected abstract Pizza createPizza(String type);
    Pizza orderPizza(String type) {
        Pizza pizza;
        pizza = createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.cut();
        return pizza;
    }
}
