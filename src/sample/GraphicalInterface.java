package sample;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GraphicalInterface{
    public static void display(){
        Stage window = new Stage();
        Scene keyBoardScene;
        window.setTitle("Gym Manager GUI");

        TableView<DefaultMember> table;

        //column1
        TableColumn<DefaultMember, String> nameColumn = new TableColumn<>("name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));




        //column2
        TableColumn<DefaultMember,String> priceColumn = new TableColumn<>("price");
        priceColumn.setMinWidth(200);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        table = new TableView<>();
        table.setItems(getMember());
        table.getColumns().addAll(priceColumn,nameColumn);

        table.setLayoutX(50);
        table.setLayoutY(50);


        Button btn = new Button("testing");
        btn.setLayoutX(100);
        btn.setLayoutY(100);

        btn.setOnAction(event -> {
            window.close();
            MyGymManager myGymManager = new MyGymManager();
            try {
                myGymManager.functionChoose(myGymManager.menuOption());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        Pane simpleSavingPane = new Pane();

        simpleSavingPane.getChildren().addAll(table);

        keyBoardScene = new Scene(simpleSavingPane,700,600);

        window.setScene(keyBoardScene);
        window.show();

    }

    public static ObservableList<DefaultMember> getMember(){
        ObservableList<DefaultMember> defaultMembers = FXCollections.observableArrayList();
        ArrayList<String> names = Database.readName();


        for(int count=0; count<=names.size()-1; count++){
            defaultMembers.add(new DefaultMember(34,names.get(count),"erf","rf","rfrf"));
        }

        return defaultMembers;

    }

}


