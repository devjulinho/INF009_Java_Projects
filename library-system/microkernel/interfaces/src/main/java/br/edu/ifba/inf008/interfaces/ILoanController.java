package br.edu.ifba.inf008.interfaces;

import java.util.ArrayList;

public interface ILoanController<T>{
    public ArrayList<T> getAllLoans();
}