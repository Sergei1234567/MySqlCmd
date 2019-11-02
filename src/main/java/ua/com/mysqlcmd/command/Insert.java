package ua.com.mysqlcmd.command;

import ua.com.mysqlcmd.model.Column;
import ua.com.mysqlcmd.model.manager.DatabaseManager;
import ua.com.mysqlcmd.view.View;

import java.util.*;

public class Insert implements Command {

    private final DatabaseManager manager;
    private final View view;
    private String[] strings;

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

        String[] data = command.split("\\s");
        // TODO сделать у всех команд одинаковый формат вывода сообщений об ошибке
        if (data.length % 2 != 0) {
            throw new IllegalArgumentException(String.format("Должно быть четное " +
                    "количество параметров в формате " +
                    "'create|tableName|column1|value1|column2|value2|...|columnN|valueN', " +
                    "а ты прислал: '%s'", command));
        }

        String tableName = data[1];
        Map<Column, String> dataInsert = new HashMap<>();

        for (int index = 2; index < (data.length / 2); index++) {
            String columnName = data[index * 2];
            String value = data[index * 2 + 1];

            dataInsert.put(new Column(columnName), value);
        }
        manager.insert(tableName, dataInsert);

        view.write("Success");
    }
}
