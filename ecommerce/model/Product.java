package ecommerce.model;

import java.util.Vector;
import java.util.Scanner;
import java.util.HashMap;

public class Product{
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
        System.out.println("******* Product id: " + this.id + "*******");
        System.out.println("Name: " + this.name);
        System.out.println("Price: $" + this.price);
        System.out.println("Amount: " + this.amount);
        System.out.println("\n");
    }

    public static void displayAllAvailableProducts(HashMap<Integer, Product> allProducts){
        allProducts.stream()
                   .filter((k, p) -> p.amount > 0)
                   .forEach((k, p) -> p.display());
    }


    public static Product getProductById(HashMap<Integer, Product> allProducts, int productId){
        return products.get(productId);
    }
}
