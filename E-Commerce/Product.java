import java.util.Vector;

public class Product{
    static int id;
    int referenceId = -1;
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
        System.out.println("---- Product id: " + this.id + "----");
        System.out.println("---- Name: " + this.name + "----");
        System.out.println("---- Price: $" + this.price + "----");
        System.out.println("---- Amount: " + this.amount + "----");
    }
    
    public void showStoredProducts(Vector<Product> products){
        for(int productIndex = 0; productIndex < products.size(); productIndex++){
            products.get(productIndex).display();
            System.out.println("\n");
        }
    }

    public void showProductById(Vector<Product> products, int id){
        products.get(id).display();
    }

}