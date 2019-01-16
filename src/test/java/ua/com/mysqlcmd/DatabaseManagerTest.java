package ua.com.mysqlcmd;

import org.junit.Before;

public class DatabaseManagerTest {
    private DatabaseManager manager;

    @Before
    public void setup() {
        manager = new DatabaseManager();
        manager.connect("sqlcmd", "root", "root");
    }
}
