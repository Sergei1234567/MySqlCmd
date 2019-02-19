package ua.com.mysqlcmd;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ua.com.mysqlcmd.view.manager.MySqlDatabaseManager;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;


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
        exception.expectMessage("Could not get database connection\n:databaseName:sqlcmd user:root password:roo,");
        manager.connect("sqlcmd", "root", "roo");
    }

    @Test
    public void testGetTableName() {
        manager.connect("sqlcmd", "root", "root");
        Set<String> tables = manager.getTableNames();
        assertTrue(tables.contains("user"));
        assertTrue(tables.contains("test"));
    }

    @Test
    public void testGetTableName2() {
        manager.connect("sqlcmd", "root", "root");
        Set<String> tables = manager.getTableNames();
        if (tables != null) {
            assertTrue(tables.contains("test"));
            assertTrue(tables.contains("user"));
        }else {
            assertTrue(tables.contains(""));
        }
    }
}
