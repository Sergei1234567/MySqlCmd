package ua.com.mysqlcmd.command;

import ua.com.mysqlcmd.model.Column;
import ua.com.mysqlcmd.model.Table;
import ua.com.mysqlcmd.model.manager.DatabaseManager;
import ua.com.mysqlcmd.view.FormatConsole;
import ua.com.mysqlcmd.view.View;

import java.util.Arrays;
import java.util.List;

public class DisplayingTableContent implements Command {
    private DatabaseManager manager;
    private View view;
    private FormatConsole formatConsole;

    public DisplayingTableContent(DatabaseManager manager, View view, FormatConsole formatConsole) {
        this.manager = manager;
        this.view = view;
        this.formatConsole = formatConsole;
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
                formatConsole.write("%1$-25s", column.getName());
//                System.out.printf("%1$-25s", column.getName());
            }
            view.write("\n");
            for (List<Table.Data> row : table.getRows()) {
                for (Table.Data data : row) {
//                    System.out.printf("%1$-25s", data.getValue());
                    formatConsole.write("%1$-25s", data.getValues());
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
