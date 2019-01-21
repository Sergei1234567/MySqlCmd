package ua.com.mysqlcmd;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.com.mysqlcmd.view.manager.MySqlDatabaseManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDatabaseManagerTest {
    private MySqlDatabaseManager manager;

    @Before
    public void setup() {
        manager = new MySqlDatabaseManager();
    }

    @Test
    public void testConnect() throws RuntimeException, IOException {
        manager.connect("sqlcmd", "root", "root");
        Path tmpDir = Files.createTempDirectory("tmp");
        tmpDir.toFile().delete();
        try {
            Files.createTempFile(tmpDir, "test", ".txt");
            Assert.fail("Expected RuntimeException");
        } catch (IOException thrown) {
            Assert.assertNotEquals("", thrown.getMessage());
        }
    }
}
