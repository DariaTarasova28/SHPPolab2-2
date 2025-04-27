package myproject;

class DrinkWithMilk extends DrinkDecorator {
    public DrinkWithMilk(Drink drink) {
        super(drink);
    }

    @Override
    public double getCost() {
        return drink.getCost() + 0.7; // Добавляем стоимость молока
    }

    @Override
    public double calculateCost() {
        return drink.calculateCost() + 0.7;
    }

    @Override
    public String toString() {
        return drink.toString() + " with milk";
    }
}