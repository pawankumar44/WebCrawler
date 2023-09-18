package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//This will create connection instance, that will be used for writing queries from java
//Create this as singleton class
public class DatabaseConnection {
    //Connection from java sql
    static Connection connection = null;
    //to set up connection
    public Connection getConnection(){
        if(connection!=null){
            return  connection;
        }
        String user = "root";
        String pwd = "root";
        String db = "searchengineapp";
        return getConnection(user,pwd,db);
    }

    //overloading
    private Connection getConnection(String user, String pwd, String db){
        //we need mysql connector j library
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + db + "?user=" + user + "&password?=" + pwd);
        }
        catch (SQLException | ClassNotFoundException sqlException){
            sqlException.printStackTrace();
        }
        return connection;
    }
}
