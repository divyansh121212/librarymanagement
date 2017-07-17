package sample.delete;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class deleter extends Application {
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("delete.xml.fxml"));
        stage.setScene(new Scene(root, 900, 550));
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
