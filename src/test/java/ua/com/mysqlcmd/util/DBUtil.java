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
            throw new RuntimeException(String.format("Could not get database connection\n:databaseName:%s user:%s password:%s,",
                    userName, password), e);
        }
    }

    public int count(String tableName) {
        String sql = "SELECT COUNT(*) FROM" + tableName;
        try (Statement ps = connection.createStatement();
             ResultSet rs = ps.executeQuery(sql)) {
            int count = 0;
            while (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
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
}
