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
        System.out.println(compareEmail(email, users));

        while (compareEmail(email, users) != -1){
            System.out.println("We already have this e-mail in our database. Please, inform another e-mail or write 'Exit'.");
            email = info.nextLine();
        }


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

}
