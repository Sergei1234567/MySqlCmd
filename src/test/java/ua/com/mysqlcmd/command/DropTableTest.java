package ua.com.mysqlcmd.command;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ua.com.mysqlcmd.model.manager.DatabaseManager;
import ua.com.mysqlcmd.view.View;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.*;

public class DropTableTest {
    DatabaseManager manager;
    View<String> view;
    Command command;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void SetUp() {
        manager = mock(DatabaseManager.class);
        view = mock(View.class);
        command = new DropTable(manager, view);
    }

    @Test
    public void shouldException_WhenInvalidDropTableProcessCommand() {
        //Given
        //When
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("wrong format please check help for help");

        //Then
        command.process("dropTable|");
    }

    @Test
    public void shouldReturnFalse_WhenInvalidDropTableCanProcessCommand() {
        //Given
        //When
        boolean canProcess = command.canProcess("qwe|");

        //Then
        assertFalse(canProcess);
    }

    @Test
    public void shouldReturnTrue_WhenValidDropTableCanProcessCommand() {
        //Given
        //When
        boolean canProcess = command.canProcess("dropTable|");

        //Then
        assertTrue(canProcess);
    }

    @Test
    public void shouldTableNameDropped_WhenValidDropTableProcessCommand_Y() {
        //Given
        when(view.read()).thenReturn("y");

        //When
        command.process("dropTable|dog");

        //Then
        verify(manager).dropTable("dog");
        verify(view).write("TableName " + "dog" + " dropped");
    }

    @Test
    public void testProcessWithoutConfirmedDropping() {
        //Given
        when(view.read()).thenReturn("n");

        //When
        command.process("dropTable|dog");
    }
}