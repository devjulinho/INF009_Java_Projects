package ecommerce.model;

import ecommerce.controller.UIController;

import java.util.Scanner;
import java.util.Vector;
import java.util.HashMap;
import java.io.Serializable;

public class Admin extends User implements Serializable{
        private static final long serialVersionUID = 1L;

    public Admin(String name, String email, String password) throws Exception{
        super(name, email, password);
    }

        public void display(){
        System.out.println("=====> User " + this.id + " <=====");
        System.out.println("Name: " + this.name);
        System.out.println("E-mail: " + this.email + "\n");
    }

    public void menu(User currentUser, HashMap<String, User> users, HashMap<Integer, Product> allProducts) throws Exception{
        UIController.adminMenu(currentUser, users, allProducts);
    }

    public static void createUser(HashMap<String, User> users, String name, String email, String password, String address) throws Exception{
        if(address == null)
            users.put(email, new Admin(name, email, password));
        else
            users.put(email, new Customer(name, email, password, address));
    }

}
