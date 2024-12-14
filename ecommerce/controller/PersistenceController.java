package ecommerce.controller;

import ecommerce.model.*;
import ecommerce.utils.SecurityUtils;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.File;
import java.util.HashMap;
import java.util.ArrayList;

public class PersistenceController{
    private static String fileName = "ecommerce/controller/database.dat";

    public static void initialization(HashMap<String, User> users, HashMap<Integer, Product> allProducts) throws Exception{
        File file = new File(fileName);

        if (file.exists()){
            deserialization(users, allProducts);
        }
        else{
            User.createDefaultAdmin(users);
        }
        UIController.initializationMessage();
    }

    public static void finalization(HashMap<String, User> users, HashMap<Integer, Product> allProducts) throws Exception{
        serialization(users, allProducts);
        UIController.finalizationMessage();
    }

    public static void serialization(HashMap<String, User> users, HashMap<Integer, Product> allProducts) throws Exception{
        FileOutputStream fileOut = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fileOut);

        ArrayList<Object> data = new ArrayList<>();
        data.add(users);
        data.add(allProducts);
        data.add(User.referenceId);
        data.add(Product.referenceId);
        data.add(Order.referenceId);
        data.add(ShoppingCart.referenceId);
        data.add(SecurityUtils.salt);

        oos.writeObject(data);
    }

    @SuppressWarnings("unchecked")
    public static void deserialization(HashMap<String, User> users, HashMap<Integer, Product> allProducts) throws Exception{
        FileInputStream fileIn = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fileIn);

        ArrayList<Object> data = (ArrayList<Object>)ois.readObject();

        HashMap<String, User> deserializedUsers = (HashMap<String, User>)data.get(0);
        HashMap<Integer, Product> deserializedProducts = (HashMap<Integer, Product>)data.get(1);
        User.referenceId = (int)data.get(2);
        Product.referenceId = (int)data.get(3);
        Order.referenceId = (int)data.get(4);
        ShoppingCart.referenceId = (int)data.get(5);
        SecurityUtils.salt = (byte [])data.get(6);

        users.clear();
        allProducts.clear();
        users.putAll(deserializedUsers);
        allProducts.putAll(deserializedProducts);

        ois.close();
    }
}
