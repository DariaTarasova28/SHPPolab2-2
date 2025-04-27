package myproject;

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

    private Menu() {
        factories.add(new BlackTeaFactory());
        factories.add(new CoffeeFactory());
        factories.add(new GreenTeaFactory());

        selections.add("You've selected black tea. Please enter the manufacturer (Lipton or other):");
        selections.add("You've selected coffee. Please enter the manufacturer (Nescafe or other):");
        selections.add("You've selected green tea. Please enter the manufacturer (Lipton or other):");
    }

    public static Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
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

    public void pay(PaymentStrategyProxy ps) {
        cart.pay(ps);
    }

    public void advise() {
        System.out.println("Please select your drinks (1 for black tea, 2 for coffee, 3 for green tea, 0 for exit):");
    }

    public void printTotalCost() {
        System.out.println("Total cost: $" + cart.calculateTotal());
    }

    public boolean choose(Client client) {
        selectDrinks(client);
        return false; // завершает цикл выбора напитков
    }

    public void selectDrinks(Client client) {
        boolean keepSelecting = true;
        while (keepSelecting) {
            System.out.println("Please select your drinks (1 for black tea, 2 for coffee, 3 for green tea, 0 for exit):");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Очищаем буфер

            if (choice == 0) {
                keepSelecting = false;
            } else if (choice >= 1 && choice <= 3) {
                DrinkFactory drinkFactory = factories.get(choice - 1);
                System.out.println(selections.get(choice - 1));
                String manufacturer = scanner.nextLine();

                // Создаем базовый напиток
                Drink drink = drinkFactory.getDrink(manufacturer);
                double baseCost = drink.getCost();

                // Обрабатываем добавки
                Handler milkHandler = new MilkHandler();
                Handler sugarHandler = new SugarHandler();
                milkHandler.setNext(sugarHandler);

                // Получаем напиток со всеми добавками
                drink = milkHandler.handle(drink, scanner);

                cart.addDrink(drink);
                client.addDrink(drink);
                System.out.println("Drink added to your cart.");
                System.out.println("Current cost of this drink: $" + drink.getCost());
                System.out.println("Total cost so far: $" + cart.calculateTotal());
                
            }
        }
        System.out.println("All drinks selected. Proceeding to payment...");
    }

    public PaymentStrategyProxy choosePayment() {

        List<PaymentStrategy> availableStrategies = new ArrayList<>();
        availableStrategies.add(new CreditCardStrategy());
        availableStrategies.add(new DebitCardStrategy());
        availableStrategies.add(new SoulPaymentStrategy());

        System.out.println("Select your payment method:");
        System.out.println("1. Credit Card");
        System.out.println("2. Debit Card");
        System.out.println("Other input - payment with your soul");

        String pchoice = scanner.next();
        handler.setNext(null); // Завершаем цепочку обязанностей

        PaymentStrategy selectedStrategy;
        int choice;

        try {
            choice = Integer.parseInt(pchoice);
            if (choice == 1 || choice == 2) {
                selectedStrategy = availableStrategies.get(choice - 1);
                scanner.nextLine(); // Очищаем буфер
                selectedStrategy.init(scanner);
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid payment method. Proceeding with soul payment...");
            selectedStrategy = availableStrategies.get(2); // SoulPaymentStrategy
        }

        return new PaymentStrategyProxy(selectedStrategy, availableStrategies, scanner);
    }

}




