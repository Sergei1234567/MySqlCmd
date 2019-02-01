package ua.com.mysqlcmd.view.manager;

import java.sql.*;

public class MySqlDatabaseManager implements DatabaseManager {

    private Connection connection;
    private Object userName = null;
    private Object password = null;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Please add jdbc jar to project.", e);
        }
    }


    public void connect(String databaseName, String userName, String password) throws RuntimeException {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName + "?useSSL=false", userName, password);
        } catch (SQLException e) {
            throw new RuntimeException(String.format("Не могу получить соединение для базы данных\n:%s user:%s password:%s,",
                    databaseName, userName, password), e);
        }
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

    @Override
    public void insert() throws RuntimeException {
        try {
            Statement stm = connection.createStatement();
            stm.executeUpdate("INSERT  INTO user(userName, password) VALUES ('Stiven', 'Pupkin')");
            stm.close();
        } catch (SQLException e) {
            throw new RuntimeException(String.format("не могу вставить имя пользователя и пароль\n: userName:%s password:%s,",
                    userName, password), e);
        }
    }
}


