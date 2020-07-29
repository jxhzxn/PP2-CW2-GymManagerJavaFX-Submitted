package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;


import static java.lang.Integer.parseInt;

public class MyGymManager extends Application implements GymManager {

    public int menuOption() {
        Scanner sc = new Scanner(System.in);

        System.out.println(
                "Add a New Member                   :   1 \n" +
                        "Delete a Member                    :   2 \n" +
                        "Print the list of Members          :   3 \n" +
                        "Sort the Item                      :   4 \n" +
                        "Write/Save in a file               :   5 \n" +
                        "Open a Graphical User Interface    :   6 \n" +
                        "Quit                               :   7 \n"
        );

        System.out.print("Choose an Option : ");
        int option = sc.nextInt();
        return option;
    }

    public void functionChoose(int option) throws FileNotFoundException {
        MyGymManager myGymManager = new MyGymManager();

        if (option == 1) {
            myGymManager.addMember();
        } else if (option == 2) {
            myGymManager.deleteMember();
        } else if (option == 3) {
            myGymManager.printAll();
        } else if (option == 4) {
            myGymManager.sorting();
        } else if (option == 5) {
            try {
                myGymManager.fileSave();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (option == 6) {
            myGymManager.openGUI();
        }else if(option==7){
            System.exit(0);
        } else{
            try{
                System.out.println("Invalid Input");
                System.out.println("-----------------------------");
                System.out.println("");
                myGymManager.functionChoose(myGymManager.menuOption());
            }catch (InputMismatchException | FileNotFoundException e){
                System.out.println("Invalid Input");
                System.out.println("-----------------------------");
                System.out.println("");
                try {
                    myGymManager.functionChoose(myGymManager.menuOption());
                } catch (InputMismatchException fileNotFoundException) {
                    myGymManager.functionChoose(myGymManager.menuOption());
                }
            }

        }
    }

    public void addMember() throws FileNotFoundException {
        MyGymManager myGymManager = new MyGymManager();
        Scanner sc = new Scanner(System.in);
        int lastID = parseInt(Database.getLastID());
        int idVal = lastID + 1;

        if (Database.getCount() < 100) {
            System.out.println(" Default Member      :   1 \n " +
                    "Student Member      :   2 \n " +
                    "Over 60 Member      :   3 \n"
            );
            System.out.println("Number of Registrations Available : " + (100 - Database.getCount()));
            System.out.print("Category to Register : ");
            int optionCat = sc.nextInt();
            System.out.println("");

            if (optionCat == 1) {
                Scanner strInput = new Scanner(System.in);
                Scanner intInput = new Scanner(System.in);
                Scanner cityInput = new Scanner(System.in);

                System.out.println("Default Member Registration");
                System.out.println("-------------------------------");

                System.out.print("Enter the Name : ");
                String name = strInput.nextLine();
                System.out.print("Enter the Gender : ");
                String gender = intInput.nextLine();
                System.out.print("Enter the City : ");
                String city = cityInput.nextLine();

                Database.DefaultMemberCreate(idVal, name,gender,city);
                myGymManager.endingPart("Registration Successful");



            } else if (optionCat == 2) {
                Scanner strInput = new Scanner(System.in);
                Scanner intInput = new Scanner(System.in);
                Scanner cityInput = new Scanner(System.in);

                System.out.println("Student Member Registration");
                System.out.println("-------------------------------");

                System.out.print("Enter the Name : ");
                String name = strInput.nextLine();

                System.out.print("Enter the Gender : ");
                String gender = intInput.nextLine();

                System.out.print("Enter the City : ");
                String city = cityInput.nextLine();

                System.out.print("Enter the School : ");
                String school = strInput.nextLine();

                Database.StudentMemberCreate(idVal, name, school,gender,city);
                myGymManager.endingPart("Registration Successful");


            } else if (optionCat == 3) {
                Scanner strInput = new Scanner(System.in);
                Scanner intInput = new Scanner(System.in);
                Scanner genderInput = new Scanner(System.in);
                Scanner cityInput = new Scanner(System.in);

                System.out.println("Over60 Member Registration");
                System.out.println("-------------------------------");


                System.out.print("Enter the Name : ");
                String name = strInput.nextLine();

                System.out.print("Enter the Gender : ");
                String gender = genderInput.nextLine();

                System.out.print("Enter the Age : ");
                int age = intInput.nextInt();

                System.out.print("Enter the City : ");
                String city = cityInput.nextLine();

                Database.Over60MemberCreate(idVal, name, age,gender,city);
                myGymManager.endingPart("Registration Successful");

            }else{
                try{
                    System.out.println("Invalid Input");
                    System.out.println("-----------------------------");
                    System.out.println("");
                    myGymManager.functionChoose(myGymManager.menuOption());
                }catch (InputMismatchException | FileNotFoundException e){
                    System.out.println("Invalid Input");
                    System.out.println("-----------------------------");
                    System.out.println("");
                    try {
                        myGymManager.functionChoose(myGymManager.menuOption());
                    } catch (InputMismatchException fileNotFoundException) {
                        myGymManager.functionChoose(myGymManager.menuOption());
                    }
                }
            }
        } else if (Database.getCount() == 100) {
            System.out.println("Registration limit of 100 is reached :(");
        }
//        myGymManager.menuOption();
    }


    public void deleteMember() {
        MyGymManager myGymManager = new MyGymManager();

        Scanner strInput = new Scanner(System.in);
        System.out.println("Member Deletion");
        System.out.println("-------------------------------");
        System.out.print("Enter the Name of the member you want to delete : ");
        String name = strInput.nextLine();
        Database.deleteUser(name);
        myGymManager.endingPart("Deletion Successful");
//        System.out.println(name + " is Deleted from the Registration List");
    }

    public void printAll() {
        MyGymManager myGymManager = new MyGymManager();

        System.out.println("Names of all Members");
        System.out.println("-------------------------------");
        Database.printAll();

        myGymManager.endingPart("");
    }

    public void sorting() {
        MyGymManager myGymManager = new MyGymManager();

        Scanner intInput = new Scanner(System.in);
        System.out.println(" Sort Name in Ascending     : 1\n " + "Sort Name in Descending    : 2");
        System.out.println("");
        System.out.print("Choose the Sorting method : ");
        int sortOption = intInput.nextInt();

        if (sortOption == 1) {
            Database.sorting("name", 1);
        } else if (sortOption == 2) {
            Database.sorting("name", -1);
        }
        myGymManager.endingPart("");

    }

    public void fileSave() throws IOException {
        MyGymManager myGymManager = new MyGymManager();

        FileSave.fileWrite();
        myGymManager.endingPart("file saved successfully");

    }

    public void openGUI() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GraphicalInterface.display();
    }

    public void endingPart(String message){
        MyGymManager myGymManager = new MyGymManager();
        Scanner strInput = new Scanner(System.in);
        System.out.println("");
        System.out.println(message);
        System.out.println("");
        System.out.print("You want to do another operation? (y/n) : ");
        String answer = strInput.nextLine();
        System.out.println("");
        if(answer.equals("y")){
            myGymManager.menuOption();
        }else if(answer.equals("n")){
            System.exit(0);
        }else{
            System.out.println("Invalid input");
        }
    }


}










