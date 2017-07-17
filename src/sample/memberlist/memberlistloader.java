package sample.memberlist;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class memberlistloader extends Application{
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("member_list.fxml"));
        stage.setScene(new Scene(root, 942, 545));
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


}
