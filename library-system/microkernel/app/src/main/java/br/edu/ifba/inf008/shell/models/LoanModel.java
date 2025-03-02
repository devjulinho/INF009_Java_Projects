package br.edu.ifba.inf008.shell.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class LoanModel implements Serializable{
    public UserModel user;
    public ArrayList<BookModel> books;
    public LocalDate startDate;
    public LocalDate devolutionDate;
    public LocalDate finalDate;

    public LoanModel(UserModel user, ArrayList<BookModel> books, LocalDate startDate){
        this.user = user;
        this.books = books;
        this.startDate = startDate;
        this.devolutionDate = startDate.plusDays(7);
    }

    public String allBooksToString(){
        ArrayList<String> allTitles = new ArrayList<>();
        for (BookModel book : books) {
            allTitles.add(book.getTitle());
        }
        String allBooksString = String.join(", ", allTitles);
        return allBooksString;
    }
}