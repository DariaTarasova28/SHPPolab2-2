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

    public void pay(PaymentStrategy ps) {
        cart.pay(ps);
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
            String schoice = scanner.next();
            if (!handler.handle(schoice)) {
                return;
            }

            int choice = Integer.parseInt(schoice);
            if (choice == 0) {
                keepSelecting = false;
            } else {
                DrinkFactory drinkFactory = factories.get(choice - 1);
                System.out.println(selections.get(choice - 1));
                String manufacturer = scanner.next();

                Drink drink = drinkFactory.getDrink(manufacturer);

                System.out.println("Add sugar? (yes/no)");
                String sugarChoice = scanner.next().toLowerCase();
                scanner.nextLine(); // сбрасываем буфер

                if (sugarChoice.equals("yes")) {
                    // Связываем цепочку обработчиков
                    IntHandler intHandler = new IntHandler();
                    SugarHandler sugarHandler = new SugarHandler(scanner);
                    sugarHandler.setNext(intHandler); // SugarHandler запрашивает, IntHandler валидирует

                    if (sugarHandler.handle("")) {
                        System.out.print("Enter sugar amount again (for parsing): ");
                        int sugars = Integer.parseInt(scanner.nextLine()); // мы точно знаем, что уже проверено
                        drink = new DrinkWithSugar(drink, sugars);
                    }
                } else if (sugarChoice.equals("no")) {
                    System.out.println("No sugar added.");
                } else {
                    System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                    continue;
                }


                cart.addDrink(drink);


                System.out.println("Drink added to your cart.");
                System.out.println("Total cost: $" + drink.getCost());
            }
        }
        System.out.println("All drinks selected. Proceeding to payment...");
    }

    public PaymentStrategy choosePayment() {
        System.out.println("Choose payment method:");
        System.out.println("1. Debit Card");
        System.out.println("2. Credit Card");
        System.out.println("3. Sell your soul");

        int choice = scanner.nextInt();
        scanner.nextLine(); // очистка ввода

        PaymentStrategy strategy;

        switch (choice) {
            case 1:
                System.out.print("Enter debit card number: ");
                strategy = new DebitCardStrategy(scanner.nextLine());
                break;
            case 2:
                System.out.print("Enter credit card number: ");
                strategy = new CreditCardStrategy(scanner.nextLine());
                break;
            case 3:
                strategy = new SoulPaymentStrategy();
                break;
            default:
                System.out.println("Invalid choice. Defaulting to soul payment.");
                strategy = new SoulPaymentStrategy();
                break;
        }

        // Оборачиваем в прокси для логирования
        return new PaymentStrategyProxy(strategy);
    }
}





