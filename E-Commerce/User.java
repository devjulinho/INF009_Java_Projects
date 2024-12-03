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

    public void login(){
        Scanner scan = new Scanner(System.in);
        System.out.println("What is your e-mail?");
        String email = scan.nextLine();
        System.out.println("What is your password?");
        String password = scan.nextLine();
    }

    // private void compareEmail(String email, Vector<User> users){
    //     for (int index = 0, index < users.size(); index++){
    //         if
    //     }
    //
    // }

    private void validateLogin(String email, Vector<User> users){


    }

}
