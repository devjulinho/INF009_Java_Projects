import java.util.Scanner;
import java.util.Vector;

public class Customer extends User{
    String address;
    ShoppingCart actualShoppingCart;
    Vector<ShoppingCart> previousShoppingCart;

    public Customer(String name, String email, String password, String address) throws Exception{
        super(name, email, password);
        this.address = address;
    }

    public void customerMenu(){
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
                    break;
                }

                case 2:{
                    System.out.println("==== View my purchase history ====");
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

        System.out.println("""
            ==== Starting a new order ====
            1 - See all available products.
            2 - See a product by id.
            3 - Add a product in Shopping Cart (by id).
            4 - See my actual Shopping Cart.
            5 - Remove a product from Shopping Cart.
            6 - Finish order.""");

        menuOption = option.nextInt();

        switch(menuOption){
            case 1:{
                System.out.println("===== AVAILABLE PRODUCTS =====");
                products.forEach(p -> p.display());
                break;
            }

            case 2:{
                product = Product.getProductById(products);
                product.display();
                break;
            }

            case 3:{



                //criar um order com o id do produto
                //adicionar order no ShoppingCart
                break;
            }

            case 4:{
                System.out.println("See my actual Shopping Cart");
                //mostrar todas as orders com Id
                //pedir o id da order para ser deletada
                break;
            }

            case 5:{
                System.out.println("Remove a product from Shopping Cart");
                //mostrar todas as orders com Id
                //pedir o id da order para ser deletada
                break;
            }

            case 6:{
                System.out.println("Finish order");
                //
                break;
            }

            default:{
                System.out.println("Ops, I think we don't have that option. Please, could you choose again?");
                break;
            }
        }
    }
}
