package ua.com.mysqlcmd.command;

import ua.com.mysqlcmd.model.Column;
import ua.com.mysqlcmd.model.manager.DatabaseManager;
import ua.com.mysqlcmd.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Create implements Command {

    private final DatabaseManager manager;
    private final View view;

    public Create(DatabaseManager manager, View view) {
        this.manager = manager;
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("create|");
    }

    @Override
    public void process(String command) {

        List<String> data = Arrays.asList(command.split("\\|"));
        List<Column> columns = new ArrayList<>();
        String tableName = data.get(1);

        for (int index = 2; index < data.size(); index++) {
            String columnName = data.get(index);
            columns.add(new Column(columnName));
        }
        manager.create(tableName,columns);
        view.write("Success");
    }
}
