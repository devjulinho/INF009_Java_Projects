package br.edu.ifba.inf008.interfaces;

import java.time.LocalDate;
import java.util.ArrayList;

public interface ILoanModel<T>{
    public String getUserName();
    public long amountOfLateDays();
    public LocalDate getFinalDate();
    public ArrayList<T> getBorrowedBooks();
    public double getFineAmount();
    public boolean isLate();
}