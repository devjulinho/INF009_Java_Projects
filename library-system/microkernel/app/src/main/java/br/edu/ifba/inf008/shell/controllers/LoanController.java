package br.edu.ifba.inf008.shell.controllers;

import java.time.LocalDate;
import java.util.ArrayList;

import br.edu.ifba.inf008.interfaces.ILoanController;
import br.edu.ifba.inf008.shell.models.BookModel;
import br.edu.ifba.inf008.shell.models.LoanModel;
import br.edu.ifba.inf008.shell.models.UserModel;

public class LoanController implements ILoanController{
    public static ArrayList<LoanModel> loans = new ArrayList<>();

    public static void addNewLoan(UserModel user, ArrayList<BookModel> books, LocalDate date){
        loans.add(new LoanModel(user, books, date));
    }

    public static void finishLoan(LoanModel loan, LocalDate finishDate){       
        if (finishDate.isAfter(loan.devolutionDate)){
            UserController.applyFine(loan.user, loan.startDate, finishDate, loan.books.size());
        }
            
        loan.finalDate = finishDate;
        loan.user.borrowedBooks.removeAll(loan.books);
        
        for(BookModel book : loan.books){
            book.available = true;
        }
    }
}