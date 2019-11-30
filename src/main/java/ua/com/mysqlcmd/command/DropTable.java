package ua.com.mysqlcmd.command;

import ua.com.mysqlcmd.model.manager.DatabaseManager;
import ua.com.mysqlcmd.view.View;

import java.util.Arrays;
import java.util.List;

public class DropTable implements Command{

    private final DatabaseManager manager;
    private final View view;

    public DropTable(DatabaseManager manager, View view) {
        this.manager = manager;
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("dropTable|");
    }

    @Override
    public void process(String command) {
        List<String> data = Arrays.asList(command.split("\\|"));
        if (data.size() % 2 != 0) {
            throw new IllegalArgumentException("wrong format please check help for help" + command);
        }

        String tableName = data.get(1);

        view.write("You really want to drop '" + tableName + "' database? Y/N");
        if (view.read().equalsIgnoreCase("y")) {
            manager.dropTable(tableName);
            view.write("Database " + tableName + " dropped");
        }
    }
}
