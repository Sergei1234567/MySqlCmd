package ua.com.mysqlcmd.command;

import ua.com.mysqlcmd.model.manager.DatabaseManager;
import ua.com.mysqlcmd.view.View;

import java.util.Arrays;
import java.util.List;

public class CreateDatabase implements Command {

    private final DatabaseManager manager;
    private final View view;

    public CreateDatabase(DatabaseManager manager, View view) {
        this.manager = manager;
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("createDatabase|");
    }

    @Override
    public void process(String command) {
        List<String> data = Arrays.asList(command.split("\\|"));
        String dataBase = data.get(1);
        manager.createDatabase(dataBase);
        view.write("Database with name " + dataBase + "\n successful created");


    }
}
