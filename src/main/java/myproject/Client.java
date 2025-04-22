package myproject;

public class Client {
    private static int clientCounter = 0;
    private final String clientId;

    // Конструктор с параметром
    public Client(String clientId) {
        this.clientId = clientId;
    }

    // Конструктор без параметров
    public Client() {
        this.clientId = "Client-" + (++clientCounter);
    }

    public String getClientId() {
        return clientId;
    }

}


