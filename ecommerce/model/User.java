package ecommerce.model;

import ecommerce.utils.SecurityUtils;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Arrays;

public abstract class User{
    public int id;
    public String name;
    public String email;
    public byte[] hashingPassword;
    public static int referenceId = 0;

    public User(String name, String email, String password) throws Exception{
        this.id = referenceId++;
        this.name = name;
        this.email = email;
        this.hashingPassword = SecurityUtils.encryptPassword(password, SecurityUtils.salt);
    }

    public abstract void display();

    public abstract void menu(User currentUser, HashMap<String, User> users, HashMap<Integer, Product> allProducts) throws Exception;

    public static User login (HashMap<String, User> users, String email, String password) throws Exception{
        boolean successfulLogin = false;
        byte[] encryptedPassword = SecurityUtils.encryptPassword(password, SecurityUtils.salt);

        User user = users.get(email);

        if(user != null){
            successfulLogin = comparePassword(encryptedPassword, user);
        }

        if(successfulLogin == true)
            return user;
        return null;
    }

    // private static int compareEmail(String email, Vector<User> users){
    //     for (int index = 0; index < users.size(); index++)
    //         if (users.get(index).email.equals(email))
    //             return index;
    //     return -1;
    // }

    private static boolean comparePassword(byte[] encryptedPassword, User user){
        if (Arrays.equals(user.hashingPassword, encryptedPassword))
                return true;
        return false;
    }

    // public User createDefaltAdmin(String name, String email, String password){
    //     User firstAdmin = new Admin("Default Account", "admin", encryptPassword("admin"));
    //     return firstAdmin;
    // }

}
