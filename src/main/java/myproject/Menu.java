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
        factories.add(new CoffeeWithMilkFactory());
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
            String schoice = scanner.next(); // Выбираем напиток по строке

            int choice = Integer.parseInt(schoice);
            if (choice == 0) {
                keepSelecting = false; // Выход
            } else {
                // Создаем напиток через фабрику
                DrinkFactory drinkFactory = factories.get(choice - 1);
                System.out.println(selections.get(choice - 1));
                String manufacturer = scanner.next();
                Drink drink = drinkFactory.getDrink(manufacturer);

                // Создаем цепочку обязанностей для добавок
                Handler sugarHandler = new SugarHandler();
                Handler milkHandler = new MilkHandler();
                sugarHandler.setNext(milkHandler); // Устанавливаем следующего обработчика

                // Обрабатываем добавки с использованием цепочки
                if (sugarHandler.handle(drink, scanner)) {
                    cart.addDrink(drink); // Добавляем напиток в корзину
                    client.addDrink(drink); // Добавляем напиток клиенту
                    System.out.println("Drink added to your cart.");
                    System.out.println("Total cost: $" + drink.getCost());
                }
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




