package com.test.interviewer.models;
//Copy from model Interviewer
import java.io.*;
import java.util.ArrayList;

public class Technology implements Serializable {
    static ArrayList<Technology> data;

    int id;
    String name;
    /*A slug is a human-readable, unique identifier,
            used to identify a resource instead of a less human-readable
            identifier like an id */
    String slug;
    String description;

    public Technology(
            String name,
            String slug,
            String description
    ) {
        this.id = data.size() + 1;
        this.name = name;
        this.slug = slug;
        this.description = description;
    }

    public Technology add() {
        data.add(this);
        Technology.saveDataToFile();
        return this;
    }

    public void delete() throws Exception{
        Technology technology = Technology.getByDescription(this.description);

        if (technology != null) {
            data.remove(this);
            Technology.saveDataToFile();
        }
        else
            throw new Exception("Technology not found");
    }

    public void save(
            String name,
            String slug,
            String description
    ) {
        try {
            this.delete();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        if (!name.equals(""))
            this.name = name;

        if (!slug.equals(""))
            this.slug = slug;

        if (!description.equals(""))
            this.description = description;

        data.add(this);
    }

    public static Technology getByDescription(String description) {
        for (Technology Technology: data) {
            if (Technology.description.equals(description))
                return Technology;
        }

        return null;
    }

    @Override
    public String toString() {
        return "\nID: " + this.id +
                "\nName: " + this.name +
                "\nLast Name: " + this.slug +
                "\ndescription: " + this.description + "\n";
    }

    public static void saveDataToFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("./technologies");
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);

            outputStream.writeObject(Technology.data);

            outputStream.close();
            fileOutputStream.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void loadDataFromFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream("./technologies");
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);

            ArrayList<Technology> fileData = (ArrayList<Technology>) inputStream.readObject();

            Technology.data.clear();
            Technology.data.addAll(fileData);

            inputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            if (!e.getMessage().contains("No such file or directory"))
                e.printStackTrace();
        }
    }
}