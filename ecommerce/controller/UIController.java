package ecommerce.controller;

public class UIController{

    public static User login(Vector<User> users){
        Scanner askEmail = new Scanner(System.in);
        Scanner askPassword = new Scanner(System.in);
        int

        System.out.println("What is your e-mail?");
        String email = askEmail.nextLine();

        System.out.println("What is your password?");
        String typedPassword = scan.nextLine();

        //criar encryptedPassword

        User user = User.login(users, email, encryptedPassword);

        return user;
        }


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

}
