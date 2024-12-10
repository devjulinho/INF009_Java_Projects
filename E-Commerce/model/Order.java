import java.util.Vector;
import java.util.Scanner;

public class Order{
    int id;
    static int referenceId = 0;
    int productId;
    int orderedAmount;

    public Order(int productId, int orderedAmount){
        this.id = referenceId++;
        this.productId = productId;
        this.orderedAmount = orderedAmount;
    }

    public void display(Vector<Product> products){
        Product product = Product.getProductById(products, this.productId);
        System.out.println("Order id: " + this.id);
        System.out.println("Product: " + product.name);
        System.out.println("Amount in this order: " + this.orderedAmount);
        System.out.println("Total value: $" + this.orderedAmount * product.price);
        System.out.println("\n");
    }

    public static Order createOrder(Vector<Product> products){
        Scanner askInfo = new Scanner(System.in);
        int amountInfo;
        Product product;

        product = Product.getProductById(products);

        if(product.amount == 0){
            System.out.println("We don't have that product in our storage!");
            return null;
        }

        do {
            System.out.println("Please, inform how many units you want to buy:");
            amountInfo = askInfo.nextInt();

            if(product.amount < amountInfo || amountInfo <= 0){
                System.out.println("We only have " + product.amount + " units.");
            }
        } while (product.amount < amountInfo || amountInfo <= 0);

        product.amount -= amountInfo;

        Order order = new Order(product.id, amountInfo);

        return order;
    }

}
