package myproject;

import java.util.ArrayDeque;
import java.util.Deque;

public class DrinkMachine {

    private static final int queueMax = 10;
    private static final Deque<Order> orders = new ArrayDeque<>();

    // Добавление заказа в очередь
    public static void addOrder(Order order) {
        if (orders.size() < queueMax) {
            orders.addLast(order);
            System.out.println("Order added to the queue: " + order.getDrink() +
                    " for client #" + order.getClient().getClientId());
        } else {
            System.out.println("Queue is full. Order was not accepted.");
        }
    }

    // Получение размера очереди
    public static int getQueueSize() {
        return orders.size();
    }

    // Обслуживание следующего заказа
    public void serveNext() {
        if (!orders.isEmpty()) {
            Order order = orders.removeFirst();  // Обслуживаем первый заказ
            System.out.println("Preparing: " + order.getDrink() +
                    " for client #" + order.getClient().getClientId());

            try {
                Thread.sleep(2000);  // Симуляция времени приготовления напитка
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(order.getDrink() + " is ready and served to client #" +
                    order.getClient().getClientId());
        } else {
            System.out.println("No orders to serve.");
        }
    }

    // Обслуживание всех заказов
    public void serveAllOrders() {
        System.out.println("\nStarting to process all orders...");
        while (!orders.isEmpty()) {
            serveNext();
        }
        System.out.println("\nAll orders have been processed.");
    }
}










