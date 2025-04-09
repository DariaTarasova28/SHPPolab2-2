package myproject;

public class IntHandler implements Handler {
    Handler next;
    IntHandler(Handler handler){
        this.next = handler;
    }

    public IntHandler() {
        next = null;
    }

    @Override
    public void setNext(Handler handler) {
        this.next = handler;
    }

    @Override
    public boolean handle(String string) {
        try {
            int result = Integer.parseInt(string);
            if (result > -1 && result < 4){
                System.out.print("Chain passed!");
                return true;
            }
            else throw new IllegalArgumentException("Answer must be between 0 and 3");
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
            return false;
        }
    }
}
