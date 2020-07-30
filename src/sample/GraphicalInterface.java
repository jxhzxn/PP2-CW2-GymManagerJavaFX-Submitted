package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class GraphicalInterface{
    public static void display(){
        Stage window = new Stage();
        Scene guiScene;
        window.setTitle("Gym Manager");

        TableView<DefaultMember> table;

        TextField searchField = new TextField();
        searchField.setLayoutX(90);
        searchField.setLayoutY(50);

        Button searchBtn = new Button("Search");
        searchBtn.setLayoutX(350);
        searchBtn.setLayoutY(50);

        Button showAllBtn = new Button("Show All");
        showAllBtn.setLayoutX(450);
        showAllBtn.setLayoutY(50);

        Label lbl1 = new Label();
        lbl1.setLayoutX(90);
        lbl1.setLayoutY(540);

        searchField.setId("searchField");
        searchBtn.setId("searchBtn");
        showAllBtn.setId("searchBtn");
        lbl1.setId("label");

        String remaining = String.valueOf(100-Database.getCount());
        String reg = String.valueOf(Database.getCount());


        lbl1.setText("Registrations : "+reg+" / 100");


        //nameColumn
        TableColumn<DefaultMember,String> nameColumn = new TableColumn<>("Full Name");
        nameColumn.setMinWidth(250);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        //genderColumn
        TableColumn<DefaultMember,String> genderColumn = new TableColumn<>("Gender");
        genderColumn.setMinWidth(100);
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));


        //cityColumn
        TableColumn<DefaultMember,String> cityColumn = new TableColumn<>("City");
        cityColumn.setPrefWidth(200);
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));

        //memTypeColumn
        TableColumn<DefaultMember,String> memTypeColumn = new TableColumn<>("Member Type");
        memTypeColumn.setPrefWidth(150);
        memTypeColumn.setCellValueFactory(new PropertyValueFactory<>("memType"));







        table = new TableView<>();
        table.setItems(getMember());


        searchBtn.setOnAction(event -> {
            table.setItems(nameSearch(searchField));
        });

        showAllBtn.setOnAction(event -> {
            searchField.clear();
            table.setItems(getMember());
        });

        table.getColumns().addAll(nameColumn,genderColumn,cityColumn,memTypeColumn);

        nameColumn.setId("column");
        genderColumn.setId("column");
        cityColumn.setId("column");
        memTypeColumn.setId("column");
        table.setId("table");

        table.setLayoutX(90);
        table.setLayoutY(120);
        table.setPrefWidth(750);




        Pane simpleSavingPane = new Pane();
        simpleSavingPane.getChildren().addAll(table,searchBtn,searchField,showAllBtn,lbl1);
        guiScene = new Scene(simpleSavingPane,930,630);
        guiScene.getStylesheets().add(GraphicalInterface.class.getResource("stylesheet.css").toExternalForm());

        window.setScene(guiScene);
        window.show();

    }

    public static ObservableList<DefaultMember> getMember(){
        ObservableList<DefaultMember> defaultMembers = FXCollections.observableArrayList();
        ArrayList<String> name = Database.readCustom("name");
        ArrayList<String> id = Database.readCustom("_id");
        ArrayList<String> memType = Database.readCustom("mem_type");
        ArrayList<String> gender = Database.readCustom("gender");
        ArrayList<String> city = Database.readCustom("city");

//        Integer.parseInt(id.get(count))
        for(int count=0; count<=name.size()-1; count++){
            defaultMembers.add(new DefaultMember(Integer.parseInt(id.get(count)),name.get(count),memType.get(count),gender.get(count),city.get(count)));
        }
        return defaultMembers;
    }


    public static ObservableList<DefaultMember> nameSearch(TextField textField){
        ObservableList<DefaultMember> defaultMembers = FXCollections.observableArrayList();

        try{
            String searchName = textField.getText().toUpperCase();

            ArrayList<String> nameArray = Database.nameSearch(Database.readNameTest(searchName),"name");
            ArrayList<String> memTypeArray = Database.nameSearch(Database.readNameTest(searchName),"mem_type");
            ArrayList<String> genderArray = Database.nameSearch(Database.readNameTest(searchName),"gender");
            ArrayList<String> cityArray = Database.nameSearch(Database.readNameTest(searchName),"city");

            for (int i=0; i<=nameArray.size()-1;i++){
                defaultMembers.add(new DefaultMember(23,nameArray.get(i),memTypeArray.get(i),genderArray.get(i),cityArray.get(i)));
            }


        }catch (NoSuchElementException e){

        }
        return defaultMembers;
    }

}


