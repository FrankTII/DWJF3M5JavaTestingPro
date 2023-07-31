package com.test.interviewer.models;
//Copy from model Interviewer
import java.io.*;
import java.util.ArrayList;

public class Interview implements Serializable {

    //al mover a model package solicita tipo : public
    public static ArrayList<Interview> data;

    int id;
    public Candidate candidate;
    public Interviewer interviewer;
    public InterviewType type;
    public Technology technology;
    public Discipline discipline;

    //Constructor
    public Interview(
            Candidate candidate,
            Interviewer interviewer,
            InterviewType type,
            Technology technology,
            Discipline discipline
    ) { //Seria mejor una tabla intermedia en DB con FK (P2)
        this.id = data.size() + 1;
        this.candidate = candidate;
        this.interviewer = interviewer;
        this.type = type;
        this.technology = technology;
        this.discipline= discipline;
    }
    //Getters and setters

    public static ArrayList<Interview> getData() {
        return data;
    }

    public static void setData(ArrayList<Interview> data) {
        Interview.data = data;
    }

    public int getId() {
        return id;
    }


    //REvisar este codigo que solo se importo de los modelos anteriores.
    public Interview add() {
        data.add(this);
        Interview.saveDataToFile();
        return this;
    }

    public void delete() {//throws Exception{
        //Code
    }

    public void save(
            Candidate candidate,
            Interviewer interviewer,
            InterviewType type,
            Technology technology,
            Discipline discipline
    ) {
        try {
            this.delete();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        if (candidate!=null)
            this.candidate = candidate;

        if (interviewer!=null)
            this.interviewer = interviewer;

        if (type!=null)
            this.type = type;

        this.technology = technology;
        this.discipline = discipline;


        data.add(this);
    }

    public static Interview getById(int id) {
        for (Interview interview: data) {
            //Code
                return interview;
        }

        return null;
    }

    @Override
    public String toString() {
        return "\nID: " + this.id +
                "\nName: " + this.candidate +
                "\nLast Name: " + this.interviewer +
                "\nEmail: " + this.type +
                "\nIs Active: " + this.technology +
                "\nIs Admin: " + this.discipline +
                "\n";
    }

    public static void saveDataToFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("./interviews");
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);

            outputStream.writeObject(Interview.data);

            outputStream.close();
            fileOutputStream.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void loadDataFromFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream("./interviews");
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);

            ArrayList<Interview> fileData = (ArrayList<Interview>) inputStream.readObject();

            Interview.data.clear();
            Interview.data.addAll(fileData);

            inputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            if (!e.getMessage().contains("No such file or directory"))
                e.printStackTrace();
        }
    }
}
