import java.util.Vector;

public class Main{
    public static void main(String[] Args){
        Vector<User> users = new Vector<>();

        users.add(new Admin("default", "admin", "admin"));
        users.get(0).display(users.get(0));




    }
}
