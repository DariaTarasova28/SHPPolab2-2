package myproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Client> clients = new ArrayList<>();
        DrinkMachine machine = new DrinkMachine();
        ShoppingCart cart = new ShoppingCart();

        Menu menu = Menu.getInstance();
        menu.setScanner(scanner);
        menu.setCart(cart);

        // Включаем логирование
        LoggingMenu loggingMenu = new LoggingMenu(menu);

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

        System.out.println("How many clients do you want to create?");
        int numberOfClients = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numberOfClients; i++) {
            System.out.println("\nCreating client #" + (i + 1) + "...");
            Client client = new Client("Client-" + (i + 1));
            clients.add(client);

            // УДАЛЕНО: menu.advise();

            boolean continueChoosing = true;
            while (continueChoosing) {
                continueChoosing = loggingMenu.choose(client);
            }

            menu.printTotalCost();

            try {
                PaymentStrategyProxy ps = menu.choosePayment();
                menu.pay(ps);
            } catch (Exception e) {
                System.out.println("Payment failed: " + e.getMessage());
            }

            System.out.println("\nClient #" + client.getClientId() + " order processed!");
        }


        System.out.println("\nProcessing all orders:");
        while (DrinkMachine.getQueueSize() > 0) {
            machine.serveNext();
        }

        System.out.println("\nAll orders processed.");
    }
}



















