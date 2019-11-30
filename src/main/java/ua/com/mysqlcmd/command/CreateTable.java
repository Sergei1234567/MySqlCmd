package ua.com.mysqlcmd.command;

import ua.com.mysqlcmd.model.Column;
import ua.com.mysqlcmd.model.manager.DatabaseManager;
import ua.com.mysqlcmd.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateTable implements Command {

    private final DatabaseManager manager;
    private final View view;

    public CreateTable(DatabaseManager manager, View view) {
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
//        if (data.size() % 2 != 0) {
//            throw new IllegalArgumentException("wrong format please check help for help " + command);
//        }

        List<Column> columns = new ArrayList<>();
        String tableName = data.get(1);

        for (int index = 2; index < data.size(); index++) {
            String[] columnDef = data.get(index).split(":");
            columns.add(new Column(columnDef[0],columnDef[1]));
        }
        manager.create(tableName,columns);
        view.write("Success");
    }
}
