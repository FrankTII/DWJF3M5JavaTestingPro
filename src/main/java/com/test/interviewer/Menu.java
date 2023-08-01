package com.test.interviewer;

import com.test.interviewer.exceptions.TooShortInputDataException;
import com.test.interviewer.exceptions.InvalidEmailException;
import com.test.interviewer.models.Interviewer;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    Scanner sc;

    public Menu() {
        sc = new Scanner(System.in);
        Interviewer.data = new ArrayList<Interviewer>();
        Interviewer.loadDataFromFile();

        showMainMenu();
    }

    public void showMainMenu() {
        int option = 0;

        while (option != 6) {
            System.out.println("Seleccione la operacion a realizar:");
            System.out.println("1. Dar de alta un entrevistador");
            System.out.println("2. Consultar un entrevistador");
            System.out.println("3. Modificar un entrevistador");
            System.out.println("4. Eliminar un entrevistador");
            System.out.println("5. Listar todos los entrevistadores");
            System.out.println("6. Salir");

            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    addInterviewer();
                    break;
                case 2:
                    searchInterviewer();
                    break;
                case 3:
                    modifyInterviewer();
                    break;
                case 4:
                    deleteInterviewer();
                    break;
                case 5:
                    listAllInterviewers();
                    break;
            }
        }

        System.out.println("Programa terminado");
    }


    public void addInterviewer() {
        try{
            System.out.println("Ingrese el nombre del entrevistador: ");
            String name = sc.nextLine();
            System.out.println("Ingrese el apellido del entrevistador: ");
            String lastName = sc.nextLine();
            System.out.println("Ingrese el email del entrevistador: ");
            String email = sc.nextLine();
            System.out.println("El entrevistador se encuentra activo? (1=Si/2=No)");
            Boolean isActive = sc.nextInt() == 1;
            System.out.println("El entrevistador es Administrador? (1=Si/2=No)");
            Boolean isAdmin = sc.nextInt() == 1;

            sc.nextLine();

            Interviewer interviewer = new Interviewer(name, lastName, email, isActive, isAdmin);
            interviewer.add();

            System.out.println(interviewer.toString());
        }catch (TooShortInputDataException e) {
            System.out.println("Error: " + e.getMessage());

        } catch (InvalidEmailException e) {
            System.out.println("Error: " + e.getMessage());

        }
    }

    public void searchInterviewer() {
        System.out.println("Ingrese el email del entrevistador a consultar:");
        String email = sc.nextLine();

        Interviewer interviewer = Interviewer.getByEmail(email);

        if (interviewer != null) {
            System.out.println("Entrevistador encontrado:");
            System.out.println(interviewer.toString());
        } else {
            System.out.println("Entrevistador no encontrado");
        }
    }

    public void modifyInterviewer() {
        System.out.println("Ingrese el email del entrevistador a modificar:");
        String email = sc.nextLine();

        Interviewer interviewer = Interviewer.getByEmail(email);

        if (interviewer != null) {
            System.out.println("Entrevistador encontrado:");
            System.out.println(interviewer.toString());

            System.out.println("Ingrese el nuevo nombre del entrevistador: (Enter para mantener actual)");
            String name = sc.nextLine();
            System.out.println("Ingrese el nuevo apellido del entrevistador: (Enter para mantener actual)");
            String lastName = sc.nextLine();
            System.out.println("Ingrese el nuevo email del entrevistador: (Enter para mantener actual)");
            String newEmail = sc.nextLine();
            System.out.println("El entrevistador se encuentra activo? (1=Si/2=No)");
            Boolean isActive = sc.nextInt() == 1;
            System.out.println("El entrevistador es Administrador? (1=Si/2=No)");
            Boolean isAdmin = sc.nextInt() == 1;
            sc.nextLine();

            try {
                interviewer.save(name, lastName, newEmail, isActive, isAdmin);
            } catch (TooShortInputDataException e) {
                System.out.println("Error: " + e.getMessage());

            } catch (InvalidEmailException e) {
                System.out.println("Error: " + e.getMessage());

            }

        } else {
            System.out.println("Entrevistador no encontrado");
        }
    }

    public void deleteInterviewer() {
        System.out.println("Ingrese el email del entrevistador a eliminar:");
        String email = sc.nextLine();

        Interviewer interviewer = Interviewer.getByEmail(email);
        try {
            interviewer.delete();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void listAllInterviewers() {
        if (Interviewer.data.isEmpty()) {
            System.out.println("No hay entrevistadores registrados.");
        } else {
            System.out.println("Listado de entrevistadores:");
            for (Interviewer interviewer : Interviewer.data) {
                System.out.println(interviewer.toString());
            }
        }
    }

    public static void main(String[] args) {
        new Menu();
    }
}