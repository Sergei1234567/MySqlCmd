package ua.com.mysqlcmd;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ua.com.mysqlcmd.view.manager.MySqlDatabaseManager;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;


public class MySqlDatabaseManagerTest {
    private MySqlDatabaseManager manager;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() {
        manager = new MySqlDatabaseManager();
        manager.connect("sqlcmd", "root", "root");
    }


    @Test
    public void testConnect() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Could not get database connection\n:databaseName:sqlcmd user:root password:roo,");
        manager.connect("sqlcmd", "root", "roo");
    }

    @Test
    public void testGetTableName() {
        String[] tables = manager.getTableNames();
        assertEquals("[test, user]", Arrays.toString(tables));
    }
}
