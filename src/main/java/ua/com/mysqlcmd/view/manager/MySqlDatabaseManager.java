package ua.com.mysqlcmd.view.manager;

import java.sql.*;
import java.util.Arrays;

public class MySqlDatabaseManager implements DatabaseManager {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Please add jdbc jar to project.", e);
        }
    }

    private Connection connection;

    @Override
    public void connect(String databaseName, String userName, String password) throws RuntimeException {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName + "?useSSL=false", userName, password);
        } catch (SQLException e) {
            throw new RuntimeException(String.format("Could not get database connection\n:databaseName:%s user:%s password:%s,",
                    databaseName, userName, password), e);
        }
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

    @Override
    public String[] getTableNames() {
        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema='sqlcmd'");
            String[] tables = new String[100];
            int index = 0;
            while (rs.next()) {
                tables[index++] = rs.getString("table_name");
            }
            tables = Arrays.copyOf(tables, index, String[].class);
            rs.close();
            stm.close();
            return tables;

        } catch (SQLException e) {
            e.printStackTrace();
            return new String[0];
        }
    }

}


