package myproject;

import java.util.List;

public class ClientSpawner {

    public void spawnClients() {
        // Передаем строку в конструктор, например, просто уникальные имена
        List<Client> clients = List.of(
                new Client("Client 1"),
                new Client("Client 2"),
                new Client("Client 3")
        );

        clients.get(0).addDrink(new BlackTea("Greenfield", 1.0));
        clients.get(1).addDrink(new Coffee("Nescafe", 2.0));
        clients.get(2).addDrink(new GreenTea("Greenfield", 1.5));
    }
}



