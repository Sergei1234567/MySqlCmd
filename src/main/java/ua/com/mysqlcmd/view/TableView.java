package ua.com.mysqlcmd.view;

import ua.com.mysqlcmd.model.Column;
import ua.com.mysqlcmd.model.Table;

import java.util.List;

public class TableView implements View<Table> {
    @Override
    public void write(Table table) {
        for (Column column : table.getColumns()) {
            System.out.printf("%1$-25s", column.getName());
        }

        System.out.println("\n");

        for (List<Table.Data> row : table.getRows()) {
            for (Table.Data data : row) {
                System.out.printf("%1$-25s", data.getValues());
            }
            System.out.println("\n");
        }
    }

    @Override
    public String read() {
        return null;
    }
}
