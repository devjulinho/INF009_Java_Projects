package ecommerce.model;

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

    public User login(Vector<User> users, String email, byte[] encryptedPassword) throws Exception{




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

    protected static int compareEmail(String email, Vector<User> users){
        for (int index = 0; index < users.size(); index++)
            if (users.get(index).email.equals(email))
                return index;
        return -1;
    }

    protected static boolean comparePassword(byte[] encryptedPassword, Vector<User> users){
        for (int index = 0; index < users.size(); index++)
        {
            if (Arrays.equals(users.get(index).hashingPassword, encryptedPassword))
                return true;
        }
        return false;
    }

    public void menu (User currentUser, Vector<User> users, Vector<Product> products) throws Exception{
        if (currentUser instanceof Customer)
            ((Customer)currentUser).customerMenu(products);
        else if (currentUser instanceof Admin)
            ((Admin)currentUser).adminMenu(users);
    }

    // public User createDefaltAdmin(String name, String email, String password){
    //     User firstAdmin = new Admin("Default Account", "admin", encryptPassword("admin"));
    //     return firstAdmin;
    // }

}
