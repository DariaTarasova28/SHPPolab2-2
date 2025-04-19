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

                if (sugarChoice.equals("yes")) {
                    System.out.println("How many sugars (0-3)?");
                    int sugars = -1;
                    while (sugars < 0 || sugars > 3) {
                        if (scanner.hasNextInt()) {
                            sugars = scanner.nextInt();
                            if (sugars >= 0 && sugars <= 3) {
                                break;
                            } else {
                                System.out.println("Please enter a number between 0 and 3.");
                            }
                        } else {
                            System.out.println("Invalid input. Please enter a number between 0 and 3.");
                            scanner.next();
                        }
                    }

                    drink = new DrinkWithSugar(drink, sugars);
                } else if (sugarChoice.equals("no")) {
                    System.out.println("No sugar added.");
                } else {
                    System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                    continue;
                }

                cart.addDrink(drink);
                client.addDrink(drink);

                System.out.println("Drink added to your cart.");
                System.out.println("Total cost: $" + drink.getCost());
            }
        }
        System.out.println("All drinks selected. Proceeding to payment...");
    }

    public PaymentStrategyProxy choosePayment() {
        System.out.println("Select your payment method:\n1. Credit Card\n2. Debit Card\nOther input means you are giving away your soul!");
        String pchoice = scanner.next();
        handler.setNext(null);

        // Объявим переменные, чтобы потом собрать список стратегий
        CreditCardStrategy creditCardStrategy = null;
        DebitCardStrategy debitCardStrategy = null;
        SoulPaymentStrategy soulPaymentStrategy = new SoulPaymentStrategy();

        if (handler.handle(pchoice)) {
            int choice = Integer.parseInt(pchoice);
            scanner.nextLine(); // clear buffer

            if (choice == 1) {
                System.out.print("Enter credit card number: ");
                String number = scanner.nextLine();
                creditCardStrategy = new CreditCardStrategy(number);
            } else if (choice == 2) {
                System.out.print("Enter debit card number: ");
                String number = scanner.nextLine();
                debitCardStrategy = new DebitCardStrategy(number);
            }
        }

        // Сбор всех возможных стратегий
        List<PaymentStrategy> allStrategies = new ArrayList<>();
        if (creditCardStrategy != null) {
            allStrategies.add(creditCardStrategy);
            allStrategies.add(new DebitCardStrategy("default-debit"));
            allStrategies.add(soulPaymentStrategy);
            return new PaymentStrategyProxy(creditCardStrategy, allStrategies);
        } else if (debitCardStrategy != null) {
            allStrategies.add(new CreditCardStrategy("default-credit"));
            allStrategies.add(debitCardStrategy);
            allStrategies.add(soulPaymentStrategy);
            return new PaymentStrategyProxy(debitCardStrategy, allStrategies);
        } else {
            allStrategies.add(new CreditCardStrategy("default-credit"));
            allStrategies.add(new DebitCardStrategy("default-debit"));
            allStrategies.add(soulPaymentStrategy);
            return new PaymentStrategyProxy(soulPaymentStrategy, allStrategies);
        }
    }


}




