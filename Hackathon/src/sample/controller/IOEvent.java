package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import sample.model.Event;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class IOEvent {
    public static final String PATH = "C:\\Users\\Nguyen Viet Tien\\Desktop\\Codegym\\03_Baitap\\00_Hackathon\\Hackathon\\src\\file\\event.csv";
    public static final String OBJ_PATH = "C:\\Users\\Nguyen Viet Tien\\Desktop\\Codegym\\03_Baitap\\00_Hackathon\\Hackathon\\src\\file\\event.dat";

    public static List<Event> readFromFile(String path) {
        List<Event> list = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] str = line.split(",");
                list.add(new Event(str[0], str[1], str[2], str[3], str[4], Integer.parseInt(str[5]), str[6]));
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void writeToFile(String path, List<Event> list) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String str = "Username,Tên sự kiện,Môn,Thời gian,Địa điểm,Trạng thái,Mô tả\n";
            for (Event event : list) {
                str += event.getId() + "," + event.getName() + "," + event.getSport() + "," + event.getTime() +
                        "," + event.getPlace() + "," + event.getMaxPerson() + "," + event.getDes() + "\n";
            }
            bufferedWriter.write(str);
            bufferedWriter.flush();
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Event> readObj(String path) {
        List<Event> list = new ArrayList<>();

        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            list.addAll((Collection<? extends Event>) objectInputStream.readObject());
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void writeObj(String path, List<Event> list) {
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
