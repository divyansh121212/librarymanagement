package sample.name.main;


import assistance.database.DatabaseHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class mainloader extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("maindesign.fxml"));
        primaryStage.setScene(new Scene(root, 1000, 620));
        primaryStage.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseHandler.getInstance();
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
