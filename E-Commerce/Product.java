import java.util.Vector;
import java.util.Scanner;

public class Product{
    int id;
    static int referenceId = -1;
    String name;
    String description;
    float price;
    int amount;
    String category;

    public Product(String name, String description, float price, int amount, String category){
        this.id = referenceId++;
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
        this.category = category;
    }

    public void display(){
        System.out.println("******* Product id: " + this.id + "*******");
        System.out.println("---- Name: " + this.name + "----");
        System.out.println("---- Price: $" + this.price + "----");
        System.out.println("---- Amount: " + this.amount + "----");
    }

    // public void showStoredProducts(Vector<Product> products){
    //     for(int productIndex = 0; productIndex < products.size(); productIndex++){
    //         if(products.get(productIndex).amount > 0){
    //             products.get(productIndex).display();
    //             System.out.println("\n");
    //         }
    //     }
    // }

    public static Product getProductById(Vector<Product> products){
        Scanner selectedId = new Scanner(System.in);
        int idOption;
        System.out.println("Please, inform a product id:");
        idOption = selectedId.nextInt();
        return products.get(idOption);
    }
}
