package ua.com.mysqlcmd.command;

import ua.com.mysqlcmd.model.manager.DatabaseManager;
import ua.com.mysqlcmd.view.View;

import java.util.Set;

public class GetTableNames implements Command {
    private DatabaseManager manager;
    private View<String> view;

    public GetTableNames(DatabaseManager manager, View<String> view) {
        this.manager = manager;
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return command.equals("tables");
    }

    @Override
    public void process(String command) {
        if (!command.equals("tables")) {
            view.write("Non-existent command: wrong format please check help for help");
        }

        Set<String> tableNames = manager.getTableNames();

        view.write(tableNames.toString());
    }
}
