package ua.com.mysqlcmd.command;

import ua.com.mysqlcmd.model.manager.DatabaseManager;
import ua.com.mysqlcmd.view.View;

import java.util.Arrays;
import java.util.Set;

public class GetTableNames implements Command {
    private DatabaseManager manager;
    private View view;

    public GetTableNames(DatabaseManager manager, View view) {
        this.manager = manager;
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return command.equals("tables");
    }

    @Override
    public void process(String command) {
        Set<String> tableNames = manager.getTableNames();
        if (!command.equals("tables")) {
            view.write("Non-existent team: wrong format please check help for help");
        }

        String message = Arrays.toString(new Set[]{tableNames});
        view.write(message);
    }
}
