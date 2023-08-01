package com.test.interviewer.models;
//Base model PW1
import java.io.*;
import java.util.ArrayList;
//ExceptionsPWK4 cucumber Validatios
import com.test.interviewer.exceptions.TooShortInputDataException;
import com.test.interviewer.exceptions.InvalidEmailException;
//Regex
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interviewer implements Serializable {

    //al mover a model package solicita tipo : public
    public static ArrayList<Interviewer> data;

    private int id;
    String name;
    String lastName;
    String email;
    Boolean isActive;

    Boolean isAdmin;

    public int getId() {
        return id;
    }

    public Interviewer(
            String name,
            String lastName,
            String email,
            Boolean isActive,
            Boolean isAdmin
    ) throws TooShortInputDataException, InvalidEmailException {
        if (name.length() < 3 || lastName.length() < 3) {
            throw new TooShortInputDataException("Name and Last Name must have at least 3 characters");
        }

        if (!isValidEmail(email)) {
            throw new InvalidEmailException("Invalid email format");
        }

        this.id = data.size() + 1;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.isActive = isActive;
        this.isAdmin= isAdmin;
    }

    public Interviewer add() {
        data.add(this);
        Interviewer.saveDataToFile();
        return this;
    }

    public void delete() throws Exception{
        Interviewer interviewer = Interviewer.getByEmail(this.email);

        if (interviewer != null) {
            data.remove(this);
            Interviewer.saveDataToFile();
        }
        else
            throw new Exception("Interviewer not found");
    }

    public void save(
            String name,
            String lastName,
            String email,
            Boolean isActive,
            Boolean isAdmin
    ) throws TooShortInputDataException, InvalidEmailException {
        if (name.length() < 3 || lastName.length() < 3) {
            throw new TooShortInputDataException("Name and Last Name must have at least 3 characters");
        }

        if (!isValidEmail(email)) {
            throw new InvalidEmailException("Invalid email format");
        }

        try {
            this.delete();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        if (!name.equals(""))
            this.name = name;

        if (!lastName.equals(""))
            this.lastName = lastName;

        if (!email.equals(""))
            this.email = email;

        this.isActive = isActive;
        this.isAdmin = isAdmin;


        data.add(this);
    }

    public static Interviewer getByEmail(String email) {
        for (Interviewer interviewer: data) {
            if (interviewer.email.equals(email))
                return interviewer;
        }

        return null;
    }

    @Override
    public String toString() {
        return "\nID: " + this.id +
                "\nName: " + this.name +
                "\nLast Name: " + this.lastName +
                "\nEmail: " + this.email +
                "\nIs Active: " + this.isActive +
                "\nIs Admin: " + this.isAdmin +
                "\n";
    }

    public static void saveDataToFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("./interviewers");
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);

            outputStream.writeObject(Interviewer.data);

            outputStream.close();
            fileOutputStream.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void loadDataFromFile() {
        try {
            //Logging view Datafile contents
            System.out.println("Loading data from file...");
            FileInputStream fileInputStream = new FileInputStream("./interviewers");
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);

            ArrayList<Interviewer> fileData = (ArrayList<Interviewer>) inputStream.readObject();

            Interviewer.data.clear();
            Interviewer.data.addAll(fileData);

            inputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            if (!e.getMessage().contains("No such file or directory"))
                e.printStackTrace();
        }
    }

    private boolean isValidName(String name) {
        return name != null && name.length() >= 3;
    }

    private boolean isValidEmail(String email) {
        // Regular expression pattern for email validation
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
}