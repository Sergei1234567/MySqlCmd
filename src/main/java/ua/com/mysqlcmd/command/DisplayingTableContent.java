package ua.com.mysqlcmd.command;

import ua.com.mysqlcmd.model.Column;
import ua.com.mysqlcmd.model.Table;
import ua.com.mysqlcmd.model.manager.DatabaseManager;
import ua.com.mysqlcmd.view.View;

import java.util.Arrays;
import java.util.List;

public class DisplayingTableContent implements Command {
    private DatabaseManager manager;
    private View view;

    public DisplayingTableContent(DatabaseManager manager, View view) {
        this.manager = manager;
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("find|");
    }

    @Override
    public void process(String command) {
        List<String> strings = Arrays.asList(command.split("\\|"));
        if (strings.size() % 2 != 0) {
            throw new IllegalArgumentException("wrong format please check help for help" + command);
        }
        String tableName = strings.get(1);

        try {
            Table table = manager.getTable(tableName);
            for (Column column : table.getColumns()) {
                view.write(String.format("%1$-25s", column.getName()));
            }
            view.write("\n");
            for (List<Table.Data> row : table.getRows()) {
                for (Table.Data data : row) {
                    view.write(String.format("%1$-25s", data.getValue()));
                }
                view.write("\n");
            }
        } catch (Exception e) {
            String message = e.getMessage();
            view.write("Failure due:" + message);
            view.write("Try again");
        }
    }
}
