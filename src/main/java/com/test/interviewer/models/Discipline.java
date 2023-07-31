package com.test.interviewer.models;
//Copy from model Interviewer
import java.io.*;
import java.util.ArrayList;

public class Discipline implements Serializable {
    static ArrayList<Discipline> data;

    int id;
    String name;
    /*A slug is a human-readable, unique identifier,
            used to identify a resource instead of a less human-readable
            identifier like an id */
    String slug;
    String description;

    public Discipline(
            String name,
            String slug,
            String description
    ) {
        this.id = data.size() + 1;
        this.name = name;
        this.slug = slug;
        this.description = description;
    }

    public Discipline add() {
        data.add(this);
        Discipline.saveDataToFile();
        return this;
    }

    public void delete() throws Exception{
        Discipline discipline = Discipline.getByDescription(this.description);

        if (discipline != null) {
            data.remove(this);
            Discipline.saveDataToFile();
        }
        else
            throw new Exception("Discipline not found");
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

    public static Discipline getByDescription(String description) {
        for (Discipline Discipline: data) {
            if (Discipline.description.equals(description))
                return Discipline;
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
            FileOutputStream fileOutputStream = new FileOutputStream("./disciplines");
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);

            outputStream.writeObject(Discipline.data);

            outputStream.close();
            fileOutputStream.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void loadDataFromFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream("./disciplines");
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);

            ArrayList<Discipline> fileData = (ArrayList<Discipline>) inputStream.readObject();

            Discipline.data.clear();
            Discipline.data.addAll(fileData);

            inputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            if (!e.getMessage().contains("No such file or directory"))
                e.printStackTrace();
        }
    }
}