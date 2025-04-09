package myproject;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private static Menu instance = null;
    private ShoppingCart cart;
    private Scanner scanner;
    private Handler handler;

    List<DrinkFactory> factories = new ArrayList<>();
    List<String> selections = new ArrayList<>();

    public static Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    public Menu() {
        factories.add(new BlackTeaFactory());
        factories.add(new CoffeeWithMilkFactory());
        factories.add(new GreenTeaFactory());

        selections.add("You've selected black tea. Please enter the manufacturer (Lipton or other):");
        selections.add("You've selected coffee. Please enter the manufacturer (Nescafe or other):");
        selections.add("You've selected green tea. Please enter the manufacturer (Lipton or other):");

        instance = this;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public void greet() {
        System.out.println("Welcome to the drink menu!");
    }

    public void pay(PaymentStrategy ps) {
        cart.pay(ps);
    }

    public void advise() {
        System.out.println("Please select your drinks (1 for black tea, 2 for coffee, 3 for green tea, 0 for exit):");
    }

    public void printTotalCost() {
        System.out.println("Total cost: $" + cart.calculateTotal());
    }

    public boolean choose() {
        String schoice = scanner.next();
        if (!handler.handle(schoice)) {
            return true;
        }

        int choice = Integer.parseInt(schoice);
        if (choice == 0)
            return false;

        DrinkFactory drinkFactory = factories.get(choice - 1);
        System.out.println(selections.get(choice - 1));
        String manufacturer = scanner.next();

        // Создаем напиток
        Drink drink = drinkFactory.getDrink(manufacturer);

        // Добавляем сахар
        System.out.println("Add sugar? (y/n)");
        if (scanner.next().equalsIgnoreCase("y")) {
            System.out.println("How many sugars (0-3)?");
            int sugars = scanner.nextInt();
            drink = new DrinkWithSugar(drink, sugars);
        }

        // Добавляем в корзину и клиенту
        cart.getDrinks().add(drink);
        Main.user.addDrink(drink);

        return true;
    }

    public PaymentStrategy choosePayment() {
        System.out.println("Select your payment method:\n1. Credit Card\n2. Debit Card\nOther input means you are giving away your soul!");
        String pchoice = scanner.next();
        handler.setNext(null);

        if (handler.handle(pchoice)) {
            int choice = Integer.parseInt(pchoice);
            scanner.nextLine(); // Очистка ввода после next()

            if (choice == 1) {
                System.out.print("Enter credit card number: ");
                String number = scanner.nextLine();
                return new CreditCardStrategy(number);
            } else if (choice == 2) {
                System.out.print("Enter debit card number: ");
                String number = scanner.nextLine();
                return new DebitCardStrategy(number);
            }
        }

        return new SoulPaymentStrategy();
    }
}
