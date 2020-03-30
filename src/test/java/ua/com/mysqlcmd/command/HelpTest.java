package ua.com.mysqlcmd.command;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ua.com.mysqlcmd.view.View;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HelpTest {
    View view;
    Command command;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        view = mock(View.class);
        command = new Help(view);
    }

    @Test
    public void shouldException_WhenInvalidCommandHelp() {
        //Given
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Command format 'help',and you entered: ");

        //When
        command.process("hel");

        //Then
    }

    @Test
    public void canProcessReturnFalse_WhenInvalidCommandHelp() {
        //Given
        //When
        boolean canProcess = command.canProcess("qwe");

        //Then
        assertFalse(canProcess);
    }

    @Test
    public void canProcessReturnTrue_WhenValidCommandHelp() {
        //Given
        //When
        boolean canProcess = command.canProcess("help");

        //Then
        assertTrue(canProcess);
    }

    @Test
    public void processShouldSuccess_WhenValidCommandHelp() {
        //Given
        //When
        command.process("help");

        //Then
        verify(view).write("Existing teams:");

        verify(view).write("\tconnect|databaseName|userName|password");
        verify(view).write("\t\tto connect to the database with which we will work");

        verify(view).write("\ttables");
        verify(view).write("\t\tto get a list of all the tables of the database to which you are connected");

        verify(view).write("\tfind|");
        verify(view).write("\t\tto get the contents of the table'tableName'");

        verify(view).write("\tclear|tableName|");
        verify(view).write("\t\tto clear the whole table");

        verify(view).write("\tcreate|tableName|column1:value1|column2:value2|...|columnN:valueN|");
        verify(view).write("\t\tto create table in database, enter column description in SQL format\n" +
                "example: user|id:INTEGER|username:varchar(225) NOT NULL UNIQUE|" +
                "password:varchar(225)");

        verify(view).write("\tupdate|tableName|column1|value1|column2|value2|...|columnN|valueN|");
        verify(view).write("\t\tto update table data");

        verify(view).write("\tinsert|tableName|column1|value1|column2|value2|...|columnN|valueN|");
        verify(view).write("\t\tto create a table entry");

        verify(view).write("\thelp");
        verify(view).write("\t\tto display this list");

        verify(view).write("\texit");
        verify(view).write("\t\tto exit the program");
    }
}