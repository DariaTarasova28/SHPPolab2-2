package myproject;

import java.util.Scanner;

public class Menu {
    private ShoppingCart cart; // Изменено с final, чтобы можно было менять корзину
    private final Scanner scanner;
    private final Handler handler;
    private final BlackTeaFactory blackTeaFactory;
    private final CoffeeFactory coffeeFactory;
    private final GreenTeaFactory greenTeaFactory;
    private final PaymentStrategyProxy paymentStrategyProxy;

    public Menu(
            ShoppingCart cart,
            Scanner scanner,
            Handler handler,
            BlackTeaFactory blackTeaFactory,
            CoffeeFactory coffeeFactory,
            GreenTeaFactory greenTeaFactory,
            PaymentStrategyProxy paymentStrategyProxy
    ) {
        this.cart = cart;
        this.scanner = scanner;
        this.handler = handler;
        this.blackTeaFactory = blackTeaFactory;
        this.coffeeFactory = coffeeFactory;
        this.greenTeaFactory = greenTeaFactory;
        this.paymentStrategyProxy = paymentStrategyProxy;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void greet() {
        System.out.println("Welcome to our Coffee Shop!");
    }

    public void selectDrinks(Client client) {
        boolean ordering = true;
        while (ordering) {
            System.out.println("\nPlease select your drink:");
            System.out.println("1. Black tea");
            System.out.println("2. Coffee");
            System.out.println("3. Green tea");
            System.out.println("0. Finish order");
            System.out.print("Your choice: ");

            int choice = getIntInput(0, 3);

            if (choice == 0) {
                ordering = false;
            } else {
                processDrinkChoice(client, choice);
            }
        }
    }

    private void processDrinkChoice(Client client, int choice) {
        DrinkFactory factory = getFactory(choice);
        System.out.print("Enter manufacturer: ");
        String manufacturer = scanner.nextLine();

        Drink drink = factory.getDrink(manufacturer);
        drink = handler.handle(drink, scanner);

        cart.addDrink(drink);
        client.addDrink(drink);
        System.out.printf("Added: %s ($%.2f)\n", drink.toString(), drink.getCost());
    }

    private DrinkFactory getFactory(int choice) {
        return switch (choice) {
            case 1 -> blackTeaFactory;
            case 2 -> coffeeFactory;
            case 3 -> greenTeaFactory;
            default -> throw new IllegalArgumentException("Invalid drink choice");
        };
    }

    public void printTotalCost() {
        System.out.printf("\nTotal order: $%.2f\n", cart.calculateTotal());
    }

    public PaymentStrategyProxy choosePayment() {
        System.out.println("\nSelect payment method:");
        System.out.println("1. Credit Card");
        System.out.println("2. Debit Card");
        System.out.println("Other - Payment with your soul");
        System.out.print("Your choice: ");

        try {
            int choice = Integer.parseInt(scanner.next());
            paymentStrategyProxy.selectStrategy(choice);
            if (choice == 1 || choice == 2) {
                scanner.nextLine();
                paymentStrategyProxy.init(scanner);
            }
        } catch (NumberFormatException e) {
            paymentStrategyProxy.selectStrategy(3); // soul payment
            System.out.println("Processing soul payment...");
        }
        return paymentStrategyProxy;
    }

    private int getIntInput(int min, int max) {
        while (true) {
            try {
                int input = scanner.nextInt();
                scanner.nextLine();
                if (input >= min && input <= max) {
                    return input;
                }
            } catch (Exception e) {
                scanner.nextLine();
            }
            System.out.printf("Please enter a number between %d and %d: ", min, max);
        }
    }
}





