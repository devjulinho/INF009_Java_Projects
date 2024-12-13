package ecommerce.controller;

import ecommerce.model.User;
import ecommerce.model.Admin;
import ecommerce.model.Customer;
import ecommerce.model.Product;
import ecommerce.utils.SecurityUtils;

import java.util.HashMap;
import java.util.Scanner;

public class UIController{

    private static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static User login(HashMap<String, User> users) throws Exception{
        Scanner askEmail = new Scanner(System.in);
        Scanner askPassword = new Scanner(System.in);
        User loginUser = null;

        do{
            System.out.println("What is your e-mail?");
            String email = askEmail.nextLine();

            System.out.println("What is your password?");
            String typedPassword = askPassword.nextLine();

            loginUser = User.login(users, email, typedPassword);

            UIController.clearScreen();

            if (loginUser == null)
                System.out.println("Ops, your email or password is incorrect. Please, try again:");
        }while(loginUser == null);

        return loginUser;
    }

     public static int adminMenu(User currentUser, HashMap<String, User> users, HashMap<Integer, Product> allProducts) throws Exception{
        Scanner option = new Scanner(System.in);
        int menuOption = 0;

        while(menuOption >= 0){

            System.out.println("""
                Please, choose a action:
                1 - Create a new product.
                2 - Create a new user.
                3 - Report - More expensive order.
                4 - Report - Product with lowest inventory.
                0 - Exit.""");

            menuOption = option.nextInt();

            switch(menuOption){
                case 1:{
                    System.out.println("Create a new product");
                    break;
                }

                case 2:{
                    System.out.println("=== Create a new user ====");
                    ((Admin)currentUser).createUser(users);
                    break;
                }

                case 3:{
                    System.out.println("Report - More expensive order.");
                    break;
                }

                case 4:{
                    System.out.println("Report - Product with lowest inventory.");
                    break;
                }

                case 0:{
                    System.out.println("Thank you. I hope to see you soon.");
                    menuOption = -1;
                    break;
                }

                default:{
                    System.out.println("Ops, I think we don't have that option. Please, could you choose again?");
                    break;
                }
            }
        }
        return menuOption;
    }

}
