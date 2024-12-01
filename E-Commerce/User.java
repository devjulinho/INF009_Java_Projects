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

}
