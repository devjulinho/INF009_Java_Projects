package br.edu.ifba.inf008.shell.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import br.edu.ifba.inf008.interfaces.IIOController;
import br.edu.ifba.inf008.shell.models.BookModel;
import br.edu.ifba.inf008.shell.models.LoanModel;
import br.edu.ifba.inf008.shell.models.UserModel;

public class IOController implements IIOController{
    private static final String fileName = "app/src/main/java/br/edu/ifba/inf008/shell/database/data.dat";
    public static IOController instance;

    private IOController(){}

    public static IOController getInstance(){
        if (instance == null)
            instance = new IOController();
        return instance;
    }

    @Override
    public void serialization(){
        try{
            File file = new File(fileName);
            File directory = file.getParentFile();

            if(!file.exists()){
                directory.mkdirs();
                file.createNewFile();
            }

            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fileOut);

            oos.writeObject(new ArrayList<>(UserController.users));
            oos.writeObject(new ArrayList<>(BookController.books));
            oos.writeObject(new ArrayList<>(LoanController.loans));
            oos.writeObject(UserController.getReferenceId());

            oos.close();
            fileOut.close();
        } catch (FileNotFoundException e){
             System.out.println("Arquivo nao existe!");
        } catch (IOException e){
            System.out.println("Erro entrada e saida!");
            e.printStackTrace();
        }
    }

    @Override
    public void deserialization(){
        try{
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fileIn);

            UserController.users = (ArrayList<UserModel>) ois.readObject();
            BookController.books = (ArrayList<BookModel>) ois.readObject();
            LoanController.loans = (ArrayList<LoanModel>) ois.readObject();
            if(!UserController.users.isEmpty()){
                UserModel.referenceId = (int)ois.readObject();
            }

            ois.close();
            fileIn.close();
        } catch (FileNotFoundException e){
            //File file = new File(fileName);
             System.out.println("Arquivo nao existe!");
        } catch (IOException e){
            System.out.println("Erro entrada e saida!");
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            System.out.println("Classe nao encontrada!");
            e.printStackTrace();
        }
    }
}
