package myproject;
// Кофе с молоком
class CoffeeWithMilk extends DrinkDecorator {
    public CoffeeWithMilk(Drink drink) {
        super(drink);
    }
    @Override
    public double getCost() {
        return calculateCost();
    }

    @Override
    public double calculateCost() {
        return drink.calculateCost() + 0.7; // Цена за добавление молока
    }
    @Override
    public String toString(){
        return "coffee with milk";
    }
}