package ua.com.mysqlcmd.view.manager;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class MySqlDatabaseManager implements DatabaseManager {

    private Connection connection;
    private String databaseName;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Please add jdbc jar to project.", e);
        }
    }

    @Override
    public void connect(String databaseName, String userName, String password) {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName + "?useSSL=false", userName, password);
            this.databaseName = databaseName;
        } catch (SQLException e) {
            throw new RuntimeException(String.format("Could not get database connection\n:databaseName:%s user:%s password:%s,",
                    databaseName, userName, password), e);
        }
    }

    @Override
    public void closeConnection() throws SQLException {
            connection.close();

    }

    @Override
    public Set<String> getTableNames() {
        Set<String> tables = null;
        try (Statement stm = connection.createStatement()) {
            ResultSet rs = stm.executeQuery("SELECT table_name FROM information_schema.tables" +
                    " WHERE table_schema = '" + databaseName + "'");
            tables = new HashSet<>();
            while (rs.next()) {
                tables.add(rs.getString("table_name"));
            }
            return tables;
        } catch (SQLException e) {
            throw new RuntimeException(String.format("failed to get tables:tables:%s,", tables),e);
        }
    }
}


