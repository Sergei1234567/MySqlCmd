package ua.com.mysqlcmd.command;

import ua.com.mysqlcmd.model.Table;
import ua.com.mysqlcmd.model.manager.DatabaseManager;
import ua.com.mysqlcmd.view.View;

import java.util.Arrays;
import java.util.List;

public class DisplayingTableContent implements Command {
    private DatabaseManager manager;
    private View<Table> view;

    public DisplayingTableContent(DatabaseManager manager, View<Table> view) {
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
            view.write(table);
        } catch (Exception e) {
            String message = e.getMessage();
            view.logError("Failure due:" + message + "Try again");
        }
    }
}
