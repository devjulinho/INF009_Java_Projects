import java.util.Scanner;
import java.util.Vector;

public class Customer extends User{
    String address;
    ShoppingCart currentShoppingCart;
    Vector<ShoppingCart> previousShoppingCart = new Vector<>();

    public Customer(String name, String email, String password, String address) throws Exception{
        super(name, email, password);
        this.address = address;
    }

    public void customerMenu(Vector<Product> products){
        Scanner option = new Scanner(System.in);
        int menuOption = 0;

        while(menuOption >= 0){

            System.out.println("""
                Please, choose a action:
                1 - Start a new order.
                2 - View my purchase history.
                0 - Exit.""");

            menuOption = option.nextInt();

            switch(menuOption){
                case 1:{
                    System.out.println("==== Starting a new order ====");
                    this.currentShoppingCart = new ShoppingCart();
                    productMenu(products);
                    break;
                }

                case 2:{
                    System.out.println("==== My purchase history ====");
                    previousShoppingCart.forEach(c -> c.display());
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

    public void productMenu(Vector<Product> products){
        Product product = null;
        Scanner option = new Scanner(System.in);
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

            menuOption = option.nextInt();
            option.nextLine();

            switch(menuOption){
                case 1:{
                    System.out.println("===== AVAILABLE PRODUCTS =====");
                    products.stream()
                            .filter(p -> p.amount > 0)
                            .forEach(p -> p.display());
                    System.out.println("Press enter to return to menu:");
                    option.nextLine();
                    break;
                }

                case 2:{
                    product = Product.getProductById(products);
                    product.display();
                    break;
                }

                case 3:{
                    Order actualOrder = Order.createOrder(products);
                    if(actualOrder == null)
                        System.out.println("Order cancelled.");
                    else{
                        this.currentShoppingCart.orders.add(actualOrder);
                        System.out.println("Order was added to your shopping cart.");
                    }
                    break;
                }

                case 4:{
                    System.out.println("See my current Shopping Cart");
                    this.currentShoppingCart.orders.forEach(o -> o.display(products));
                    break;
                }

                case 5:{
                    System.out.println("Remove a product from Shopping Cart");
                    this.currentShoppingCart.removeOrderById(products);
                    break;
                }

                case 6:{
                    System.out.println("Finish order");
                    stayMenu = !currentShoppingCart.finishOrder(products, currentShoppingCart, previousShoppingCart);
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
