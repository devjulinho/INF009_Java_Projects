package br.edu.ifba.inf008.interfaces;

import java.util.ArrayList;

public interface IBookController<T> extends ILibCollectionController{
    public ArrayList<T> getAllBooks();
}
