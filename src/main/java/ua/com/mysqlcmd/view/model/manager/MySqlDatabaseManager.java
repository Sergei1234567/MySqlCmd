package ua.com.mysqlcmd.view.model.manager;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;
import static java.lang.String.valueOf;
import static ua.com.mysqlcmd.view.util.ServerProperty.*;

public class MySqlDatabaseManager implements DatabaseManager {
    private Connection connection;
    private String databaseName;
    private String tableName;

    static {
        try {
            Class.forName(valueOf(DATABASE_DRIVER.getValue()));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Please add jdbc jar to project.", e);
        }
    }

    @Override
    public void connect(String databaseName, String userName, String password) {
        try {
            connection = DriverManager.getConnection(DATABASE_DATA_URL.getValue() +
                    databaseName + DATABASE_USE_SSL.getValue());
            this.databaseName = databaseName;
        } catch (SQLException e) {
            throw new RuntimeException(String.format("Could not get database connection\n:databaseName:%s user:%s password:%s,",
                    databaseName, userName, password), e);
        }
    }

    @Override
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
           throw new RuntimeException("failed to close connection.", e);
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
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("no data in the table", e);
        }
    }

    private int getSize(String tableName)throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rsCount = stmt.executeQuery("SELECT COUNT(*) FROM " + tableName);
        rsCount.next();
        int size = rsCount.getInt(1);
        rsCount.close();
        return size;
    }

}


