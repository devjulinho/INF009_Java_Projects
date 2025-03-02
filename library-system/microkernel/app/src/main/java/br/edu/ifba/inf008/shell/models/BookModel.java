package br.edu.ifba.inf008.shell.models;

import java.io.Serializable;

import br.edu.ifba.inf008.interfaces.IBookModel;

public class BookModel extends LibCollectionModel implements Serializable, IBookModel{
    protected String isbn;
    protected String genre;

    public BookModel(String isbn, String title, String author, int releaseYear, String genre){
        super(title, author, releaseYear);
        this.isbn = isbn;
        this.genre = genre;
    }

    public String getTitle(){
        return this.title;
    }

    public String getIsbn(){
        return this.isbn;
    }

    public String getAuthor(){
        return this.author;
    }

    public int getReleaseYear(){
        return this.releaseYear;
    }

    public String getGenre(){
        return this.genre;
    }

    public boolean isAvailable(){
        return this.available;
    }

    @Override
    public String toString(){
        return this.title + " (" + this.releaseYear + ") - " + this.author; 
    }
}