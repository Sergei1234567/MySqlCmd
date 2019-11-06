package ua.com.mysqlcmd.command;

import ua.com.mysqlcmd.model.Column;
import ua.com.mysqlcmd.model.manager.DatabaseManager;
import ua.com.mysqlcmd.view.View;

import java.util.*;

public class Insert implements Command {

    private final DatabaseManager manager;
    private final View view;

    public Insert(DatabaseManager manager, View view) {
        this.manager = manager;
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("insert");
    }

    @Override
    public void process(String command) {

        List<String> data = Arrays.asList(command.split("\\|"));
        if (data.size() % 2 != 0) {
            throw new IllegalArgumentException(String.format("Must be even " +
                    "number of parameters in the format" +
                    "'create | tableName | column1 | value1 | column2 | value2 | ... | columnN | valueN'," +
                    "and you sent: '%s'", command));
        }

        String tableName = data.get(1);
        Map<Column, String> dataInsert = new HashMap<>();

        for (int index = 1; index < data.size() / 2; index++) {
            String columnName = data.get(index * 2);
            String value = data.get(index * 2 + 1);

            dataInsert.put(new Column(columnName), value);
        }
        manager.insert(tableName, dataInsert);

        view.write("Success");
    }
}
