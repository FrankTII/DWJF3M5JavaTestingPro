package com.test.interviewer.models;
//Copy from model Interviewer
import java.io.*;
import java.util.ArrayList;

public class InterviewType  implements Serializable {
    static ArrayList<InterviewType> data;

    int id;
    String name;
    /*A slug is a human-readable, unique identifier,
            used to identify a resource instead of a less human-readable
            identifier like an id */
    String slug;
    String description;

    public InterviewType(
            String name,
            String slug,
            String description
    ) {
        this.id = data.size() + 1;
        this.name = name;
        this.slug = slug;
        this.description = description;
    }

    public InterviewType add() {
        data.add(this);
        InterviewType.saveDataToFile();
        return this;
    }

    public void delete() throws Exception{
        InterviewType interviewType = InterviewType.getByDescription(this.description);

        if (interviewType != null) {
            data.remove(this);
            InterviewType.saveDataToFile();
        }
        else
            throw new Exception("InterviewType not found");
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

    public static InterviewType getByDescription(String description) {
        for (InterviewType InterviewType: data) {
            if (InterviewType.description.equals(description))
                return InterviewType;
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
            FileOutputStream fileOutputStream = new FileOutputStream("./interviewTypes");
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);

            outputStream.writeObject(InterviewType.data);

            outputStream.close();
            fileOutputStream.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void loadDataFromFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream("./interviewTypes");
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);

            ArrayList<InterviewType> fileData = (ArrayList<InterviewType>) inputStream.readObject();

            InterviewType.data.clear();
            InterviewType.data.addAll(fileData);

            inputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            if (!e.getMessage().contains("No such file or directory"))
                e.printStackTrace();
        }
    }
}