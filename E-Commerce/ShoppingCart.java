import java.util.Vector;

public class ShoppingCart{
    int id;
    static int referenceId = 0;
    Vector<Order> orders = new Vector<>();

    public ShoppingCart(){
        id = referenceId++;
    }
}
