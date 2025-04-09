package myproject;

import java.util.ArrayDeque;
import java.util.Deque;

public class DrinkMachine {
    private final int machineId;
    private static int idCounter = 0;
    private static int queueMax = 10;
    private static final Deque<Order> orders = new ArrayDeque<>();

    public DrinkMachine() {
        this.machineId = idCounter++;
    }

    public static void addOrder(Order order) {
        if (orders.size() < queueMax) {
            orders.addLast(order);
            System.out.println("Order added to the queue: " + order.drink);
        } else {
            System.out.println("Queue is full. Order was not accepted.");
        }
    }

    // Новый метод serveNext (вместо processNextOrder)
    public void serveNext() {
        if (!orders.isEmpty()) {
            Order order = orders.removeFirst();
            System.out.println("Machine " + (machineId + 1) + ": " + order.drink + " was given to client #" + order.client.getClientId());
        } else {
            System.out.println("Machine " + (machineId + 1) + " found no orders to serve.");
        }
    }

    public int getMachineId() {
        return machineId;
    }

    public static int getQueueMax() {
        return queueMax;
    }

    public static void setQueueMax(int queueMax) {
        DrinkMachine.queueMax = queueMax;
    }

    public static int getQueueSize() {
        return orders.size();
    }

    public static Deque<Order> getOrders() {
        return orders;
    }
}


