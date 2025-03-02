package br.edu.ifba.inf008.shell.models;

import java.io.Serializable;
import java.util.ArrayList;

public class UserModel implements Serializable{
    public static int referenceId = 0;
    protected int id;
    protected String name;
    public ArrayList<BookModel> borrowedBooks = new ArrayList<>();
    public double fine;

    public UserModel(String name){
        this.id = referenceId++;
        this.name = name;
        this.fine = 0;
    }

    public String getName(){
        return this.name;
    }

    public int getId(){
        return this.id;
    }

    public ArrayList<BookModel> getBorrowedBooks(){
        return borrowedBooks;
    }

    public double getFine(){
        return fine;
    }

    @Override
    public String toString(){
        return this.name + " (" + this.id + ")";
    }
}