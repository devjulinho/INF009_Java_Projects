package br.edu.ifba.inf008.shell.models;

import java.io.Serializable;
import java.time.LocalDate;

public class LoanModel implements Serializable{
    public UserModel user;
    public BookModel book;
    public LocalDate startDate;
    public LocalDate devolutionDate;
    public LocalDate finalDate;

    public LoanModel(UserModel user, BookModel book, LocalDate startDate){
        this.user = user;
        this.book = book;
        this.startDate = startDate;
        this.devolutionDate = startDate.plusDays(7);
    }
}