package myproject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Menu menu = context.getBean(Menu.class);
        Scanner scanner = context.getBean(Scanner.class);

        System.out.print("How many clients? ");
        int clientCount = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < clientCount; i++) {
            // Создаем новую корзину для каждого клиента
            ShoppingCart newCart = context.getBean(ShoppingCart.class);
            menu.setCart(newCart); // Устанавливаем новую корзину в Menu

            Client client = new Client("Client-" + (i+1));
            menu.greet();
            menu.selectDrinks(client);
            menu.printTotalCost();

            PaymentStrategyProxy ps = menu.choosePayment();
            newCart.pay(ps);
        }
    }
}




















