package ua.com.mysqlcmd.command;

import ua.com.mysqlcmd.model.manager.DatabaseManager;
import ua.com.mysqlcmd.view.View;

import java.util.Arrays;
import java.util.List;

public class Clear implements Command {

    private final DatabaseManager manager;
    private final View<String> view;

    public Clear(DatabaseManager manager, View<String> view) {
        this.manager = manager;
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("clear|");
    }

    @Override
    public void process(String command) {
        List<String> data = Arrays.asList(command.split("\\|"));
        if (data.size() != 2) {
            throw new IllegalArgumentException(String.format("Command format 'clear|tableName',and you entered: ") + command);
        }
        manager.clear(data.get(1));

        view.write(String.format("The table %s has been cleared successfully.", data.get(1)));
    }
}
