package br.edu.ifba.inf008.shell.models;

import java.io.Serializable;

public class LibCollectionModel implements Serializable{
    protected String title;
    protected String author;
    protected int releaseYear;

    public LibCollectionModel(String title, String author, int releaseYear){
        this.title = title;
        this.author = author;
        this.releaseYear = releaseYear;
    }
}
