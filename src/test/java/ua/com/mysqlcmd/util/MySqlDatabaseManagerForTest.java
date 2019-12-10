package ua.com.mysqlcmd.util;

import java.sql.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class MySqlDatabaseManagerForTest {
    private Connection connection;
    private String databaseName;

    static {
        try {
            Class.forName(ServerProperty.DATABASE_DRIVER.getValue());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Please add jdbc jar to project.", e);
        }
    }

    public void connect(String databaseName, String userName, String password) {
        try {
            connection = DriverManager.getConnection(ServerProperty.DATABASE_DATA_URL.getValue() +
                    databaseName + ServerProperty.DATABASE_USE_SSL.getValue(), userName, password);
            this.databaseName = databaseName;
        } catch (SQLException e) {
            throw new RuntimeException(String.format("Could not get database connection\n:databaseName:%s user:%s password:%s,",
                    databaseName, userName, password), e);
        }
    }

    public Set<String> getDatabases() {
        String sql = "SELECT datname FROM pg_database WHERE datistemplate = false;";
        try (Statement ps = connection.createStatement();
             ResultSet rs = ps.executeQuery(sql)) {
            Set<String> result = new LinkedHashSet<>();
            while (rs.next()) {
                result.add(rs.getString(1));
            }
            return result;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void createDatabase(String databaseName) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("CREATE DATABASE " + databaseName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropDatabase(String dataBaseName) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DROP DATABASE " + dataBaseName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Set<String> getTableNames() {
        try (Statement stm = connection.createStatement()) {
            if (connection != null && !connection.isClosed()) {
                ResultSet rs = stm.executeQuery("SELECT table_name FROM information_schema.tables" +
                        " WHERE table_schema = '" + databaseName + "'" + ";");
                Set<String> tables = new HashSet<>();
                while (rs.next()) {
                    tables.add(rs.getString("table_name"));
                }
                return tables;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(String.format("failed to get tables:%s", databaseName), e);
        }
    }
}
