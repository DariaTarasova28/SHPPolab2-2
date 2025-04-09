package myproject;

public class LoggingMenu {
    private final Menu menu;

    public LoggingMenu(Menu menu) {
        this.menu = menu;
    }

    public Object choose() {
        System.out.println("Menu.choose() called");
        Object result = null;
        try {
            result = menu.choose();
            System.out.println("Menu.choose() returned " + result);
        } catch (Exception e) { // теперь ловим просто Exception (или вообще можно убрать try-catch, если не нужен)
            System.out.println("Exception while calling choose: " + e.getMessage());
        }
        return result;
    }
}
