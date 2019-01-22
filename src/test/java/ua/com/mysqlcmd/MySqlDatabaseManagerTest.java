package ua.com.mysqlcmd;

import org.junit.Before;
import org.junit.Test;
import ua.com.mysqlcmd.view.manager.MySqlDatabaseManager;


import static junit.framework.TestCase.assertEquals;

public class MySqlDatabaseManagerTest {
    private MySqlDatabaseManager manager;

    @Before
    public void setup() {
        manager = new MySqlDatabaseManager();
    }

    @Test
    public void testConnect() throws RuntimeException {
        try {
        manager.connect("sqlcmd", "roo", "root");
        }catch (RuntimeException e){
            assertEquals("Cant get connection for database:%s user:%s password:%s,", e.getMessage());
        }

    }
}
