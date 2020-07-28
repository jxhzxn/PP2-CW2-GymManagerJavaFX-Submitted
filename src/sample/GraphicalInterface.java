package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import static java.lang.Double.parseDouble;

public class GraphicalInterface {

    public static void display() throws FileNotFoundException {
        Stage window = new Stage();
        Scene keyBoardScene;
        window.setTitle("Simple Savings");

        //creating a new Decimal Formatter
        DecimalFormat df = new DecimalFormat("#.##");


        //Creating the TextFields
        Text futureValueText = new Text("Future Value");
        Text presentValueText = new Text("Present Value");
        Text interestRateText = new Text("Interest Rate");
        Text yearsText = new Text("Time in Years");

        Button calculateBtn = new Button("Calculate");
        calculateBtn.setId("keyboardButton");
        calculateBtn.setLayoutX(90);
        calculateBtn.setLayoutY(570);

        Button resetBtn = new Button("Reset");
        resetBtn.setId("keyboardButton");
        resetBtn.setLayoutX(320);
        resetBtn.setLayoutY(570);

        Label headText = new Label("Simple Saving");
        headText.setLayoutX(10);
        headText.setLayoutY(50);
        headText.setId("headText");


        futureValueText.setId("fdText");
        presentValueText.setId("fdText");
        interestRateText.setId("fdText");
        yearsText.setId("fdText");

        futureValueText.setLayoutX(50);
        futureValueText.setLayoutY(220);

        presentValueText.setLayoutX(50);
        presentValueText.setLayoutY(290);

        interestRateText.setLayoutX(50);
        interestRateText.setLayoutY(360);

        yearsText.setLayoutX(50);
        yearsText.setLayoutY(430);


        //creating the textfields
        TextField futureValueField = createTextField(300,180,150,60,"futureValueField");
        TextField presentValueField =    createTextField(300,250,150,60,"presentValueField");
        TextField interestRateField =   createTextField(300,320,150,60,"interestRateField");
        TextField yearsField =          createTextField(300,390,150,60,"yearsField");







        Pane simpleSavingPane = new Pane();

        simpleSavingPane.getChildren().addAll(
                futureValueText,presentValueText,interestRateText,yearsText,
                presentValueField,interestRateField,yearsField,calculateBtn, futureValueField, resetBtn,headText
        );

        keyBoardScene = new Scene(simpleSavingPane,900,700);
//        keyBoardScene.getStylesheets().add(HomePage.class.getResource("stylesheet.css").toExternalForm());
        simpleSavingPane.setStyle("-fx-background-color: #f75e50;");

        window.setScene(keyBoardScene);
        window.show();



    }

    //Method to create TextFields
    private static TextField createTextField(double x, double y, double j, double k, String id){
        TextField textField = new TextField();
        textField.setLayoutX(x);
        textField.setLayoutY(y);
        textField.setId(id);
        textField.setPrefSize(j,k);

        return textField;
    }
}
