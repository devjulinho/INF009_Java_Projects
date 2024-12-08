import java.util.Vector;
import java.util.Scanner;

public class ShoppingCart{
    int id;
    static int referenceId = 0;
    Vector<Order> orders = new Vector<>();

    public ShoppingCart(){
        id = referenceId++;
    }

    public void removeOrderById(Vector<Product> products){
        Scanner askInfo = new Scanner(System.in);
        int idInfo;

        do{
            System.out.println("Please, inform the order id that you want to remove:");
            idInfo = askInfo.nextInt();

            if(idInfo < 0 || idInfo > Order.referenceId)
                System.out.println("We didn't find a order with this id in your ShoppingCart. Please, inform another id:");
        }while (idInfo < 0 || idInfo > Order.referenceId);

        int id = idInfo;

        Order order = this.orders.stream()
                          .filter(p -> p.id == id)
                          .findFirst()
                          .orElse(null);

        if (order != null){
            this.orders.remove(order);
            Product product = Product.getProductById(products, order.productId);
            product.amount += order.orderedAmount;
            System.out.println("Order was removed.");

        }
        else{
            System.out.println("We didn't find a order with this id in your ShoppingCart.");
        }
    }
}
