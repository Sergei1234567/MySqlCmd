package ua.com.mysqlcmd.model.manager;

import ua.com.mysqlcmd.model.Column;
import ua.com.mysqlcmd.model.Table.Data;
import ua.com.mysqlcmd.util.ServerProperty;
import ua.com.mysqlcmd.model.Table;

import java.sql.*;
import java.util.*;

public class MySqlDatabaseManager implements DatabaseManager {
    private Connection connection;
    private String databaseName;

    static {
        try {
            Class.forName(ServerProperty.DATABASE_DRIVER.getValue());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Please add jdbc jar to project.", e);
        }
    }

    @Override
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
            throw new RuntimeException(String.format("failed to get tables:%s", databaseName), e);
        }
    }

    @Override
    public Table getTable(String tableName) {
        try (Statement stm = connection.createStatement()) {
            ResultSet resultSet = stm.executeQuery("SELECT * FROM " + tableName);
            ResultSetMetaData metaData = resultSet.getMetaData();

            int columnCount = metaData.getColumnCount();
            List<Column> columns = new ArrayList<>(columnCount);
            for (int i = 1; i <= columnCount; i++) {
                columns.add(new Column(metaData.getColumnName(i)));
            }

            List<List<Data>> rows = new ArrayList<>();
            while (resultSet.next()) {
                List<Data> row = new ArrayList<>();
                for (Column column : columns) {
                    String columnName = column.getName();
                    Object value = resultSet.getObject(columnName);
                    row.add(new Data(columnName, value));
                }
                rows.add(row);
            }
            return new Table(tableName, columns, rows);
        } catch (SQLException e) {
            throw new RuntimeException("no data in the table", e);
        }
    }

}


