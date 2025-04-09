package myproject;

public class ParseHandler implements Handler {
    Handler next;
    ParseHandler(Handler handler){
        this.next = handler;
    }
    public ParseHandler() {
        this.next = null;
    }

    @Override
    public void setNext(Handler handler) {
        this.next = handler;
    }

    @Override
    public boolean handle(String string) {
        try {
            Integer.parseInt(string);
            if (next != null)
                return this.next.handle(string);
            else return true;
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
            return false;
        }
    }
}
