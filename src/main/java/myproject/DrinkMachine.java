package myproject;

import java.util.ArrayDeque;
import java.util.Deque;

public class DrinkMachine {
    private final int machineId;
    private static int idCounter = 0;
    private static final int queueMax = 10;
    private static final Deque<Order> orders = new ArrayDeque<>();

    public DrinkMachine() {
        this.machineId = idCounter++;
    }

    public static void addOrder(Order order) {
        if (orders.size() < queueMax) {
            orders.addLast(order);
            System.out.println("Order added to the queue: " + order.getDrink() + " for client #" + order.getClient().getClientId());
        } else {
            System.out.println("Queue is full. Order was not accepted.");
        }
    }

    public void serveNext() {
        if (!orders.isEmpty()) {
            Order order = orders.removeFirst();
            System.out.println("Machine " + (machineId + 1) + " is now preparing: " + order.getDrink() + " for client #" + order.getClient().getClientId());
            // Симуляция времени на приготовление
            try {
                Thread.sleep(2000); // Симуляция времени, которое машина тратит на приготовление (2 секунды)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Machine " + (machineId + 1) + ": " + order.getDrink() + " is ready and served to client #" + order.getClient().getClientId());
        } else {
            System.out.println("Machine " + (machineId + 1) + " found no orders to serve.");
        }
    }

    public static int getQueueSize() {
        return orders.size();
    }

    public static Deque<Order> getOrders() {
        return orders;
    }

    public void serveAllOrders() {
        System.out.println("\nStarting to process all orders...");
        while (!orders.isEmpty()) {
            serveNext();
        }
        System.out.println("\nAll orders have been processed.");
    }
}








