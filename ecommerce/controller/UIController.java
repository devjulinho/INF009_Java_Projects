package ecommerce.controller;

import ecommerce.model.User;
import ecommerce.model.Admin;
import ecommerce.model.Customer;
import ecommerce.model.Product;
import ecommerce.utils.SecurityUtils;
import ecommerce.model.Order;
import ecommerce.model.ShoppingCart;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

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

        System.out.println("Hello, " + currentUser.name);

        while(menuOption >= 0){

            System.out.println("""
                Please, choose a action:
                1 - Create a new product.
                2 - Create a new user.
                3 - Report all users.
                4 - Report - More expensive order.
                5 - Report - Product with lowest inventory.
                0 - Exit.""");

            menuOption = option.nextInt();

            switch(menuOption){
                case 1:{
                    createNewProductMenu(allProducts);
                    break;
                }

                case 2:{
                    System.out.println("=== Create a new user ====");
                    createNewUserMenu(users);
                    break;
                }

                case 3:{
                    User.reportAllUsers(users);
                    break;
                }

                case 4:{
                    System.out.println("Report - More expensive order.");
                    break;
                }

                case 5:{
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

    private static void createNewProductMenu(HashMap<Integer, Product> allProducts){
        Scanner askInfo = new Scanner(System.in);
        String name, description, category;
        double price;
        int id, amount;

        UIController.clearScreen();

        System.out.println("======= CREATING A NEW PRODUCT =======");
        System.out.println("Now, we will ask some informations about the product:");
        System.out.println("Please, inform the product name:");
        name = askInfo.nextLine();

        System.out.println("Please, inform description for the new product:");
        description = askInfo.nextLine();

        System.out.println("Please, inform the product category:");
        category = askInfo.nextLine();

        System.out.println("Please, inform the product price:");
        price = askInfo.nextDouble();

        System.out.println("How many products do you have to sell?");
        amount = askInfo.nextInt();

        Product.createNewProduct(allProducts, name, description, price, amount, category);

        System.out.println("We successfully create a new product. Now, they're on sale!");
    }

    private static void createNewUserMenu(HashMap<String, User> users) throws Exception{
        Scanner askInfo = new Scanner(System.in);
        int menuOption = -1;
        User newUser = null;
        String email, name, password, address;

        while (menuOption < 1 || menuOption > 2){
            System.out.println("What user type do you want do create?\n" +
                "1- Administrator\n" +
                "2- Customer");

            menuOption = askInfo.nextInt();

            if (menuOption < 1 || menuOption > 2)
                System.out.println("Invalid option! Please, try again.");
        }

        askInfo.nextLine();

        do{
            System.out.println("What's the user e-mail? (Please, use our domain: @ecommerce.com)");

            email = askInfo.nextLine();

            if (users.get(email) != null)
                System.out.println("We already have this e-mail in our database. Please, inform another e-mail or write 'Exit'.");

            if (!email.endsWith("@ecommerce.com"))
                System.out.println("Ops, I think you put a incorrent domain. Please, use @ecommerce.com");

            if(email.equals("Exit"))
                return;
        } while (users.get(email) != null || !email.endsWith("@ecommerce.com"));

        System.out.println("What's the user name?");
        name = askInfo.nextLine();
        System.out.println("What's the user password?");
        password = askInfo.nextLine();

        switch (menuOption){
            case 1:{
                Admin.createUser(users, name, email, password, null);
                System.out.println("New administrator was created!");
                break;
            }

            case 2:{
                System.out.println("What's the user address?");
                address = askInfo.nextLine();
                Admin.createUser(users, name, email, password, address);
                System.out.println("New customer was created!");
                break;
            }
        }
    }

    public static void customerMenu(User currentUser, HashMap<String, User> users, HashMap<Integer, Product> allProducts){
        Scanner askInfo = new Scanner(System.in);
        int menuOption = 0;

        System.out.println("Hello, " + currentUser.name);

        while(menuOption >= 0){

            System.out.println("""
                Please, choose a action:
                1 - Start a new order.
                2 - View my purchase history.
                0 - Exit.""");

            menuOption = askInfo.nextInt();

            switch(menuOption){
                case 1:{
                    ((Customer)currentUser).createShoppingCart();
                    productMenu((Customer)currentUser, allProducts);
                    break;
                }

                case 2:{
                    System.out.println("==== My purchase history ====");
                    ((Customer)currentUser).previousShoppingCart.forEach(c -> c.display(allProducts));
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

    private static void productMenu(Customer currentUser, HashMap<Integer, Product> allProducts){
        Product product = null;
        Scanner askInfo = new Scanner(System.in);
        int menuOption;
        boolean stayMenu = true;

        while(stayMenu){
            System.out.println("""
                ==== Starting a new order ====
                1 - See all available products.
                2 - See a product by id.
                3 - Add a product in Shopping Cart (by id).
                4 - See my current Shopping Cart.
                5 - Remove a product from Shopping Cart.
                6 - Finish order.""");

            menuOption = askInfo.nextInt();
            askInfo.nextLine();

            switch(menuOption){
                case 1:{
                    UIController.clearScreen();
                    System.out.println("===== AVAILABLE PRODUCTS =====");
                    Product.displayAllAvailableProducts(allProducts);
                    System.out.println("Press enter to return to menu:");
                    askInfo.nextLine();
                    UIController.clearScreen();
                    break;
                }

                case 2:{
                    UIController.clearScreen();
                    System.out.println("Please, inform the id:");
                    int productId = askInfo.nextInt();
                    product = Product.getProductById(allProducts, productId);
                    if(product != null)
                        product.display();
                    else
                        System.out.println("We don't have that product id!");
                    break;
                }

                case 3:{
                    UIController.clearScreen();
                    newOrderMenu(currentUser, allProducts);
                    break;
                }

                case 4:{
                    UIController.clearScreen();
                    System.out.println("===== CURRENT SHOPPING CART =====");
                    currentUser.currentShoppingCart.display(allProducts);
                    break;
                }

                case 5:{
                    System.out.println("Remove a product from Shopping Cart");
                    removeOrderMenu(currentUser);
                    break;
                }

                case 6:{
                    System.out.println("Finish order");
                    stayMenu = !finishOrderMenu(currentUser, allProducts);
                    break;
                }

                default:{
                    System.out.println("Ops, I think we don't have that option. Please, could you choose again?");
                    break;
                }
            }
        }
    }

    private static void newOrderMenu(Customer currentUser, HashMap<Integer, Product> allProducts){
        Scanner askInfo = new Scanner(System.in);
        int productId;
        int amountInfo;
        Product product;

        Product.displayAllAvailableProducts(allProducts);

        do{
            System.out.println("Please, inform the product id you want to buy");
            productId = askInfo.nextInt();

            product = Product.getProductById(allProducts, productId);

            if(product == null || product.amount == 0){
                System.out.println("We don't have that product in our storage!");
                return;
            }
        } while(product == null || product.amount == 0);

        do {
            System.out.println("Please, inform how many units you want to buy:");
            amountInfo = askInfo.nextInt();

            if(product.amount < amountInfo || amountInfo <= 0){
                System.out.println("We only have " + product.amount + " units.");
            }
        } while (product.amount < amountInfo || amountInfo <= 0);

        Order.createNewOrder(currentUser, product.id, amountInfo);

        System.out.println("Your order was successfully added to your Shopping Cart!");
    }

    public static void removeOrderMenu(Customer currentUser){
        Scanner askInfo = new Scanner(System.in);
        int idInfo;
        boolean status;

        do{
            System.out.println("Please, inform the Order ID that you want to remove:");
            idInfo = askInfo.nextInt();

            if(idInfo < 0 || idInfo > Order.referenceId)
                System.out.println("We didn't find a order with this id in your ShoppingCart. Please, inform another id:");
        }while (idInfo < 0 || idInfo > Order.referenceId);

        status = ShoppingCart.removeOrderById(currentUser, idInfo);

        if (status == false)
            System.out.println("We didn't find that order id in your Shopping Cart.");
        else
            System.out.println("We removed that order from your Shopping Cart!");
    }

    public static boolean finishOrderMenu(Customer currentUser, HashMap<Integer, Product> allProducts){
        Scanner askInfo = new Scanner(System.in);
        int selectedOption;
        Vector<Order> unavailableProducts = null;

        if(currentUser.currentShoppingCart.orders.size() == 0){
            System.out.println("You don't have products in your Shopping Cart. Press enter to continue shopping.");
            askInfo.nextLine();
            return true;
        }

        currentUser.currentShoppingCart.display(allProducts);

        do {
            System.out.println("""
                                  Do you want to finish this order?
                                  1 - Yes.
                                  2 - No.""");
            selectedOption = askInfo.nextInt();

            if (selectedOption < 1 || selectedOption > 2){
                System.out.println("Ops, I didn't get this option. Could you select a valid option, please:");
            }
        } while (selectedOption < 1 || selectedOption > 2);

        switch (selectedOption){
            case 1:{
                unavailableProducts = ShoppingCart.areProductsAvailable(currentUser.currentShoppingCart, allProducts);

                if(unavailableProducts != null){
                    System.out.println("We don't have storage enough for some orders:");
                    for (Order order : unavailableProducts){
                        System.out.println("Order ID: " + order.id + " - Order Product: " + allProducts.get(order.productId).name);
                        System.out.println("Amount required: " + order.orderedAmount + " - Storage Amount: " + allProducts.get(order.productId).amount);
                        System.out.println("\n");
                    }
                    return false;
                }
                else{
                    ShoppingCart.finishOrder(currentUser, allProducts);
                    System.out.println("Thank you for buying with us!");
                    return true;
                }
            }
            case 2:{
                do {
                    System.out.println("""
                                        Please, choose one option:
                                        1 - I want to continue shopping.
                                        2 - I want to cancel this purchase.""");
                    selectedOption = askInfo.nextInt();

                    if (selectedOption < 1 || selectedOption > 2){
                        System.out.println("Ops, I didn't get this option. Could you select a valid option, please:");
                    }
                } while (selectedOption < 1 || selectedOption > 2);

                if (selectedOption == 1){
                    return false;
                }

                else{
                    currentUser.currentShoppingCart.removeAllOrders(currentUser);
                    System.out.println("Your purchase was cancelled and your Shopping Cart was cleaned!");
                    return true;
                }
            }
        }
        return false;
    }

    public static void initializationMessage(){
        System.out.println("Welcome to ecommerce.com!");
    }

    public static void finalizationMessage(){
        System.out.println("Thank you for using ecommerce.com!");
    }
}
