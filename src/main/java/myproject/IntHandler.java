// IntHandler.java
package myproject;

public class IntHandler implements Handler {
    private Handler next;

    public IntHandler() {
        this.next = null;
    }

    public IntHandler(Handler next) {
        this.next = next;
    }

    @Override
    public void setNext(Handler handler) {
        this.next = handler;
    }

    @Override
    public boolean handle(String input) {
        try {
            int result = Integer.parseInt(input);
            if (result >= 0 && result <= 3) {
                System.out.println("Input validated: " + result);
                if (next != null) return next.handle(input);
                return true;
            } else {
                System.out.println("Number must be between 0 and 3.");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format.");
            return false;
        }
    }
}


