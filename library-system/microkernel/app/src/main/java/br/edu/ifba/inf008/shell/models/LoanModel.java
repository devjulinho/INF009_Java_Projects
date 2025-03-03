package br.edu.ifba.inf008.shell.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import br.edu.ifba.inf008.interfaces.ILoanModel;

public class LoanModel implements Serializable, ILoanModel{
    public UserModel user;
    public ArrayList<BookModel> books;
    public LocalDate startDate;
    public LocalDate devolutionDate;
    public LocalDate finalDate;

    public LoanModel(UserModel user, ArrayList<BookModel> books, LocalDate startDate){
        this.user = user;
        this.books = books;
        this.startDate = startDate;
        this.devolutionDate = startDate.plusDays(14);
    }

    public String allBooksToString(){
        ArrayList<String> allTitles = new ArrayList<>();
        for (BookModel book : books) {
            allTitles.add(book.getTitle());
        }
        String allBooksString = String.join(", ", allTitles);
        return allBooksString;
    }

    public LocalDate getFinalDate(){
        return finalDate;
    }

    public String getUserName(){
        return user.toString();
    }

    public long amountOfLateDays(){
        return ChronoUnit.DAYS.between(devolutionDate, LocalDate.now());
    }

    public double getFineAmount(){
        return (amountOfLateDays()) * 0.5;
    }

    public ArrayList<BookModel> getBorrowedBooks(){
        return books;
    }

    public boolean isLate(){
        return LocalDate.now().isAfter(devolutionDate);
    }
}