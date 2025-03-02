package br.edu.ifba.inf008.shell.controllers;

import java.util.ArrayList;

import br.edu.ifba.inf008.interfaces.IBookController;
import br.edu.ifba.inf008.shell.models.BookModel;

public class BookController extends LibCollectionController implements IBookController{
    public static BookController instance;

    public static BookController getInstance(){
        if(instance == null)
            instance = new BookController();
        return instance;
    }

    public static ArrayList<BookModel> books = new ArrayList<>();

    public static void addNewBook(String isbn, String title, String author, int releaseYear, String genre){
        books.add(new BookModel(isbn, title, author, releaseYear, genre));
    }

    public static int booksSize(){
        return books.size();
    }

    public ArrayList<BookModel> getAllBooks(){
        return books;
    }

}
