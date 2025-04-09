package myproject;
// Зеленый чай
public class GreenTea implements Drink {
    private final double price;
    private final String manufacturer;
    public GreenTea(String manufacturer, double price) {
        this.manufacturer = manufacturer;
        this.price = price;
    }
    @Override
    public double calculateCost() {
        // Цена за зеленый чай
        if (manufacturer.equalsIgnoreCase("Lipton")) {
            return price + 0.5; // Дополнительная цена за чай Lipton
        } else {
            return price;
        }
    }
    @Override
    public String toString(){
        return "green tea";
    }
}