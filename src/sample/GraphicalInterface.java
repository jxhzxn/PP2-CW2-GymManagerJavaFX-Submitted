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

//        //idColumn
//        TableColumn<DefaultMember, String> idColumn = new TableColumn<>("ID");
//        idColumn.setMinWidth(50);
//        idColumn.setCellValueFactory(new PropertyValueFactory<>("mem_no"));

        //nameColumn
        TableColumn<DefaultMember,String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(150);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        //genderColumn
        TableColumn<DefaultMember,String> genderColumn = new TableColumn<>("Gender");
        genderColumn.setMinWidth(50);
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));

//        //ageColumn
//        TableColumn<DefaultMember,String> ageColumn = new TableColumn<>("Age");
//        ageColumn.setMinWidth(50);
//        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
//
//        //schoolColumn
//        TableColumn<DefaultMember,String> schoolColumn = new TableColumn<>("School");
//        schoolColumn.setMinWidth(50);
//        schoolColumn.setCellValueFactory(new PropertyValueFactory<>("schoolName"));

        //cityColumn
        TableColumn<DefaultMember,String> cityColumn = new TableColumn<>("City");
        cityColumn.setMinWidth(50);
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));

        //memTypeColumn
        TableColumn<DefaultMember,String> memTypeColumn = new TableColumn<>("Member Type");
        memTypeColumn.setMinWidth(50);
        memTypeColumn.setCellValueFactory(new PropertyValueFactory<>("memType"));

        table = new TableView<>();
        table.setItems(getMember());
        table.getColumns().addAll(nameColumn,genderColumn,cityColumn,memTypeColumn);

        table.setLayoutX(150);
        table.setLayoutY(50);
        table.setPrefWidth(470);


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

}


