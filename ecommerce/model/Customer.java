package ecommerce.model;

import ecommerce.controller.UIController;

import java.util.Scanner;
import java.util.Vector;
import java.util.HashMap;
import java.io.Serializable;

public class Customer extends User implements Serializable{
    private static final long serialVersionUID = 1L;
    private String address;
    public ShoppingCart currentShoppingCart;
    public Vector<ShoppingCart> previousShoppingCart = new Vector<>();

    public Customer(String name, String email, String password, String address) throws Exception{
        super(name, email, password);
        this.address = address;
    }

        public void display(){
        System.out.println("=====> User " + this.id + " <=====");
        System.out.println("Name: " + this.name);
        System.out.println("E-mail: " + this.email);
        System.out.println("Address: " + this.address + "\n");
    }

    public void menu(User currentUser, HashMap<String, User> users, HashMap<Integer, Product> allProducts){
        UIController.customerMenu(currentUser, users, allProducts);
    }

    public void createShoppingCart(){
        currentShoppingCart = new ShoppingCart();
    }
}
