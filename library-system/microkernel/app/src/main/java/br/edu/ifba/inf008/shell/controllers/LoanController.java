package br.edu.ifba.inf008.shell.controllers;

import java.time.LocalDate;
import java.util.ArrayList;

import br.edu.ifba.inf008.interfaces.ILoanController;
import br.edu.ifba.inf008.shell.models.BookModel;
import br.edu.ifba.inf008.shell.models.LoanModel;
import br.edu.ifba.inf008.shell.models.UserModel;

public class LoanController implements ILoanController{
    public static ArrayList<LoanModel> loans = new ArrayList<>();

    public static void addNewLoan(UserModel user, BookModel book, LocalDate date){
        loans.add(new LoanModel(user, book, date));
    }

}