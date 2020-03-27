package ua.com.mysqlcmd.util;

import java.sql.*;

public class DBUtil {
    private Connection connection;

    static {
        try {
            Class.forName(ServerProperty.DATABASE_DRIVER.getValue());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Please add jdbc jar to project.", e);
        }
    }

    public void connect(String userName, String password) {
        try {
            connection = DriverManager.getConnection(ServerProperty.DATABASE_DATA_URL.getValue() +
                    ServerProperty.DATABASE_USE_SSL.getValue(), userName, password);
        } catch (SQLException e) {
            throw new RuntimeException(String.format("Could not get connection to MySql server\nuser:%s password:%s,",
                    userName, password), e);
        }
    }

    public int countRows(String tableName) {
        String sql = "SELECT COUNT(*) AS rowcount FROM " + tableName;
        try (Statement ps = connection.createStatement();
             ResultSet rs = ps.executeQuery(sql)) {
            rs.next();
            int count = rs.getInt("rowcount");
            return count;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void createDatabase(String databaseName) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("CREATE DATABASE " + databaseName);
            stmt.execute("USE " + databaseName);
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
}
