package ua.com.mysqlcmd.command;

import ua.com.mysqlcmd.model.manager.DatabaseManager;
import ua.com.mysqlcmd.view.View;

import java.util.Arrays;

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
        String[] tableNames = manager.getTableNames().toArray(new String[0]);

        String message = Arrays.toString(tableNames);

        view.write(message);
    }
}
