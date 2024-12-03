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
    static int referenceId = 0;

    public User(String name, String email, String password) throws Exception{
        this.id = referenceId++;
        this.name = name;
        this.email = email;
        this.salt = generateSalt();
        this.hashingPassword = encryptPassword(password, salt);
    }

    public void display(User user){
        System.out.println("=====> User " + this.id + " <=====");
        System.out.println("Name: " + this.name);
        System.out.println("E-mail: " + this.email);
        if (user instanceof Customer)
            System.out.println("Address: " + ((Customer)user).address);
    }

    public User login(Vector<User> users) throws Exception{
        Scanner scan = new Scanner(System.in);
        boolean successfulLogin = false;
        boolean successfulEmail = false;
        boolean successfulPassword = false;
        int userIndex = -1;

        while(successfulLogin == false){
            while(successfulEmail == false){
                System.out.println("What is your e-mail?");
                String email = scan.nextLine();

                userIndex = compareEmail(email, users);

                if(userIndex == -1){
                    System.out.println("We don't have that e-mail in our database. Please, check correctly.");
                }

                else
                    successfulEmail = true;
            }

            for(int numberAttemps = 0; successfulPassword == false; numberAttemps++){
                System.out.println("What is your password?");
                String typedPassword = scan.nextLine();

                if(comparePassword(encryptPassword(typedPassword, users.get(userIndex).salt), users) == false){
                    System.out.println("Wrong password. You have only " + (3 - numberAttemps) + " chances");

                if(numberAttemps == 3 && comparePassword(encryptPassword(typedPassword, users.get(userIndex).salt), users) == false)
                    System.exit(0);
                }
                successfulPassword = comparePassword(encryptPassword(typedPassword, users.get(userIndex).salt), users);
            }
            successfulLogin = true;
        }
        System.out.println("Welcome, " + users.get(userIndex).name);
        return users.get(userIndex);
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

    protected int compareEmail(String email, Vector<User> users){
        for (int index = 0; index < users.size(); index++)
            if (users.get(index).email.equals(email))
                return index;
        return -1;
    }

    protected boolean comparePassword(byte[] encryptedPassword, Vector<User> users){
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
