package ua.com.mysqlcmd.view.model.manager;

import java.util.Set;

public interface DatabaseManager {

    void connect(String databaseName, String userName, String password);

    void closeConnection();

    Set<String> getTableNames();

    DataSet[] getTableData();
}
