package myproject;

class DrinkWithSugar extends DrinkDecorator {
    private final int sugarAmount;

    public DrinkWithSugar(Drink drink, int sugarAmount) {
        super(drink);
        this.sugarAmount = sugarAmount;
    }

    @Override
    public double getCost() {
        return drink.getCost() + (0.2 * sugarAmount); // Добавляем стоимость сахара
    }

    @Override
    public double calculateCost() {
        return drink.calculateCost() + (0.2 * sugarAmount);
    }

    @Override
    public String toString() {
        return drink.toString() + " with " + sugarAmount + " sugar(s)";
    }
}