package sample.booklist;

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

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;
import java.util.ResourceBundle;

public class BookList implements Initializable {
    ObservableList<Book> list= FXCollections.observableArrayList();
    @FXML
    private TableView<Book> vieww;
    @FXML
    private TableColumn<Book, String> idb;

    @FXML
    private TableColumn<Book, String> titleb;

    @FXML
    private TableColumn<Book, String> authorb;

    @FXML
    private TableColumn<Book, String> publisherb;

    @FXML
    private TableColumn<Book, Boolean> avilibility;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
initCol();
        loadData();
    }

    private void loadData() {
        DatabaseHandler handler=DatabaseHandler.getInstance();
        String qu="SELECT * FROM BOOK";
        ResultSet rs=handler.execquery(qu);
        try {
            while (rs.next()) {
                String idx = rs.getString("id");
                String titlex = rs.getString("title");
                String authorx = rs.getString("author");
                String publisherx = rs.getString("publisher");
                Boolean avail= rs.getBoolean("isAvail");
list.add(new Book(idx,titlex,authorx,publisherx,avail));
            }    } catch (SQLException e) {
            e.printStackTrace();
        }
        vieww.getItems().setAll(list);
    }

    private void initCol() {
        titleb.setCellValueFactory(new PropertyValueFactory<>("title"));
        idb.setCellValueFactory(new PropertyValueFactory<>("id"));
        authorb.setCellValueFactory(new PropertyValueFactory<>("author"));
        publisherb.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        avilibility.setCellValueFactory(new PropertyValueFactory<>("avaibility"));
    }

  public static   class Book{
        private final SimpleStringProperty id;
        private final SimpleStringProperty author;
        private final SimpleStringProperty title;
        private final SimpleStringProperty publisher;
private SimpleBooleanProperty avaibility;
Book(String id,String author,String title,String publisher,Boolean availibility){
    this.id=new SimpleStringProperty(id);
    this.author=new SimpleStringProperty(author);
    this.title=new SimpleStringProperty(title);
    this.publisher=new SimpleStringProperty(publisher);
    this.avaibility=new SimpleBooleanProperty(availibility);
}

        public String getId() {
            return id.get();
        }

        public SimpleStringProperty idProperty() {
            return id;
        }

        public String getAuthor() {
            return author.get();
        }

        public SimpleStringProperty authorProperty() {
            return author;
        }

        public String getTitle() {
            return title.get();
        }

        public SimpleStringProperty titleProperty() {
            return title;
        }

        public String getPublisher() {
            return publisher.get();
        }

        public SimpleStringProperty publisherProperty() {
            return publisher;
        }

        public boolean isAvaibility() {
            return avaibility.get();
        }

        public SimpleBooleanProperty avaibilityProperty() {
            return avaibility;
        }
    }
}
