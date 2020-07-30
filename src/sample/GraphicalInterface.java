package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class GraphicalInterface{
    public static void display(){
        Stage window = new Stage();         //setting the stage
        Scene guiScene;
        window.setTitle("Gym Manager");

        TableView<DefaultMember> table;     //Initializing the tableView

        //Creating a TextField
        TextField searchField = new TextField();
        searchField.setLayoutX(90);
        searchField.setLayoutY(50);

        //creating a button for the search
        Button searchBtn = new Button("Search");
        searchBtn.setLayoutX(350);
        searchBtn.setLayoutY(50);

        //creating a button for showAll
        Button showAllBtn = new Button("Show All");
        showAllBtn.setLayoutX(450);
        showAllBtn.setLayoutY(50);

        //creating a label
        Label lbl1 = new Label();
        lbl1.setLayoutX(90);
        lbl1.setLayoutY(540);


        String remaining = String.valueOf(100-Database.getCount());
        String reg = String.valueOf(Database.getCount());


        //Setting the label text
        lbl1.setText("Registrations : "+reg+" / 100");


        //creating nameColumn
        TableColumn<DefaultMember,String> nameColumn = new TableColumn<>("Full Name");
        nameColumn.setMinWidth(250);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        //creating genderColumn
        TableColumn<DefaultMember,String> genderColumn = new TableColumn<>("Gender");
        genderColumn.setMinWidth(100);
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));


        //creating cityColumn
        TableColumn<DefaultMember,String> cityColumn = new TableColumn<>("City");
        cityColumn.setPrefWidth(200);
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));

        //creating memTypeColumn
        TableColumn<DefaultMember,String> memTypeColumn = new TableColumn<>("Member Type");
        memTypeColumn.setPrefWidth(150);
        memTypeColumn.setCellValueFactory(new PropertyValueFactory<>("memType"));

        //creating the table
        table = new TableView<>();

        //setting the items on the table
        table.setItems(getMember());

        //Setting the lambda function for the search Button
        searchBtn.setOnAction(event -> {
            table.setItems(nameSearch(searchField));
        });

        //setting the lambda function for the ShowAll button
        showAllBtn.setOnAction(event -> {
            searchField.clear();
            table.setItems(getMember());
        });

        //setting all the columns on the table
        table.getColumns().addAll(nameColumn,genderColumn,cityColumn,memTypeColumn);

        //Setting the IDs for the elements
        searchField.setId("searchField");
        searchBtn.setId("searchBtn");
        showAllBtn.setId("searchBtn");
        lbl1.setId("label");
        nameColumn.setId("column");
        genderColumn.setId("column");
        cityColumn.setId("column");
        memTypeColumn.setId("column");
        table.setId("table");

        //setting the position and the width of the table
        table.setLayoutX(90);
        table.setLayoutY(120);
        table.setPrefWidth(750);




        Pane simpleSavingPane = new Pane();
        simpleSavingPane.getChildren().addAll(table,searchBtn,searchField,showAllBtn,lbl1);        //adding the child elements to the pane
        guiScene = new Scene(simpleSavingPane,930,630);

        //linking the external CSS
        guiScene.getStylesheets().add(GraphicalInterface.class.getResource("stylesheet.css").toExternalForm());

        window.setScene(guiScene);
        window.show();

//        //When the UI close Button is Clicked;
//        window.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
//            window.close();
//            MyGymManager myGymManager = new MyGymManager();
//            try {
//                myGymManager.functionChoose(myGymManager.menuOption());
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        });

    }

    //method to populate the Table by creating the members again by using the DefaultMembers Constructor, using the information fetched from the Database
    public static ObservableList<DefaultMember> getMember(){
        ObservableList<DefaultMember> defaultMembers = FXCollections.observableArrayList();
        ArrayList<String> name = Database.readCustom("name");
        ArrayList<String> id = Database.readCustom("_id");
        ArrayList<String> memType = Database.readCustom("mem_type");
        ArrayList<String> gender = Database.readCustom("gender");
        ArrayList<String> city = Database.readCustom("city");

        for(int count=0; count<=name.size()-1; count++){
            defaultMembers.add(new DefaultMember(Integer.parseInt(id.get(count)),name.get(count),memType.get(count),gender.get(count),city.get(count)));
        }
        return defaultMembers;
    }


    //method to populate the table with the searched name
    public static ObservableList<DefaultMember> nameSearch(TextField textField){
        ObservableList<DefaultMember> defaultMembers = FXCollections.observableArrayList();

        try{
            String searchName = textField.getText().toUpperCase();

            ArrayList<String> nameArray = Database.nameSearch(Database.readNameSearch(searchName),"name");
            ArrayList<String> memTypeArray = Database.nameSearch(Database.readNameSearch(searchName),"mem_type");
            ArrayList<String> genderArray = Database.nameSearch(Database.readNameSearch(searchName),"gender");
            ArrayList<String> cityArray = Database.nameSearch(Database.readNameSearch(searchName),"city");

            for (int i=0; i<=nameArray.size()-1;i++){
                defaultMembers.add(new DefaultMember(23,nameArray.get(i),memTypeArray.get(i),genderArray.get(i),cityArray.get(i)));
            }


        }catch (NoSuchElementException e){

        }
        return defaultMembers;
    }

}


