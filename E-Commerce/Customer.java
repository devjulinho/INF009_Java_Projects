import java.util.Scanner;
import java.util.Vector;

public class Customer extends User{
    String address;
    //Historico - pos-produto

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
                    System.out.println("Add product");
                    System.out.println("Remove a product from Shopping Cart");
                    System.out.println("View Shopping Cart");
                    System.out.println("Finish order");
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
}
