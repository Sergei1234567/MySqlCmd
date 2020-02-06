package ua.com.mysqlcmd.command;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ua.com.mysqlcmd.model.manager.DatabaseManager;
import ua.com.mysqlcmd.view.View;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ConnectTest {
    private static String COMMAND_SAMPLE = "connect|sqlcmd|root|root";
    DatabaseManager manager;
    View view;
    Command command;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        manager = mock(DatabaseManager.class);
        view = mock(View.class);
        command = new Connect(manager, view);
    }

    @Test
    public void shouldWriteErrorMessage_WhenInvalidConnectCommand() {
        //Given -When
        command.process("connect|");

        // Then
        verify(view).write("Failure due:the wrong number of parameters, wait 4, but there are: 1\nTry again");
    }

    @Test
   public void canProcessShouldReturnTrue_WhenValidConnectCommand(){
        //Given-When
        boolean canProcess = command.canProcess("connect|");

        //then
        assertTrue(canProcess);
    }

    @Test
   public void canProcessShouldReturnFalse_WhenInvalidConnectCommand(){
        //Given-When
        boolean canProcess = command.canProcess("qwe|");

        //then
        assertFalse(canProcess);
    }

    @Test
    public void shouldConnect_ValidCommand() {
        //Given

        //when
        command.process("connect|sqlcmd|root|root");

        //Then
        verify(manager).connect("sqlcmd", "root", "root");
        verify(view).write("Success");
    }
}