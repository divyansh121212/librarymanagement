package assistance.database;


import javax.swing.*;
import java.sql.*;


public class DatabaseHandler {
    private static DatabaseHandler handler=null;
    private static final String DB_URL=  "jdbc:derby:database;create=true";
    private static Connection conn=null;
    private static Statement stat=null;
    private DatabaseHandler(){
        createconnection();
        setupnewtable();
        setupmember();
        issuebook();
    }
public static DatabaseHandler getInstance(){
        if(handler==null)
        {
            handler=new DatabaseHandler();
        }
        return handler;
}
    private void setupmember() {
        String Table_Name="MEMBER";
        try {
            stat=conn.createStatement();
            DatabaseMetaData dbm=conn.getMetaData();
            ResultSet tables=dbm.getTables(null,null,Table_Name.toUpperCase(),null);
            if(tables.next()){
                System.out.println("Table "+Table_Name+" already exists .Ready for go!");
            }else {
                stat.execute("CREATE TABLE "+Table_Name+"("+
                        "  mid varchar(200) primary key,\n"+
                        "  name varchar(200),\n"+
                        "  mobile varchar(200),\n"+
                        "  email varchar(100)"+
                        " )");

            }
        }catch (SQLException e){
            System.err.println(e.getMessage()+" ...........setUpDatabase");
        }
    }

    private void createconnection(){
    try{
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
        conn= DriverManager.getConnection(DB_URL);
    } catch (Exception e) {
        e.printStackTrace();
    }
}
private void issuebook(){
    String Table_Name="ISSUE";
    try {
        stat=conn.createStatement();
        DatabaseMetaData dbm=conn.getMetaData();
        ResultSet tables=dbm.getTables(null,null,Table_Name.toUpperCase(),null);
        if(tables.next()){
            System.out.println("Table "+Table_Name+" already exists .Ready for go!");
        }else {
            stat.execute("CREATE TABLE "+Table_Name+"("+
                    "  bookid varchar(200) primary key,\n"+
                    " memberid varchar(200),\n"+
            "issuetime timestamp default CURRENT_TIMESTAMP,\n"+
                    "renew_count integer default 0,\n"+
                    "FOREIGN KEY (bookid) REFERENCES BOOK(id),\n"+
                    "FOREIGN KEY (memberid) REFERENCES MEMBER(mid)"+
            " )");

        }
    }catch (SQLException e){
        System.err.println(e.getMessage()+" ...........setUpDatabase");
    }
}
void setupnewtable(){
    String Table_Name="BOOK";
    try {
        stat=conn.createStatement();
        DatabaseMetaData dbm=conn.getMetaData();
        ResultSet tables=dbm.getTables(null,null,Table_Name.toUpperCase(),null);
        if(tables.next()){
            System.out.println("Table "+Table_Name+" already exists .Ready for go!");
        }else {
            stat.execute("CREATE TABLE "+Table_Name+"("+
                    "  id varchar(200) primary key,\n"+
                    "  title varchar(200),\n"+
                    "  author varchar(200),\n"+
                    "  publisher varchar(100),\n"+
                    "  isAvail boolean default true"+
                    " )");

        }
    }catch (SQLException e){
        System.err.println(e.getMessage()+" ...........setUpDatabase");
    }
}
public ResultSet execquery(String query){
    ResultSet resultSet;
    try {
        stat=conn.createStatement();
        resultSet=stat.executeQuery(query);
    }catch (Exception e){
        System.out.println(e.getLocalizedMessage());
        return  null;
    }
    return resultSet;
}
public boolean execAct(String qu){
    try {
        stat=conn.createStatement();
        stat.execute(qu);
    return true;
    }catch (Exception e){
        System.out.println(e.getLocalizedMessage());
        return false;
    }
}
}
