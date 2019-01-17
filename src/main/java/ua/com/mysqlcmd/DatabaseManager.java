package ua.com.mysqlcmd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private Connection connection;

    public void connect(String databaseName, String userName, String password) throws RuntimeException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Please add jdbc jar to project.", e);
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName, userName, password);
        } catch (SQLException e) {
            connection = null;
            throw new RuntimeException(String.format("Cant get connection for database:%s user:%s,",
                    databaseName, userName),
                    e);
        }
    }

    public void closeConnection() throws SQLException {
         connection.close();
    }

}


