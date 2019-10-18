package ua.com.mysqlcmd.integration;

import org.junit.Before;
import org.junit.Test;
import ua.com.mysqlcmd.controller.Main;
import ua.com.mysqlcmd.model.manager.DatabaseManager;
import ua.com.mysqlcmd.model.manager.MySqlDatabaseManager;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;

public class integrationTest {

    private static ConfigurableInputStream in;
    private static ByteArrayOutputStream out;
    private DatabaseManager databaseManager;

    @Before
    public void setup() {
        databaseManager = new MySqlDatabaseManager();
        out = new ByteArrayOutputStream();
        in = new ConfigurableInputStream();


        System.setIn(in);
        System.setOut(new PrintStream(out));
    }

    @Test
    public void testHelp() {

        //given
        in.add("help");
        in.add("exit");

        //when
        Main.main(new String[0]);

        //then
        assertEquals("Hello user!\r\n" +
                "Please enter a command in the format: connect databaseName userName password\r\n" +
                "Existing teams:\r\n" +
                "\tconnect databaseName userName password\r\n" +
                "\t\tto connect to the database with which we will work\r\n" +
                "\ttables\r\n" +
                "\t\tto get a list of all the tables of the database to which you are connected\r\n" +
                "\tclear|tableName\r\n" +
                "\t\tto clear the whole table\r\n" +
                "\tcreate|tableName|column1|value1|column2|value2|...|columnN|valueN\r\n" +
                "\t\tto create a table entry\r\n" +
                "\ttableName\r\n" +
                "\t\tto get the contents of the table'tableName'\r\n" +
                "\thelp\r\n" +
                "\t\tto display this list\r\n" +
                "\texit\r\n" +
                "\t\tto exit the program\r\n" +
                "Enter the command (or help for help)\r\n" +
                "See you soon!\r\n", getData());
    }

    private String getData() {
        try {
            String result = new String(out.toByteArray(), "UTF-8");
            out.reset();
            return result;
        } catch (UnsupportedEncodingException e) {
            return e.getMessage();
        }
    }

    @Test
    public void testExit() {

        //given
        in.add("exit");

        //when
        Main.main(new String[0]);

        //then
        assertEquals("Hello user!\r\n" +
                "Please enter a command in the format: connect databaseName userName password\r\n" +
                "See you soon!\r\n", getData());
    }

    @Test
    public void testGetTableNames_WithoutConnect() {

        //given
        in.add("tables");
        in.add("exit");

        //when
        Main.main(new String[0]);

        //then
        assertEquals("Hello user!\r\n" +
                "Please enter a command in the format: connect databaseName userName password\r\n" +
                "You conneot use the command 'tables' yet do not connect using the command connect databaseName userName password\r\n" +
                "Enter the command (or help for help)\r\n" +
                "See you soon!\r\n", getData());
    }

    @Test
    public void testDisplayingTableContent_WithoutConnect() {

        //given
        in.add("test");
        in.add("exit");

        //when
        Main.main(new String[0]);

        //then
        assertEquals("Hello user!\r\n" +
                "Please enter a command in the format: connect databaseName userName password\r\n" +
                "You conneot use the command 'test' yet do not connect using the command connect databaseName userName password\r\n" +
                "Enter the command (or help for help)\r\n" +
                "See you soon!\r\n", getData());
    }

    @Test
    public void testUnsupported() {

        //given
        in.add("unsupported");
        in.add("exit");

        //when
        Main.main(new String[0]);

        //then
        assertEquals("Hello user!\r\n" +
                "Please enter a command in the format: connect databaseName userName password\r\n" +
                "You conneot use the command 'unsupported' yet do not connect using the command connect databaseName userName password\r\n" +
                "Enter the command (or help for help)\r\n" +
                "See you soon!\r\n", getData());
    }

    @Test
    public void testUnsupportedAfterConnect() {

        //given
        in.add("connect sqlcmd root root");
        in.add("unsupported");
        in.add("exit");

        //when
        Main.main(new String[0]);

        //then
        assertEquals("Hello user!\r\n" +
                "Please enter a command in the format: connect databaseName userName password\r\n" +
                //connect
                "Success\r\n" +
                "Enter the command (or help for help)\r\n" +
                //unsupported
                "Failure due:no data in the table\r\n" +
                "Try again\r\n" +
                "Enter the command (or help for help)\r\n" +
                //exit
                "See you soon!\r\n", getData());
    }

    @Test
    public void testGetTableNames_AfterConnect() {

        //given
        in.add("connect sqlcmd root root");
        in.add("tables");
        in.add("exit");

        //when
        Main.main(new String[0]);

        //then
        assertEquals("Hello user!\r\n" +
                "Please enter a command in the format: connect databaseName userName password\r\n" +
                //connect
                "Success\r\n" +
                "Enter the command (or help for help)\r\n" +
                //getTableNames
                "[[test, user]]\r\n" +
                "Enter the command (or help for help)\r\n" +
                //exit
                "See you soon!\r\n", getData());
    }

    @Test
    public void testDisplayingTableContent_WithError_AfterConnect() {

        //given
        in.add("connect sqlcmd root root");
        in.add("tableName");
        in.add("exit");

        //when
        Main.main(new String[0]);

        //then
        assertEquals("Hello user!\r\n" +
                "Please enter a command in the format: connect databaseName userName password\r\n" +
                //connect
                "Success\r\n" +
                "Enter the command (or help for help)\r\n" +
                //getTableNames
                "Failure due:no data in the table\r\n" +
                "Try again\r\n" +
                "Enter the command (or help for help)\r\n" +
                //exit
                "See you soon!\r\n", getData());
    }

    @Test
    public void testConnect_AfterConnect() {

        //given
        in.add("connect sqlcmd root root");
        in.add("tables");
        in.add("connect world root root");
        in.add("tables");
        in.add("exit");

        //when
        Main.main(new String[0]);

        //then
        assertEquals("Hello user!\r\n" +
                "Please enter a command in the format: connect databaseName userName password\r\n" +
                //connect sqlcmd
                "Success\r\n" +
                "Enter the command (or help for help)\r\n" +
                //tables
                "[[test, user]]\r\n" +
                "Enter the command (or help for help)\r\n" +
                //connect world
                "Success\r\n" +
                "Enter the command (or help for help)\r\n" +
                //tables
                "[[country, city, countrylanguage]]\r\n" +
                "Enter the command (or help for help)\r\n" +
                //exit
                "See you soon!\r\n", getData());
    }
}
