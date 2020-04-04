package ua.com.mysqlcmd.command;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentCaptor;
import ua.com.mysqlcmd.model.manager.DatabaseManager;
import ua.com.mysqlcmd.view.View;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.*;

public class UpdateTest {
    private DatabaseManager manager;
    private View<String> view;
    private Command command;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        manager = mock(DatabaseManager.class);
        view = mock(View.class);
        command = new Update(manager, view);
    }

    @Test
    public void shouldException_WhenInvalidCommandUpdate() {
        //Given
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("wrong format please check help for help");

        //When
        command.process("update|");

        //Then
    }

    @Test
    public void shouldCanProcessReturnFalse_WhenInvalidCommandUpdate() {
        //Given
        //When
        boolean canProcess = command.canProcess("qwe|");

        //Then
        assertFalse(canProcess);
    }

    @Test
    public void shouldCanProcessReturnTrue_WhenValidCommandUpdate() {
        //Given
        //When
        boolean canProcess = command.canProcess("update|");

        //Then
        assertTrue(canProcess);
    }

    @Test
    public void shouldProcessSuccess_WhenValidCommandUpdate() {
        //Given

        doNothing().when(manager).update(anyString(), anyInt(), anyMap());

        //When
        command.process("update|test|name|serg|id|2");

        //Then
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(view).write(captor.capture());
        verify(manager).update(eq("test"),eq(2),anyMap());
        String joinedResult = String.join("", captor.getAllValues());

        assertEquals("Success", joinedResult);
    }
}