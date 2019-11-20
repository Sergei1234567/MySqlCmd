package ua.com.mysqlcmd.command;

import ua.com.mysqlcmd.model.manager.DatabaseManager;
import ua.com.mysqlcmd.view.View;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Update implements Command {
    private final DatabaseManager manager;
    private final View view;

    public Update(DatabaseManager manager, View view) {
        this.manager = manager;
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("update|");
    }

    @Override
    public void process(String command) {
        List<String> data = Arrays.asList(command.split("\\|"));

        if (data.size() % 2 != 0) {
            throw new IllegalArgumentException(String.format("wrong format please check help for help", command));
        }

        String tableName = data.get(1);
        int id = Integer.parseInt(data.get(data.size() - 1));

        Map<String, Object> dataUpdate = new HashMap<>();

        for (int index = 1; index < data.size() / 2; index++) {
            String columnName = data.get(index * 2);
            String values = data.get(index * 2 + 1);
            dataUpdate.put(columnName, values);
        }
        manager.update(tableName, id, dataUpdate);
    }
}
