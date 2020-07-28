package sample;

import javafx.application.Application;
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

public class GraphicalInterface{
    public static void display(){
        Stage window = new Stage();
        Scene keyBoardScene;
        window.setTitle("Gym Manager GUI");

        Pane simpleSavingPane = new Pane();

        keyBoardScene = new Scene(simpleSavingPane,700,600);

        window.setScene(keyBoardScene);
        window.show();
    }

}


