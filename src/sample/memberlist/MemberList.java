package sample.memberlist;

import assistance.database.DatabaseHandler;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.booklist.BookList;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MemberList implements Initializable {
    private ObservableList<MemberList.Member> list= FXCollections.observableArrayList();
    @FXML
    private TableView<Member> vieww;

    @FXML
    private TableColumn<Member, String> id;

    @FXML
    private TableColumn<Member, String > nameb;

    @FXML
    private TableColumn<Member, String > mobileb;

    @FXML
    private TableColumn<Member, String > emailb;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCol();
        loadData();
    }
    private void loadData() {
        DatabaseHandler handler=DatabaseHandler.getInstance();
        String qu="SELECT * FROM MEMBER";
        ResultSet rs=handler.execquery(qu);
        try {
            while (rs.next()) {
                String idx = rs.getString("mid");
                String nameex = rs.getString("name");
                String mobliex = rs.getString("mobile");
                String emailx = rs.getString("email");
                list.add(new MemberList.Member(idx,nameex,mobliex,emailx));
            }    } catch (SQLException e) {
            e.printStackTrace();
        }
        vieww.getItems().setAll(list);
    }

    private void initCol() {
        nameb.setCellValueFactory(new PropertyValueFactory<>("name"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        mobileb.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        emailb.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    public static   class Member {
        private final SimpleStringProperty id;
        private final SimpleStringProperty name;
        private final SimpleStringProperty mobile;

        public String getId() {
            return id.get();
        }

        public SimpleStringProperty idProperty() {
            return id;
        }

        public String getName() {
            return name.get();
        }

        public SimpleStringProperty nameProperty() {
            return name;
        }

        public String getMobile() {
            return mobile.get();
        }

        public SimpleStringProperty mobileProperty() {
            return mobile;
        }

        public String getEmail() {
            return email.get();
        }

        public SimpleStringProperty emailProperty() {
            return email;
        }

        private final SimpleStringProperty email;

        Member(String id, String name, String mobile, String email) {
            this.id = new SimpleStringProperty(id);
            this.name = new SimpleStringProperty(name);
            this.mobile = new SimpleStringProperty(mobile);
            this.email = new SimpleStringProperty(email);
        }
    }
    }
