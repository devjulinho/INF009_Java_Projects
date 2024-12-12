package ecommerce.model;

import java.util.Scanner;
import java.util.Vector;

public class Admin extends User{

    public Admin(String name, String email, String password) throws Exception{
        super(name, email, password);
        //System.out.println("I am here!");
    }

    public void createUser(Vector<User> users) throws Exception{
        Scanner userOption = new Scanner(System.in);
        Scanner info = new Scanner(System.in);
        int menuOption = -1;
        User newUser = null;

        while (menuOption < 1 || menuOption > 2){
            System.out.println("What user type do you want do create?\n" +
                "1- Administrator\n" +
                "2- Customer");

            menuOption = userOption.nextInt();

            if (menuOption < 1 || menuOption > 2)
                System.out.println("Invalid option! Please, try again.");
        }

        System.out.println("What's the user e-mail?");
        String email = info.nextLine();
        //System.out.println(compareEmail(email, users));

        while (compareEmail(email, users) != -1){
            System.out.println("We already have this e-mail in our database. Please, inform another e-mail or write 'Exit'.");
            email = info.nextLine();
        }

        if(email.equals("Exit"))
            return;

        System.out.println("What's the user name?");
        String name = info.nextLine();
        System.out.println("What's the user password?");
        String password = info.nextLine();

        switch (menuOption){
            case 1:{
                newUser = new Admin(name, email, password);
                System.out.println("New administrator was created!");
                break;
            }

            case 2:{
                System.out.println("What's the user address?");
                String address = info.nextLine();
                newUser = new Customer(name, email, password, address);
                System.out.println("New customer was created!");
                break;
            }
        }
        users.add(newUser);
    }

    public void adminMenu(Vector<User> users) throws Exception{
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
                    createUser(users);
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

    }

}
