package ua.com.mysqlcmd.model.manager;

import ua.com.mysqlcmd.model.Table;

import java.sql.SQLException;
import java.util.Set;

public interface DatabaseManager {

    void connect(String databaseName, String userName, String password);

    void closeConnection();

    Set<String> getTableNames();

    Table getTable(String tableName);


}
