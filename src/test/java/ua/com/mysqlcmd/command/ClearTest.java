package ua.com.mysqlcmd.command;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ua.com.mysqlcmd.model.manager.DatabaseManager;
import ua.com.mysqlcmd.view.View;

import static junit.framework.TestCase.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ClearTest {
    private Command command;
    private DatabaseManager manager;
    private View view;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        manager = mock(DatabaseManager.class);
        view = mock(View.class);
        command = new Clear(manager, view);
    }

    @Test
    public void shouldValid_CommandClear() {
        //Given
        String tableName = "dog";

        // When
        command.process("clear|dog");

        //Then
        verify(view).write("The table " + tableName + " has been cleared successfully.");

    }

    @Test
    public void shouldInValid_CommandClear_throwsIllegalArgumentException() {
        //Given
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Command format 'clear|tableName',and you entered: ");

        //When
        command.process("clear|");

        //Then
    }

    @Test
    public void canProcessShouldReturnFalse_WhenInvalidClearComand() {
        //Given-When
        boolean canProcess = command.canProcess("qwe|");

        //Then
        assertFalse(canProcess);
    }

    @Test
    public void shouldProcessClear_WithConfirmedClearing() {
        //Given
        String tableName = "dog";

        //When
        command.process("clear|dog");

        //Then
        verify(manager).clear(tableName);
        verify(view).write("The table " + tableName + " has been cleared successfully.");
    }
}