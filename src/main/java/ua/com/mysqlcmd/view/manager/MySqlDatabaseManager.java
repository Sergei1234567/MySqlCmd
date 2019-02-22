package ua.com.mysqlcmd.view.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class MySqlDatabaseManager implements DatabaseManager {

    private Connection connection;
    private String databaseName;
    private String tableName;

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
            new RuntimeException("failed to close connection.", e);
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

    @Override
    public DataSet[] getTableData() {
        try (Statement stmt = connection.createStatement()){
            this.tableName = "user";
            int size = getSize(tableName);
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
            ResultSetMetaData rsmd = rs.getMetaData();
            DataSet[] result = new DataSet[size];
            int index = 0;
            while (rs.next()) {
                DataSet dataSet = new DataSet();
                result[index++] = dataSet;
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    dataSet.put(rsmd.getColumnName(i), rs.getObject(i));
                }
            }
            System.out.println(Arrays.toString(result));
            rs.close();
            stmt.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return new DataSet[0];
        }
    }

    public int getSize(String tableName)throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rsCount = stmt.executeQuery("SELECT COUNT(*) FROM " + tableName);
        rsCount.next();
        int size = rsCount.getInt(1);
        rsCount.close();
        return size;
    }

}


