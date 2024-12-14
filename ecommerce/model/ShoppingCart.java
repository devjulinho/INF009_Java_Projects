package ecommerce.model;

import java.util.Vector;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.Serializable;

public class ShoppingCart implements Serializable{
    public int id;
    public static int referenceId = 0;
    public Vector<Order> orders = new Vector<>();

    public ShoppingCart(){
        id = referenceId++;
    }

    public void display(HashMap<Integer, Product> allProducts){
        System.out.println("===== Shopping Cart ID -> " + this.id + "======");
        System.out.println("Number of orders -> " + orders.size());
        System.out.println("Total to pay -> $" + getTotalPrice(allProducts));
        System.out.println("_________________________");
        orders.forEach(o -> o.display(allProducts));
    }

    public static boolean removeOrderById(Customer currentUser, int id){
        Order order = currentUser.currentShoppingCart.orders.stream()
                                                            .filter(p -> p.id == id)
                                                            .findFirst()
                                                            .orElse(null);

        if (order == null){
            return false;
        }
        else{
            currentUser.currentShoppingCart.orders.remove(order);
            return true;
        }
    }

    public void removeAllOrders(Customer currentUser){
        currentUser.currentShoppingCart.orders.clear();
    }

    public double getTotalPrice(HashMap<Integer, Product> allProducts){
        double totalPrice = 0.0;
        Product product;

        for (Order order : orders){
            product = Product.getProductById(allProducts, order.productId);
            totalPrice += (product.price * order.orderedAmount);
        }
        return totalPrice;
    }

    public static Vector<Order> areProductsAvailable(ShoppingCart currentShoppingCart, HashMap<Integer, Product> allProducts){
        Vector<Order> unavailableProducts = null;

        for (Order order : currentShoppingCart.orders){
            if(order.orderedAmount > allProducts.get(order.productId).amount){
                unavailableProducts.add(order);
            }
        }
        return unavailableProducts;
    }


    public static void finishOrder(Customer currentUser, HashMap<Integer, Product> allProducts){
        Product product = null;

        for(Order order : currentUser.currentShoppingCart.orders){
            product = allProducts.get(order.productId);
            product.amount -= order.orderedAmount;
        }

        currentUser.previousShoppingCart.add(currentUser.currentShoppingCart);
    }

    private static ShoppingCart getCustomersMostExpensiveShoppingCart(User currentUser, HashMap<Integer, Product> allProducts){
        double referenceValue = 0;
        ShoppingCart mostExpensiveShoppingCart = null;

        for (ShoppingCart shoppingCart : ((Customer)currentUser).previousShoppingCart){
            if(shoppingCart.getTotalPrice(allProducts) > referenceValue){
                referenceValue = shoppingCart.getTotalPrice(allProducts);
                mostExpensiveShoppingCart = shoppingCart;
            }
        }
        return mostExpensiveShoppingCart;
    }

    public static ArrayList getMostExpensiveShoppingCart(HashMap<String, User> users, HashMap<Integer, Product> allProducts){
        ArrayList<Object> expensiveShoppingCartAndCustomer = new ArrayList<>();
        ShoppingCart mostExpensiveShoppingCart = new ShoppingCart();
        ShoppingCart referenceShoppingCart = null;
        User shoppingCartOwner = null;

        for (User user : users.values()){
            if(user instanceof Customer){
                referenceShoppingCart = getCustomersMostExpensiveShoppingCart(user, allProducts);
                if(referenceShoppingCart.getTotalPrice(allProducts) > mostExpensiveShoppingCart.getTotalPrice(allProducts)){
                    mostExpensiveShoppingCart = referenceShoppingCart;
                    shoppingCartOwner = user;
                }
            }
        }
        expensiveShoppingCartAndCustomer.add(mostExpensiveShoppingCart);
        expensiveShoppingCartAndCustomer.add(shoppingCartOwner);
        return expensiveShoppingCartAndCustomer;
    }
}
