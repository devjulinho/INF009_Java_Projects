package ecommerce.model;

import java.util.Vector;
import java.util.Scanner;
import java.util.HashMap;
import java.io.Serializable;

public class Order implements Serializable{
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
        System.out.println("\t " + this.id + " \t | \t " + product.name + " \t | \t " + this.orderedAmount + " \t | \t $" + this.orderedAmount * product.price + "\t |");
    }

    public static void createNewOrder(Customer currentUser, int id, int orderedAmount){
        currentUser.currentShoppingCart.orders.add(new Order(id, orderedAmount));
    }
}
