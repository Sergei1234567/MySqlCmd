package ua.com.mysqlcmd.command;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentCaptor;
import ua.com.mysqlcmd.model.Column;
import ua.com.mysqlcmd.model.manager.DatabaseManager;
import ua.com.mysqlcmd.view.View;

import java.util.LinkedHashMap;
import java.util.Map;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class InsertTest {
    DatabaseManager manager;
    View view;
    Command command;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        manager = mock(DatabaseManager.class);
        view = mock(View.class);
        command = new Insert(manager, view);
    }

    @Test
    public void shouldException_WhenInvalidCommandInsert() {
        //Given
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("wrong format please check help for help ");

        //When
        command.process("insert|");

        //Then
    }

    @Test
    public void shouldCanProcessReturnFalse_WhenInvalidCommandInsert() {
        //Given
        //When
        boolean canProcess = command.canProcess("qwe|");

        //Then
        assertFalse(canProcess);
    }

    @Test
    public void shouldCanProcessReturnTrue_WhenValidCommandInsert() {
        //Given
        //When
        boolean canProcess = command.canProcess("insert|");

        //Then
        assertTrue(canProcess);
    }

    @Test
    public void shouldProcessSuccess_WhenValidCommandInsert() {
        //Given
        Column id = new Column("id", "INTEGER");
        Column name = new Column("name", "VARCHAR(25)");

        Map<Column, String> data = new LinkedHashMap<>();
        data.put(id, "2");
        data.put(name, "tom");

        //When
        command.process("insert|test|id|2|name|tom");

        //Then
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(view).write(captor.capture());
        String joinedResult = String.join("", captor.getAllValues());

        assertEquals("Success", joinedResult);
    }
}