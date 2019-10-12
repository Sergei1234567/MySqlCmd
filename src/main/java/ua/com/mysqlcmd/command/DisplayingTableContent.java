package ua.com.mysqlcmd.command;

import ua.com.mysqlcmd.model.Column;
import ua.com.mysqlcmd.model.Table;
import ua.com.mysqlcmd.model.manager.DatabaseManager;
import ua.com.mysqlcmd.view.View;

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
        return command.startsWith(command);
    }

    @Override
    public void process(String command) {
        if (command.equals(command)) {
            try {
                Table table = manager.getTable(command);
                view.write("\n");
                for (Column column : table.getColumns()) {
                    view.write(String.format("%1$-25s", column.getName()));
                }
                for (List<Table.Data> row : table.getData()) {
                    for (Table.Data data : row) {
                        view.write(String.format("%1$-25s", data.getValue()));
                    }
                }
            } catch (Exception e) {
                String message = e.getMessage();
                view.write("Failure due:" + message);
                view.write("Try again");
            }
        } else {
            view.write("command [" + command + "] not found.\n try again");
        }
    }

}
