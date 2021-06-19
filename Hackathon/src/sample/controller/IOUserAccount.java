package sample.controller;

import sample.model.UserAccount;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class IOUserAccount {
    public static String PATH = "C:\\Users\\Nguyen Viet Tien\\Desktop\\Codegym\\03_Baitap\\00_Hackathon\\Hackathon\\src\\file\\useraccount.dat";

    public static List<UserAccount> readObj(String path) {
        List<UserAccount> list = new ArrayList<>();

        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            list.addAll((Collection<? extends UserAccount>) objectInputStream.readObject());
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void writeObj(String path, List<UserAccount> list) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(list);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
