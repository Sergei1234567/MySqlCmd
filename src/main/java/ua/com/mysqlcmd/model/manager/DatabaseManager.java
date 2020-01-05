package ua.com.mysqlcmd.model.manager;

import ua.com.mysqlcmd.model.Column;
import ua.com.mysqlcmd.model.Table;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface DatabaseManager {

    void connect(String databaseName, String userName, String password);

    void clear(String tablename);

    void create(String tableName, List<Column> columns);

    void insert(String tableName, Map<Column, String> data);

    void update(String tableName, int id, Map<String, Object> dataInsert);

    void dropTable(String tableName);

    Set<String> getTableNames();

    Table getTable(String tableName);

    boolean isConnected();
}
