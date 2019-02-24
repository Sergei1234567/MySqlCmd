package ua.com.mysqlcmd;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ua.com.mysqlcmd.view.manager.MySqlDatabaseManager;

import java.util.Set;

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
    public void connect_ShouldThrowsRuntimeException_WhenInvalidPassword() {
        //Given - Then
        exception.expect(RuntimeException.class);
        exception.expectMessage("Could not get database connection\n:databaseName:sqlcmd user:root password:roo,");
        //When
        manager.connect("sqlcmd", "root", "roo");
    }

    @Test
    public void shouldContainTableNames_WhenConnectionSuccessful() {
        //Given
        manager.connect("sqlcmd", "root", "root");
        //When
        Set<String> tables = manager.getTableNames();
        //Then
        assertTrue(tables.contains("user"));
        assertTrue(tables.contains("test"));
    }

    @Test
    public void shouldContainTableNames_WhenTablesNotEqualToZero() {
        //Given
        manager.connect("sqlcmd", "root", "root");
        //When
        Set<String> tables = manager.getTableNames();
        //Then
        if (tables != null) {
            assertTrue(tables.contains("test"));
            assertTrue(tables.contains("user"));
        } else {
            assertTrue(tables.contains(""));
        }
    }
}
