package ua.com.mysqlcmd.command;

import ua.com.mysqlcmd.model.manager.DatabaseManager;
import ua.com.mysqlcmd.view.View;

public class IsConnected implements Command {

    private DatabaseManager manager;
    private View<String> view;

    public IsConnected(DatabaseManager manager, View<String> view) {
        this.manager = manager;
        this.view = view;

    }

    @Override
    public boolean canProcess(String command) {
        return !manager.isConnected();
    }

    @Override
    public void process(String command) {
        view.write(String.format("You conneot use the command '%s' yet do not connect using the command connect|" +
                "databaseName|userName|password|", command));
    }
}
