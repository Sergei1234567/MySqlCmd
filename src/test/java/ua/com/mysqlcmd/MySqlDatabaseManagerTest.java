package ua.com.mysqlcmd;

import org.junit.Before;
import org.junit.Test;
import ua.com.mysqlcmd.view.manager.MySqlDatabaseManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static junit.framework.TestCase.assertEquals;

public class MySqlDatabaseManagerTest {
    private MySqlDatabaseManager manager;

    @Before
    public void setup() {
        manager = new MySqlDatabaseManager();
        manager.connect("sqlcmd", "root", "root");
    }

    @Test
    public void testConnect() throws RuntimeException{
        Connection connection;

        String databaseName = "sqlcmd";
        String userName = "root";
        String password = "root";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Please add jdbc jar to project.", e);
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName, userName, password);
        } catch (SQLException e) {
            throw new RuntimeException(String.format("Cant get connection for database:%s user:%s,",
                    databaseName, userName),
                    e);
        }

    }
}
