package ua.com.mysqlcmd.view.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class MySqlDatabaseManager implements DatabaseManager {

    private Connection connection;
    private String databaseName;

    static {
        try (FileInputStream stream = new FileInputStream("src/main/resources/application.properties")) {
            Properties properties = new Properties();
            properties.load(stream);
            Class.forName(properties.getProperty("database.driver"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Please add jdbc jar to project.", e);
        } catch (IOException e) {
            throw new RuntimeException("wrong file path application.properties.", e);
        }
    }

    @Override
    public void connect(String databaseName, String userName, String password) {
        try (FileInputStream stream = new FileInputStream("src/main/resources/application.properties")) {
            Properties properties = new Properties();
            properties.load(stream);
            connection = DriverManager.getConnection(properties.getProperty("database.dataURL") +
                    databaseName + properties.getProperty("database.useSSL"), userName, password);
            this.databaseName = databaseName;
        } catch (SQLException e) {
            throw new RuntimeException(String.format("Could not get database connection\n:databaseName:%s user:%s password:%s,",
                    databaseName, userName, password), e);
        } catch (IOException e) {
            throw new RuntimeException("wrong file path application.properties.", e);
        }
    }

    @Override
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Set<String> getTableNames() {
        try (Statement stm = connection.createStatement()) {
            ResultSet rs = stm.executeQuery("SELECT table_name FROM information_schema.tables" +
                    " WHERE table_schema = '" + databaseName + "'");
            Set<String> tables = new HashSet<>();
            while (rs.next()) {
                tables.add(rs.getString("table_name"));
            }
            return tables;
        } catch (SQLException e) {
            throw new RuntimeException(String.format("failed to get tables:tables:%s,", databaseName), e);
        }
    }

}


