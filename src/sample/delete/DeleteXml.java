package sample.delete;

import assistance.database.DatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class DeleteXml implements Initializable{
    DatabaseHandler databaseHandler;
    @FXML
    private TextField bookid;
    Boolean bois=false,b;
    @FXML
    private Button onidsubmit;

    @FXML
    private TextArea bookabout;

    @FXML
    private Button deletebook;

    @FXML
    private TextField memberid;

    @FXML
    private Button onid2submit;

    @FXML
    private TextArea memberabout;

    @FXML
    private Button deletemember;
    @FXML
    void deleteb(ActionEvent event) {
    if(b) {
        String bod=bookid.getText();
        String qq = "DELETE FROM BOOK WHERE id='" + bod + "'";
        if(databaseHandler.execAct(qq)){
            Alert alert1=new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("SUCCESS");
            alert1.setHeaderText(null);
            alert1.setContentText("Book Deleted");
            alert1.showAndWait();
        }
        else {
            Alert alert1=new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("FAILED");
            alert1.setHeaderText(null);
            alert1.setContentText("ERROR");
            alert1.showAndWait();
        }
    }
    else {
        Alert alert1=new Alert(Alert.AlertType.ERROR);
        alert1.setTitle("FAILURE");
        alert1.setHeaderText(null);
        alert1.setContentText("Book Issued By Someone");
        alert1.showAndWait();
    }
    }

    @FXML
    void deletem(ActionEvent event) {
        if(!bois) {
            String bod=memberid.getText();
            String qqa = "DELETE FROM MEMBER WHERE mid='" + bod + "'";
            if(databaseHandler.execAct(qqa)){
                Alert alert1=new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("SUCCESS");
                alert1.setHeaderText(null);
                alert1.setContentText("Member Deleted");
                alert1.showAndWait();
            }
            else {
                Alert alert1=new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("FAILED");
                alert1.setHeaderText(null);
                alert1.setContentText("ERROR 404");
                alert1.showAndWait();
            }
        }
        else {
            Alert alert1=new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("FAILURE");
            alert1.setHeaderText(null);
            alert1.setContentText("This Member has issued some books");
            alert1.showAndWait();
        }
    }

    @FXML
    void submitb(ActionEvent event) {
    String bid= bookid.getText();
    String qu="SELECT * FROM BOOK WHERE id='"+bid+"'";
        ResultSet rs= databaseHandler.execquery(qu);
        try {
            while (rs.next()){
            String name="Name :- "+ rs.getString("title")+"\n";
            name=name+"Author :- "+rs.getString("author")+"\n";
            b=rs.getBoolean("isAvail");
            String bb=(b)?"In Library":"Not in Library";
            name=name+"Where :- "+bb;
            bookabout.setText(name);
            }
        }catch (Exception e){
        e.printStackTrace();
        }
    }

    @FXML
    void submitm(ActionEvent event) {
        String mid= memberid.getText();
        String qu="SELECT * FROM MEMBER WHERE mid='"+mid+"'";
        ResultSet rs= databaseHandler.execquery(qu);
        try {bois=false;
            while (rs.next()){
                String mname="Name :- "+rs.getString("name")+"\n";
                mname=mname+"Mobile :- "+rs.getString("mobile")+"\n";
                String qqu="SELECT * FROM ISSUE WHERE memberid='"+mid+"'";
                ResultSet rt=databaseHandler.execquery(qqu);
                try {
                    while(rt.next()){
                        bois=true;
                    }
                }catch (Exception ee){
                    ee.printStackTrace();
                }
                String any=(bois)?"This Member has Book Issued (Call)":"No Issued Book";
                mname=mname+"Issued Book :- "+any;
                memberabout.setText(mname);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if(bois){
            Alert alert1=new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Failure");
            alert1.setHeaderText(null);
            alert1.setContentText("This Member has a book issued on his/her name");
            alert1.showAndWait();
    }}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        databaseHandler=DatabaseHandler.getInstance();
    }
}
