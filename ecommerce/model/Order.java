package ecommerce.model;

import java.util.Vector;
import java.util.Scanner;
import java.util.HashMap;

public class Order{
    public int id;
    public static int referenceId = 0;
    public int productId;
    public int orderedAmount;

    public Order(int productId, int orderedAmount){
        this.id = referenceId++;
        this.productId = productId;
        this.orderedAmount = orderedAmount;
    }

    public void display(HashMap<Integer, Product> allProducts){
        Product product = Product.getProductById(allProducts, this.productId);
        System.out.println("Order id: " + this.id);
        System.out.println("Product: " + product.name);
        System.out.println("Amount in this order: " + this.orderedAmount);
        System.out.println("Order value: $" + this.orderedAmount * product.price);
        System.out.println("\n");
    }

    public static void createNewOrder(Customer currentUser, int id, int orderedAmount){
        currentUser.currentShoppingCart.orders.add(new Order(id, orderedAmount));
    }

}
