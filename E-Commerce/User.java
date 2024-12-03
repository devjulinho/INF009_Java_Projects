import java.util.Scanner;
import java.util.Vector;
import java.util.Arrays;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKeyFactory;

public class User{
    int id;
    String name;
    String email;
    byte[] hashingPassword;
    byte[] salt;

    public User(String name, String email, String password) throws Exception{
        this.name = name;
        this.email = email;
        this.salt = generateSalt();
        this.hashingPassword = encryptPassword(password, salt);
    }

    public void display(User user){
        System.out.println("Name: " + this.name);
        System.out.println("E-mail: " + this.email);
        if (user instanceof Customer)
            System.out.println("Address: " + ((Customer)user).address);
    }

    public void login(Vector<User> users) throws Exception{
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
                String typedPassword = scan.nextLine();

                if(comparePassword(encryptPassword(typedPassword, users.get(0).salt), users) == false){
                    System.out.println("Wrong password. You have only " + (3 - numberAttemps) + " chances");

                if(numberAttemps == 3 && comparePassword(encryptPassword(typedPassword, users.get(0).salt), users) == false)
                    System.exit(0);
                }
                successfulPassword = comparePassword(encryptPassword(typedPassword, users.get(0).salt), users);
            }
            successfulLogin = true;
        }
        System.out.println("Welcome, Name...");
    }

    public byte[] generateSalt(){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        return salt;
    }

    public byte[] encryptPassword(String password, byte[] salt) throws Exception{
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 4096, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] hash = factory.generateSecret(spec).getEncoded();

        return hash;
    }

    private boolean compareEmail(String email, Vector<User> users){
        for (int index = 0; index < users.size(); index++)
            if (users.get(index).email.equals(email))
                return true;
        return false;
    }

    private boolean comparePassword(byte[] encryptedPassword, Vector<User> users){
        for (int index = 0; index < users.size(); index++)
        {
            if (Arrays.equals(users.get(index).hashingPassword, encryptedPassword))
                return true;
        }
        return false;
    }

    // public User createDefaltAdmin(String name, String email, String password){
    //     User firstAdmin = new Admin("Default Account", "admin", encryptPassword("admin"));
    //     return firstAdmin;
    // }

}
