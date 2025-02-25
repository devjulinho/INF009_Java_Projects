package br.edu.ifba.inf008.shell.controllers;

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

}
