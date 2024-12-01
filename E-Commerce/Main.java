public class Main{
    public static void main(String[] Args){

        //Customer customer = new Customer();
        Admin admin = new Admin("Julio", "julio@edu.br", "1234");
        User user = admin.createUser();
        user.display(user);


    }
}
