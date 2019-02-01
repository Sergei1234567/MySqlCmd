package ua.com.mysqlcmd;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ua.com.mysqlcmd.view.manager.MySqlDatabaseManager;

public class MySqlDatabaseManagerTest {
    private MySqlDatabaseManager manager;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() {
        manager = new MySqlDatabaseManager();
    }


    @Test
    public void testConnect() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Не могу получить соединение для базы данных\n:sqlcmd user:root password:roo,");
        manager.connect("sqlcmd", "root", "roo");
    }
}
