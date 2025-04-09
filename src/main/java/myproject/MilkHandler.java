package myproject;
public class MilkHandler implements Handler{
    Handler next;
    MilkHandler(Handler handler){
        this.next = handler;
    }

    public MilkHandler() {
        this.next = null;
    }

    @Override
    public void setNext(Handler handler) {
        this.next = handler;
    }

    @Override
    public boolean handle(String string) {
        if (string.substring(0, 1).equalsIgnoreCase("y")) {
            return true;
        } else {
            return false;
        }
    }
}
