import java.util.Vector;

public class Main{
    public static void main(String[] Args) throws Exception{
        Vector<User> users = new Vector<>();
        Vector<Product> allProducts = new Vector<>();

        //Welcome space
        //only in the first creation, do it in the serialization
        //to push Referenceid when desserialize
        users.add(new Admin("Default Account", "admin", "admin"));
        users.add(new Admin("Julio", "julio@ifba.edu", "testing"));
        users.add(new Customer("Maria", "maria@ifba.edu", "testing2", "Rua A"));

        allProducts.add(new Product("feijao", "feijao preto", 7.50, 10, "Alimentos"));
        allProducts.add(new Product("carne", "carne para churrasco", 21.40, 30, "Alimentos"));
        allProducts.add(new Product("refrigerante", "para festas", 9.50, 10, "Bebidas"));
        allProducts.add(new Product("colher", "para comer", 12.10, 0, "Utesilios"));

        User currentUser = users.get(0).login(users);
        currentUser.menu(currentUser, users, allProducts);
        //((Admin)(users.get(0))).adminMenu(users);
        //((Admin)(users.get(0))).createUser(users);

        //users.forEach(u -> u.display(u));
        //currentUser.display(currentUser);
        // System.out.println(users.get(0).compareEmail("admin", users));

    }
}
