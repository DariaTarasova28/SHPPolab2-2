package myproject;
public class BlackTea implements Drink {
    private final String manufacturer;
    private final double price;

    public BlackTea(String manufacturer, double price) {
        this.manufacturer = manufacturer;
        this.price = price;
    }

    @Override
    public double calculateCost() {
        if (manufacturer.equalsIgnoreCase("Lipton")) {
            return price + 0.5;
        } else {
            return price;
        }
    }

    @Override
    public double getCost() {
        return calculateCost(); // реализация getCost
    }

    @Override
    public String toString() {
        return "black tea";
    }
}
