import java.util.Vector;

public class Main{
    public static void main(String[] Args){
        Vector<User> users = new Vector<>();

        //only in the first creation, do it in the serialization
        users.add(new Admin("Default Account", "admin", "admin"));
        users.get(0).login(users);

        users.get(0).display(users.get(0));
        // System.out.println(users.get(0).compareEmail("admin", users));




    }
}
