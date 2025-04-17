package myproject;

// Декоратор для добавления сахара
class DrinkWithSugar extends DrinkDecorator {
    private final int sugarAmount; // Количество ложек сахара

    public DrinkWithSugar(Drink drink, int sugarAmount) {
        super(drink);
        this.sugarAmount = sugarAmount;
    }
    @Override
    public double getCost() {
        return calculateCost();
    }


    @Override
    public double calculateCost() {
        return drink.calculateCost() + (0.2 * sugarAmount); // Цена за ложку сахара — 0.2$
    }

    @Override
    public String toString() {
        return drink.toString() + " with " + sugarAmount + " sugar(s)";
    }
}
