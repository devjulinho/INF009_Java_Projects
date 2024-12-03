import java.util.Scanner;

public class Customer extends User{
    String address;
    //Historico - pos-produto

    public Customer(String name, String email, String password, String address){
        super(name, email, password);
        this.address = address;
    };

}
