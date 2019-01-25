package ua.com.mysqlcmd;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ua.com.mysqlcmd.view.manager.MySqlDatabaseManager;

public class MySqlDatabaseManagerTest {
    private MySqlDatabaseManager manager;

    @Before
    public void setup() {
        manager = new MySqlDatabaseManager();
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testConnect() {
        manager.connect("sqlcmd", "root", "root");
        exception.expectMessage("Cant get connection for database:sqlcmd user:root password:root,");
        throw new RuntimeException("Cant get connection for database:sqlcmd user:root password:root,");
    }
}
