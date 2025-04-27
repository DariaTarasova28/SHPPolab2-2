package myproject;

import java.util.ArrayDeque;
import java.util.Deque;

public class DrinkMachine {

    private static final Deque<Order> orders = new ArrayDeque<>();

    // Получение размера очереди
    public static int getQueueSize() {
        return orders.size();
    }

    // Обслуживание следующего заказа
    public void serveNext() {
        if (!orders.isEmpty()) {
            Order order = orders.removeFirst();
            System.out.println("Preparing: " + order.getDrink() +
                    " for client #" + order.getClient().getClientId());

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(order.getDrink() + " is ready and served to client #" +
                    order.getClient().getClientId());
        } else {
            System.out.println("No orders to serve.");
        }
    }
}










