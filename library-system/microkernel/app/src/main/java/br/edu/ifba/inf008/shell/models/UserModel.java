package br.edu.ifba.inf008.shell.models;

import java.io.Serializable;
import java.util.ArrayList;

public class UserModel implements Serializable{
    public static int referenceId = 0;
    protected int id;
    protected String name;
    protected ArrayList<BookModel> borrowedBooks;

    public UserModel(String name){
        this.id = referenceId++;
        this.name = name;
        this.borrowedBooks = null;
    }

    public String getName(){
        return this.name;
    }

    public int getId(){
        return this.id;
    }
}