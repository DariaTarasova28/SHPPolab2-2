package myproject;

public class LoggingMenu {
    private final Menu menu;

    public LoggingMenu(Menu menu) {
        this.menu = menu;
    }

    public boolean choose(Client client) {
        System.out.println("Menu.choose(client) called for Client #" + client.getClientId());
        boolean result = false;
        try {
            result = menu.choose(client);
            System.out.println("Menu.choose(client) returned: " + result);
        } catch (Exception e) {
            System.out.println("Exception while calling choose(client): " + e.getMessage());
        }
        return result;
    }
}


