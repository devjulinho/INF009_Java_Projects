package br.edu.ifba.inf008.shell.controllers;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import br.edu.ifba.inf008.interfaces.IUserController;
import br.edu.ifba.inf008.shell.models.UserModel;

public class UserController implements IUserController{
    
    public static ArrayList<UserModel> users = new ArrayList<>();

    public static void addUser(String name){
        users.add(new UserModel(name));
    }

    public static int usersSize(){
        return users.size();
    }

    public static void applyFine(UserModel user, LocalDate startDate, LocalDate devolutionDate, int bookAmount){
        user.fine = user.fine + ((ChronoUnit.DAYS.between(startDate, devolutionDate) - 14) * 0.5 * bookAmount);
    }

    public static int getReferenceId(){
        if (!users.isEmpty())
            return users.get(0).getReferenceId();
        return 0;
    }

}
