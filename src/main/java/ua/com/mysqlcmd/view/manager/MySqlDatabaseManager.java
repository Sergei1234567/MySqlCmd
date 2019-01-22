package ua.com.mysqlcmd.view.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDatabaseManager implements DatabaseManager {

    private Connection connection;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Please add jdbc jar to project.", e);
        }
    }

    public void connect(String databaseName, String userName, String password) throws RuntimeException {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName, userName, password);
        } catch (SQLException e) {
            throw new RuntimeException(String.format("Cant get connection for database:%s user:%s password:%s,",
                    databaseName, userName, password), e);
        }
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

}


