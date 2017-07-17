package sample.name.main;

import assistance.database.DatabaseHandler;
import com.jfoenix.effects.JFXDepthManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.ResourceBundle;

public class Maindesign implements Initializable {

    DatabaseHandler databaseHandler;
    @FXML
    private TextField bookkid;
    @FXML
    private TextField bookidinput;
    @FXML
    private TextField memsea;
    @FXML
    private Text bookname;
    @FXML
    private Text memname;

    @FXML
    private Text contact;
    @FXML
    private Text author;

    @FXML
    private Text bookstatus;
    @FXML
        private Button meme;

        @FXML
        private Button boke;

        @FXML
        private Button vmeme;

        @FXML
        private Button vbokw;

        @FXML
        private Button setting;

        @FXML
        private HBox hboxcard;
    @FXML
    private Button issuebutton;

        @FXML
    private HBox hboxca;
    @FXML
    private ListView<String> listvv;
    Boolean isReadyforsubmission=false;
    @FXML
    void loadissueoperation(ActionEvent event) {
String memid=memsea.getText();
String bookid=bookidinput.getText();
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Issue Operation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to issue book "+bookname.getText()+"\n"+"to "+memname.getText()+"?");
        Optional<ButtonType> response=alert.showAndWait();
        if(response.get()==ButtonType.OK){
            String str="INSERT INTO ISSUE(memberid,bookid) VALUES ("
                    +"'"+memid+"',"
                    +"'"+bookid+"')";
            String str2="UPDATE BOOK SET isAvail=false WHERE id='"+bookid+"'";
            if(databaseHandler.execAct(str)&&databaseHandler.execAct(str2)){
                Alert alert1=new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Success");
                alert1.setHeaderText(null);
                alert1.setContentText("Book Issue Complete");
                alert1.showAndWait();
            }else {
                Alert alert2=new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("Failure");
                alert2.setHeaderText(null);
                alert2.setContentText("Issue Operation Failed");
                alert2.showAndWait();
            }
        }else {
            Alert alert2=new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Cancelled");
            alert2.setHeaderText(null);
            alert2.setContentText("Issue Operation Cancelled");
            alert2.showAndWait();
        }
    }

    @FXML
    void loadbookdata(ActionEvent event) {
String id=bookidinput.getText();
String qu="SELECT * FROM BOOK WHERE id='"+id+"'";
        ResultSet rs=databaseHandler.execquery(qu);
        Boolean flag=false;
        try {
            while (rs.next()){
                String bmane=rs.getString("title");
                String bauthor=rs.getString("author");
                Boolean bavail=rs.getBoolean("isAvail");
                bookname.setText(bmane);
                author.setText(bauthor);
                String status=(bavail)?"Is Available":"Not Available";
             bookstatus.setText(status);
            flag=true;
            }
            if(!flag){
                bookname.setText("Book Not Availble");
                author.setText("");
                bookstatus.setText("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void loadmem(ActionEvent event) {
        String id=memsea.getText();
        String qu="SELECT * FROM MEMBER WHERE mid='"+id+"'";
        ResultSet rs=databaseHandler.execquery(qu);
        Boolean flag=false;
        try {
            while (rs.next()){
                String bmane=rs.getString("name");
                String bauthor=rs.getString("mobile");
                memname.setText(bmane);
                contact.setText(bauthor);
                flag=true;
            }
            if(!flag){
                memname.setText("No Member");
                contact.setText("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        @FXML
        void addbook(ActionEvent event) {
            loadWindow("/sample/addbook/sample.fxml","Add Book");
        }

        @FXML
        void addmem(ActionEvent event) {
            loadWindow("/sample/addMember/add-Member.fxml","Add Member");
        }

        @FXML
        void settings(ActionEvent event) {
            loadWindow("/sample/delete/delete.xml.fxml","Delete Member");
        }

        @FXML
        void viewboke(ActionEvent event) {
            loadWindow("/sample/booklist/book_list.fxml","Book List");
        }

        @FXML
        void viewmem(ActionEvent event) {
            loadWindow("/sample/memberlist/member_list.fxml","Member List");
        }

        @Override
        public void initialize(URL location, ResourceBundle resources) {
JFXDepthManager.setDepth(hboxcard,2);
            JFXDepthManager.setDepth(hboxca,2);
            databaseHandler=DatabaseHandler.getInstance();
        }
        private void loadWindow(String loc, String title){
            try {
                Parent parent= FXMLLoader.load(getClass().getResource(loc));
                Stage stage=new Stage(StageStyle.DECORATED);
                stage.setTitle(title);
                stage.setScene(new Scene(parent));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    @FXML
    void loadbookinfo(ActionEvent event) {
        isReadyforsubmission=false;
            ObservableList<String> issuelistdata = FXCollections.observableArrayList();
        String id=bookkid.getText();
String qu="SELECT * FROM ISSUE WHERE bookid='"+id+"'";
ResultSet rt=databaseHandler.execquery(qu);
        try {
            while (rt.next()){
                String mBookid=id;
                String mMemberid=rt.getString("memberid");
                Timestamp timestamp=rt.getTimestamp("issuetime");
                int mrenewcount=rt.getInt("renew_count");
                issuelistdata.add("Issue Date and Time: "+timestamp.toGMTString());
                issuelistdata.add("Renew Count: "+mrenewcount);
                issuelistdata.add("Book Information:- ");
                qu="SELECT * FROM BOOK WHERE id='"+mBookid+"'";
                ResultSet r1=databaseHandler.execquery(qu);
                while(r1.next()){
issuelistdata.add("         Book Name:- "+r1.getString("title"));
                    issuelistdata.add("         Book Id:- "+r1.getString("id"));
                    issuelistdata.add("         Book Author:- "+r1.getString("author"));
                    issuelistdata.add("         Book Publisher:- "+r1.getString("publisher"));
                }
                issuelistdata.add("Member Information:- ");
                qu="SELECT * FROM MEMBER WHERE mid='"+mMemberid+"'";
                 r1=databaseHandler.execquery(qu);
                while(r1.next()){
                    issuelistdata.add("          Member name:- "+r1.getString("name"));
                    issuelistdata.add("          Member ID:- "+r1.getString("mid"));
                    issuelistdata.add("          Member Mobile:- "+r1.getString("mobile"));
                    issuelistdata.add("          Member E-mail:- "+r1.getString("email"));

                }
                isReadyforsubmission=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
listvv.getItems().setAll(issuelistdata);
        }

    @FXML
    void submitbutton(ActionEvent event) {
if(!isReadyforsubmission){
    Alert alert1=new Alert(Alert.AlertType.ERROR);
    alert1.setTitle("Failure");
    alert1.setHeaderText(null);
    alert1.setContentText("Please select a book to Submit");
    alert1.showAndWait();
    return;
}
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Issue Operation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to issue book "+bookkid.getText()+"\n"+" ?");
        Optional<ButtonType> response=alert.showAndWait();
        if(response.get()==ButtonType.OK){
String id=bookkid.getText();
String ac="DELETE FROM ISSUE WHERE bookid='"+id+"'";
String ac1="UPDATE BOOK SET isAvail=true WHERE id='"+id+"'";
if(databaseHandler.execAct(ac)&&databaseHandler.execAct(ac1)){
    Alert alert1=new Alert(Alert.AlertType.INFORMATION);
    alert1.setTitle("Success");
    alert1.setHeaderText(null);
    alert1.setContentText("Book has been Submitted");
    alert1.showAndWait();
}else {
    Alert alert1=new Alert(Alert.AlertType.ERROR);
    alert1.setTitle("Failure");
    alert1.setHeaderText(null);
    alert1.setContentText("Submission has been Failed");
    alert1.showAndWait();
}
    }else {
            Alert alert2=new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Cancelled");
            alert2.setHeaderText(null);
            alert2.setContentText("Submission Operation Cancelled");
            alert2.showAndWait();
        }
    }
    @FXML
    void renewaction(ActionEvent event) {
        if(!isReadyforsubmission){
            Alert alert1=new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Failure");
            alert1.setHeaderText(null);
            alert1.setContentText("Please select a book to Renew");
            alert1.showAndWait();
            return;
        }
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Renew Operation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to Renew book "+bookkid.getText()+"\n"+" ?");
        Optional<ButtonType> response=alert.showAndWait();
        if(response.get()==ButtonType.OK){
String ac="UPDATE ISSUE SET issuetime=CURRENT_TIMESTAMP,renew_count=renew_count+1 WHERE bookid='"+bookkid.getText() +"'";
System.out.println(ac);
            if(databaseHandler.execAct(ac)&&databaseHandler.execAct(ac)){
                Alert alert1=new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Success");
                alert1.setHeaderText(null);
                alert1.setContentText("Book has been Renew");
                alert1.showAndWait();
            }else {
                Alert alert1=new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Failure");
                alert1.setHeaderText(null);
                alert1.setContentText("Renew has been Failed");
                alert1.showAndWait();
            }

        }
    }
}


