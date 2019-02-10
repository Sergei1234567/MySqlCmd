package ua.com.mysqlcmd.view.manager;

import java.sql.SQLException;
import java.util.Set;

public interface DatabaseManager {

    void connect(String databaseName, String userName, String password) throws RuntimeException;

    void closeConnection() throws SQLException;

    Set<String> getTableNames() throws SQLException;

}
