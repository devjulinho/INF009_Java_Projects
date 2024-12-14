package ecommerce.main;

import ecommerce.model.Admin;
import ecommerce.model.User;
import ecommerce.model.Customer;
import ecommerce.model.Product;
import ecommerce.model.Order;
import ecommerce.model.ShoppingCart;
import ecommerce.utils.SecurityUtils;
import ecommerce.controller.UIController;
import ecommerce.controller.PersistenceController;

import java.util.HashMap;

public class Main{
    public static void main(String[] Args) throws Exception{
        HashMap<String, User> users = new HashMap<>();
        HashMap<Integer, Product> allProducts = new HashMap<>();

        PersistenceController.initialization(users, allProducts);

        System.out.println("---- POS-PERSISTENTE MOMENT -----");
        System.out.println(users);
        System.out.println(allProducts);

        //User.reportAllUsers(users);
        // allProducts.get(0).display();

        //to push Referenceid when desserialize

        User currentUser = UIController.login(users);

        currentUser.menu(currentUser, users, allProducts);

        PersistenceController.finalization(users, allProducts);
    }
}
