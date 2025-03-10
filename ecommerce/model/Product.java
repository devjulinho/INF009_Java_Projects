package ecommerce.model;

import java.util.Vector;
import java.util.Scanner;
import java.util.HashMap;
import java.io.Serializable;

public class Product implements Serializable{
    public int id;
    public static int referenceId = 0;
    public String name;
    public String description;
    public double price;
    public int amount;
    public String category;

    public Product(String name, String description, double price, int amount, String category){
        this.id = referenceId++;
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
        this.category = category;
    }

    public static void createNewProduct(HashMap<Integer, Product> allProducts, String name, String description, double price, int amount, String category){
        allProducts.put(Product.referenceId, new Product(name, description, price, amount, category));
    }

    public void display(){
        System.out.println("\t " + this.id + " \t | \t " + this.name + " \t | \t " + this.price + " \t | \t " + this.amount + " \t |");
    }

    public static void displayAllAvailableProducts(HashMap<Integer, Product> allProducts){
        System.out.println("\t ID \t | \t NAME \t | \t PRICE \t | \t AMOUNT \t |");
        allProducts.entrySet().stream()
                              .filter(entry -> entry.getValue().amount > 0)
                              .forEach(entry -> entry.getValue().display());
    }


    public static Product getProductById(HashMap<Integer, Product> allProducts, int productId){
        return allProducts.get(productId);
    }

    public static void lowestInventory(HashMap<Integer, Product> allProducts){
        System.out.println("\t ID \t | \t NAME \t | \t PRICE \t | \t AMOUNT \t |");
        allProducts.entrySet().stream()
                              .filter(entry -> entry.getValue().amount < 10)
                              .forEach(entry -> entry.getValue().display());
    }
}
