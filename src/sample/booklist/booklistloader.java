package sample.booklist;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class booklistloader extends Application {


        @Override
        public void start(Stage stage) throws Exception{
            Parent root = FXMLLoader.load(getClass().getResource("book_list.fxml"));
            stage.setScene(new Scene(root, 900, 550));
            stage.show();
        }


        public static void main(String[] args) {
            launch(args);
        }


}
