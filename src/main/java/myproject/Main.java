package myproject;

import java.util.Scanner;

public class Main {
    public static Client user = new Client();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DrinkMachine machine = new DrinkMachine();
        ShoppingCart cart = new ShoppingCart();

        Menu menu = Menu.getInstance();
        menu.setScanner(scanner);
        menu.setCart(cart);

        // Анонимный обработчик ввода
        menu.setHandler(new Handler() {
            private Handler next;

            @Override
            public boolean handle(String input) {
                try {
                    int choice = Integer.parseInt(input);
                    return choice >= 0 && choice <= 3;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number between 0 and 3.");
                    return false;
                }
            }

            @Override
            public void setNext(Handler handler) {
                this.next = handler;
            }
        });

        menu.greet();

        boolean keepGoing = true;
        while (keepGoing) {
            menu.advise();
            try {
                keepGoing = menu.choose();
            } catch (Exception e) {
                System.out.println("Error while processing choice: " + e.getMessage());
            }
        }

        menu.printTotalCost();

        try {
            PaymentStrategy strategy = menu.choosePayment(); // ← теперь всё правильно!
            menu.pay(strategy);
        } catch (Exception e) {
            System.out.println("Payment failed: " + e.getMessage());
        }

        // Обработка очереди заказов
        System.out.println("\nProcessing all orders:");
        while (DrinkMachine.getQueueSize() > 0) {
            machine.serveNext();
        }
    }
}







