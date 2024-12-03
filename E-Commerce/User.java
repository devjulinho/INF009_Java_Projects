import java.util.Scanner;
import java.util.Vector;

public class User{
    int id;
    String name;
    String email;
    String password;

    public User(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void display(User user){
        System.out.println("Name: " + this.name);
        System.out.println("E-mail: " + this.email);
        if (user instanceof Customer)
            System.out.println("Address: " + ((Customer)user).address);
    }

    public void login(Vector<User> users){
        Scanner scan = new Scanner(System.in);
        boolean successfulLogin = false;
        boolean successfulEmail = false;
        boolean successfulPassword = false;

        while(successfulLogin == false){
            while(successfulEmail == false){
                System.out.println("What is your e-mail?");
                String email = scan.nextLine();

                if(compareEmail(email, users) == false){
                    System.out.println("We don't have that e-mail in our database. Please, check correctly.");
                }

                successfulEmail = compareEmail(email, users);
            }

            for(int numberAttemps = 0; successfulPassword == false; numberAttemps++){
                System.out.println("What is your password?");
                String password = scan.nextLine();

                if(comparePassword(password, users) == false){
                    System.out.println("Wrong password. You have only " + (3 - numberAttemps) + " chances");

                if(numberAttemps == 3 && comparePassword(password, users) == false)
                    System.exit(0);
                }
                successfulPassword = comparePassword(password, users);
            }
            successfulLogin = true;
        }
        System.out.println("Welcome, Name...");
    }

    private boolean compareEmail(String email, Vector<User> users){
        for (int index = 0; index < users.size(); index++)
            if (users.get(index).email.equals(email))
                return true;
        return false;
    }

    private boolean comparePassword(String password, Vector<User> users){
        for (int index = 0; index < users.size(); index++)
            if (users.get(index).password.equals(password))
                return true;
        return false;
    }

}
