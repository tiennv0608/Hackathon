package sample.controller;

import sample.model.UserAccount;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class IOUserAccount {
    public static final String PATH = "C:\\Users\\Nguyen Viet Tien\\Desktop\\Codegym\\03_Baitap\\00_Hackathon\\Hackathon\\src\\file\\username.csv";

    public static List<UserAccount> readUser(String path) {
        List<UserAccount> list = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                list.add(new UserAccount(values[0], values[1], values[2], Integer.parseInt(values[3]), values[4], values[5], values[6]));
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void writeUser(String path, List<UserAccount> list) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String str = "";
            for (UserAccount userAccount : list) {
                str += userAccount.getUserName() + "," + userAccount.getPassword() + "," + userAccount.getFullname() + "," +
                        userAccount.getAge() + "," + userAccount.getGender() + "," + userAccount.getAddress() + "," + userAccount.getPhoneNumber() + "\n";
            }
            bufferedWriter.write(str);
            bufferedWriter.flush();
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
