package myproject;
// Черный чай
public class BlackTea implements Drink {
    private final String manufacturer;
    private final double price;
    public BlackTea(String manufacturer, double price) {
        this.manufacturer = manufacturer;
        this.price = price;
    }

    @Override
    public double calculateCost() {
        // Цена за черный чай
        if (manufacturer.equalsIgnoreCase("Lipton")) {
            return price + 0.5; // Дополнительная цена за чай Lipton
        } else {
            return price;
        }
    }
    @Override
    public String toString(){
        return "black tea";
    }
}