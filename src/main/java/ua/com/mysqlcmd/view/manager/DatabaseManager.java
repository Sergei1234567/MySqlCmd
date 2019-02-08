package ua.com.mysqlcmd.view.manager;

import java.sql.SQLException;
import java.util.HashSet;

public interface DatabaseManager {

    void connect(String databaseName, String userName, String password) throws RuntimeException;

    void closeConnection() throws SQLException;

    HashSet<String> getTableNames() throws SQLException;

}
