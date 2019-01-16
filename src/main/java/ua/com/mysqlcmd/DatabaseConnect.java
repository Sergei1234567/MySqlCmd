package ua.com.mysqlcmd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnect {
    private Connection connection;

    public void connection(String databaseName, String userName, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Please add jdbc jar to project.");
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName, userName, password);
        } catch (SQLException e) {
            System.out.println(String.format("Cant get connection for database:%s user:%s,", databaseName, userName));
            e.printStackTrace();
            connection = null;
        }
    }

    public Connection getConnection() {
        return connection;
    }
}


