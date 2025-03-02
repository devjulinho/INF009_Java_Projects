package br.edu.ifba.inf008.interfaces;

public interface IBookModel{

    public String getTitle();

    public String getIsbn();

    public String getAuthor();

    public int getReleaseYear();

    public String getGenre();

    public boolean getAvailability();

    @Override
    public String toString();
}