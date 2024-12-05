import java.util.Vector;

public class Main{
    public static void main(String[] Args) throws Exception{
        Vector<User> users = new Vector<>();
        Vector<Product> products = new Vector<>();

        //Welcome space
        //only in the first creation, do it in the serialization
        //to push Referenceid when desserialize
        users.add(new Admin("Default Account", "admin", "admin"));
        users.add(new Admin("Julio", "julio@ifba.edu", "testing"));
        users.add(new Customer("Maria", "maria@ifba.edu", "testing2", "Rua A"));

        User currentUser = users.get(0).login(users);
        currentUser.menu(currentUser, users);
        //((Admin)(users.get(0))).adminMenu(users);
        //((Admin)(users.get(0))).createUser(users);

        //users.forEach(u -> u.display(u));
        //currentUser.display(currentUser);
        // System.out.println(users.get(0).compareEmail("admin", users));

    }
}
