package ua.com.mysqlcmd.command;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import ua.com.mysqlcmd.model.Column;
import ua.com.mysqlcmd.model.Table;
import ua.com.mysqlcmd.model.manager.DatabaseManager;
import ua.com.mysqlcmd.view.View;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.atLeastOnce;

public class DisplayingTableContentTest {

    private DatabaseManager manager;
    private View view;

    @Before
    public void setUp() {
        manager = mock(DatabaseManager.class);
        view = mock(View.class);
    }

    @Test
    public void test() {
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

        Command command = new DisplayingTableContent(manager, view);
        when(manager.getTable("user"))
                .thenReturn(new Table("user", column, rows));

        //When
        command.process("find|user");

        //Then
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(view, atLeastOnce()).write(captor.capture());
        assertEquals("", captor.getAllValues().toString());
    }
}