package ua.com.mysqlcmd.command;

import ua.com.mysqlcmd.model.manager.DatabaseManager;
import ua.com.mysqlcmd.view.View;

public class Clear implements Command {

    private final DatabaseManager manager;
    private final View view;

    public Clear(DatabaseManager manager, View view) {
        this.manager = manager;
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return false;
    }

    @Override
    public void process(String command) {
       String[] data = command.split("\\$");
        manager.clear(data[1]);

    }
}
