package ua.com.mysqlcmd.model;

import org.junit.*;
import org.junit.rules.ExpectedException;
import ua.com.mysqlcmd.model.manager.MySqlDatabaseManager;
import ua.com.mysqlcmd.util.DBUtil;

import java.util.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;


public class MySqlDatabaseManagerTest {

    private static final MySqlDatabaseManager mySqlManager = new MySqlDatabaseManager();

    private static final DBUtil dbUtil = new DBUtil();

    private static final String TEST_DATABASE = "TEST_DATABASE";

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @BeforeClass
    public static void beforeClass() {
        String userName = "root";
        String password = "root";
        dbUtil.connect(userName, password);
        dbUtil.createDatabase(TEST_DATABASE);
        mySqlManager.connect(TEST_DATABASE, userName, password);
    }

    @AfterClass
    public static void cleanUp() {
        dbUtil.dropDatabase(TEST_DATABASE);
    }

    @Test
    public void tableShouldBeEmpty_WhenClear() {
        //Given
        String tableName = "Dog";
        Column id = new Column("ID", "INTEGER");
        Column name = new Column("NAME", "VARCHAR(20)");
        mySqlManager.create(tableName, Arrays.asList(id, name));

        Map<Column, String> dataToInsert = Map.of(id, "1", name, "Jack");
        mySqlManager.insert(tableName, dataToInsert);

        //When
        mySqlManager.clear(tableName);

        //Then
        assertEquals(1, dbUtil.count(tableName));
        mySqlManager.dropTable(tableName);
    }

    @Test
    public void connect_ShouldThrowsRuntimeException_WhenInvalidPassword() {
        //Given - Then
        exception.expect(RuntimeException.class);
        exception.expectMessage("Could not get database connection\n:databaseName:TEST_DATABASE user:root password:roo,");
        //When
        mySqlManager.connect(TEST_DATABASE,"root", "roo");
    }

    @Test
    public void shouldContainsTableNames_WhenConnectionSuccessful() {
        //Given
        List<Column> userColumns = List.of(new Column("ID", "INTEGER"),
        new Column("AGE", "INTEGER"));

        List<Column> testColumns = List.of(new Column("ID", "INTEGER"));

        mySqlManager.create("user", userColumns);
        mySqlManager.create("test", testColumns);

        //When
        Set<String> tables = mySqlManager.getTableNames();
        //Then
        assertTrue(tables.contains("user"));
        assertTrue(tables.contains("test"));

        mySqlManager.dropTable("user");
        mySqlManager.dropTable("test");
    }

    @Test
    public void shouldContainTableNames_WhenTablesNotEqualToZero() {
        //Given

        //When
        Set<String> tables = mySqlManager.getTableNames();
        //Then
        assertEquals(0, tables.size());
    }
}
