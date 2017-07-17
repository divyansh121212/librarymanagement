package sample.addMember;

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

public class AddMember implements Initializable {
    private DatabaseHandler handler;
    @FXML
    private TextField memid;

    @FXML
    private TextField name;

    @FXML
    private TextField mobile;

    @FXML
    private TextField email;

    @FXML
    private Button add;

    @FXML
    private Button cancel;

    @FXML
    void addMember(ActionEvent event) {
        String mid=memid.getText();
        String mname=name.getText();
        String mmobile=mobile.getText();
        String memail=email.getText();
        if(mid.isEmpty()||mname.isEmpty()||mmobile.isEmpty()||memail.isEmpty()){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter in fields");
            alert.showAndWait();
            return;
        }
        String st="INSERT INTO MEMBER (mid,name,mobile,email) VALUES  ("
                +"'"+mid+"',"+
                "'"+mname+"',"+
                "'"+mmobile+"',"+
                "'"+memail+"'"+
                " )";
        System.out.println(st);
        if(handler.execAct(st)){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Member Added");
            alert.showAndWait();
        }else {
            Alert alert=new Alert(Alert.AlertType.ERROR );
            alert.setHeaderText(null);
            alert.setContentText("FAILURE");
            alert.showAndWait();
        }
    }

    @FXML
    void cancel(ActionEvent event) {
        Stage stage=(Stage) cancel.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
handler=DatabaseHandler.getInstance();
    }
    private void checkData() {
        String qu="SELECT name FROM MEMBER";
        ResultSet rs=handler.execquery(qu);
        try {
            while (rs.next()) {
                String titlex = rs.getString("name");
                System.out.println(titlex);
            }    } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


