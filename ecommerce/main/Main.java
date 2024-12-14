package ecommerce.main;

import ecommerce.model.Admin;
import ecommerce.model.User;
import ecommerce.model.Customer;
import ecommerce.model.Product;
import ecommerce.model.Order;
import ecommerce.model.ShoppingCart;
import ecommerce.utils.SecurityUtils;
import ecommerce.controller.UIController;

import java.util.HashMap;

public class Main{
    public static void main(String[] Args) throws Exception{
        HashMap<String, User> users = new HashMap<>();
        HashMap<Integer, Product> allProducts = new HashMap<>();
        //Welcome space
        //only in the first creation, do it in the serialization
        //to push Referenceid when desserialize

        users.put("admin", new Admin("Default Account", "admin", "admin"));
        users.put("julio@ifba.edu", new Admin("Julio", "julio@ifba.edu", "testing"));
        users.put("maria@ifba.edu", new Customer("Maria", "maria@ifba.edu", "testing2", "Rua A"));

        allProducts.put(Product.referenceId, new Product("feijao", "feijao de caldo", 7.95, 30, "Alimentos"));
        allProducts.put(Product.referenceId, new Product("carne", "picanha", 23.95, 10, "Alimentos"));
        allProducts.put(Product.referenceId, new Product("farinha", "farinha de mandioca", 4.75, 20, "Alimentos"));

        User currentUser = UIController.login(users);

        System.out.println("Hello, " + currentUser.name);

        currentUser.menu(currentUser, users, allProducts);
    }
}
