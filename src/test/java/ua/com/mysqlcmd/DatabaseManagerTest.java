package ua.com.mysqlcmd;

import org.junit.Before;
import ua.com.mysqlcmd.view.manager.DatabaseManager;

public class DatabaseManagerTest {
    private DatabaseManager manager;

    @Before
    public void setup() {
        manager = new DatabaseManager();
        manager.connect("sqlcmd", "root", "root");
    }
}
