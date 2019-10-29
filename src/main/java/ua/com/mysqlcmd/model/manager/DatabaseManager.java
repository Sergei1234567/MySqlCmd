package ua.com.mysqlcmd.model.manager;

import ua.com.mysqlcmd.model.Column;
import ua.com.mysqlcmd.model.Table;

import java.util.List;
import java.util.Set;

public interface DatabaseManager {

    void connect(String databaseName, String userName, String password);

    void clear(String tablename);

    void create(String tableName, List<Column> columns);

    Set<String> getTableNames();

    Table getTable(String tableName);

    boolean isConnected();

}
