package fragment.read.pattern.factory.abstractFactory;


import fragment.read.pattern.factory.abstractFactory.pizzaIngredient.PizzaIngredientFactory;

public class DefaultPizza extends Pizza {
    PizzaIngredientFactory ingredientFactory;

    public DefaultPizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    @Override
    public void prepare() {
        dough = ingredientFactory.createDough();
        sauce = ingredientFactory.createSauce();
        cheese = ingredientFactory.createCheese();
        clam = ingredientFactory.createClam();
    }
}
