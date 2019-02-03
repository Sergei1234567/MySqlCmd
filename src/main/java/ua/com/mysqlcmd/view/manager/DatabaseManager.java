package ua.com.mysqlcmd.view.manager;

import java.sql.SQLException;

public interface DatabaseManager {

    void connect(String databaseName, String userName, String password) throws RuntimeException;

    void closeConnection() throws SQLException;

    String[] getTableNames() throws SQLException;

}
