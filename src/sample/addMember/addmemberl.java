package sample.addMember;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class addmemberl extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("add-Member.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 520, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
