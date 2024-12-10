import java.util.Vector;
import java.util.Scanner;

public class ShoppingCart{
    int id;
    static int referenceId = 0;
    Vector<Order> orders = new Vector<>();

    public ShoppingCart(){
        id = referenceId++;
    }

    public void display(){

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

    public void removeAllOrders(Vector<Product> products){
        for (Order order : orders){
            Product product = Product.getProductById(products, order.productId);
            product.amount += order.orderedAmount;
        }
    }

    public double getTotalPrice(Vector<Product> products){
        double totalPrice = 0.0;
        Product product;

        for (Order order : orders){
            product = Product.getProductById(products, order.productId);
            totalPrice += (product.price * order.orderedAmount);
        }

        return totalPrice;
    }

    public boolean finishOrder(Vector<Product> products, ShoppingCart currentShoppingCart, Vector<ShoppingCart> customerPreviousShoppingCart){
        Scanner askInfo = new Scanner(System.in);
        int selectedOption;

        System.out.println("Total price: $ " + getTotalPrice(products));

        do {
            System.out.println("""
                                  Do you want to finish this order?
                                  1 - Yes.
                                  2 - No.""");
            selectedOption = askInfo.nextInt();

            if (selectedOption < 1 || selectedOption > 2){
                System.out.println("Ops, I didn't get this option. Could you select a valid option, please:");
            }
        } while (selectedOption < 1 || selectedOption > 2);

        switch (selectedOption){
            case 1:{
                customerPreviousShoppingCart.add(currentShoppingCart);
                System.out.println("Thank you for buying with us!");
                return true;
            }
            case 2:{
                do {
                    System.out.println("""
                                        Please, choose one option:
                                        1 - I want to continue shopping.
                                        2 - I want to cancel this purchase.""");
                    selectedOption = askInfo.nextInt();

                    if (selectedOption < 1 || selectedOption > 2){
                        System.out.println("Ops, I didn't get this option. Could you select a valid option, please:");
                    }
                } while (selectedOption < 1 || selectedOption > 2);

                if (selectedOption == 1){
                    return false;
                }

                else{
                    currentShoppingCart.removeAllOrders(products);
                    currentShoppingCart.orders.clear();
                    System.out.println("Your purchase was cancelled and your Shopping Cart was cleaned!");
                    return true;
                }
            }
        }
        return false;
    }
}
