package sample.addbook;

import assistance.database.DatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML TextField id1;
    @FXML TextField author;
    @FXML TextField title;
    @FXML TextField publisher;
    @FXML
    private Button save;
    @FXML
    private Button cancel;
   private DatabaseHandler databaseHandler;
    @FXML
    public void cancel(ActionEvent actionEvent) {
        Stage stage=(Stage) cancel.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void addbook(ActionEvent actionEvent) {
        String bookID=id1.getText();
        String bookAuthor=author.getText();
        String bookTitle=title.getText();
        String bookPublisher=publisher.getText();
if(bookID.isEmpty()||bookAuthor.isEmpty()||bookTitle.isEmpty()||bookPublisher.isEmpty()){
    Alert alert=new Alert(Alert.AlertType.ERROR);
    alert.setHeaderText(null);
    alert.setContentText("Please Enter in All fields");
    alert.showAndWait();
return;
}
String qu="INSERT INTO BOOK (id,title,author,publisher,isAvail) VALUES  ("
        +"'"+bookID+"',"+
        "'"+bookTitle+"',"+
        "'"+bookAuthor+"',"+
        "'"+bookPublisher+"',"+
        ""+true+""+
        ")";
    System.out.println(qu);
    if(databaseHandler.execAct(qu)){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("SUCCESS");
        alert.showAndWait();
    }else {
        Alert alert=new Alert(Alert.AlertType.ERROR );
        alert.setHeaderText(null);
        alert.setContentText("FAILURE");
        alert.showAndWait();
    }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
databaseHandler=DatabaseHandler.getInstance();
checkData();
    }

    private void checkData() {
    String qu="SELECT title FROM BOOK";
        ResultSet rs=databaseHandler.execquery(qu);
    try {
        while (rs.next()) {
            String titlex = rs.getString("title");
     System.out.println(titlex);
        }    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    }

