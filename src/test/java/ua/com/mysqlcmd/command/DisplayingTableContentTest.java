package ua.com.mysqlcmd.command;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import ua.com.mysqlcmd.model.Column;
import ua.com.mysqlcmd.model.Table;
import ua.com.mysqlcmd.model.manager.DatabaseManager;
import ua.com.mysqlcmd.view.FormatConsole;
import ua.com.mysqlcmd.view.View;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.atLeastOnce;

public class DisplayingTableContentTest {

    private DatabaseManager manager;
    private View view;
    private FormatConsole formatConsole;
    Command command;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Captor
    private ArgumentCaptor<String> captor;

    @Before
    public void setUp() {
        manager = mock(DatabaseManager.class);
        view = mock(View.class);
        command = new DisplayingTableContent(manager, view, formatConsole);
    }

    @Test
    public void canProcessShouldReturnTrue_WhenValidFindCommand() {
        //Given-When
        boolean canProcess = command.canProcess("find|");

        //Then
        assertTrue(canProcess);
    }

    @Test
    public void canProcessShouldReturnFalse_WhenInvalidFindCommand() {
        //Given-When
        boolean canProcess = command.canProcess("qwe|");

        //Then
        assertFalse(canProcess);
    }

    @Test
    public void shouldException_WhenInvalidFindCommand() {
        //Given
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("wrong format please check help for help");

        // When
        command.process("find|");

        //Then
    }

    @Test
    public void processShouldSuccess_WhenValidCommandFind() {
        //Given
        List<Column> column = new ArrayList<>();
        Column column1 = new Column("id", "INTEGER");
        Column column2 = new Column("name", "VARCHAR(25)");

        column.add(column1);
        column.add(column2);

        List<Table.Data> dataList = new ArrayList<>();
        Table.Data data = new Table.Data("id", "1");
        Table.Data data1 = new Table.Data("name", "Jack");

        dataList.add(data);
        dataList.add(data1);

        List<List<Table.Data>> rows = new ArrayList<>();
        rows.add(dataList);

        when(manager.getTable("user"))
                .thenReturn(new Table("user", column, rows));

        //When
        command.process("find|user");

        //Then
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(view, atLeastOnce()).write(captor.capture());
        String joinedResult = String.join("", captor.getAllValues());
        assertEquals("id                       name                     \n" +
                "1                        Jack                     \n", joinedResult);
    }
}