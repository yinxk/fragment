package fragment.read.pattern.factory.abstractFactory.pizzaIngredient;

/**
 * 原料工厂
 */
public interface PizzaIngredientFactory {
    Dough createDough();

    Sauce createSauce();

    Cheese createCheese();

    Clams createClam();
}
