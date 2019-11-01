package ua.com.mysqlcmd.command;

import com.mysql.fabric.xmlrpc.base.Data;
import ua.com.mysqlcmd.model.Column;
import ua.com.mysqlcmd.model.Table;
import ua.com.mysqlcmd.model.manager.DatabaseManager;
import ua.com.mysqlcmd.view.View;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        Map<Column, String> dataInsert = new LinkedHashMap<>();
        String tableName = data[1];

        for (int index = 2; index < data.length; index++) {
           String columnName = data[index];
            String value = data[index];

            dataInsert.put(columnName,value);
        }
        manager.insert(tableName, dataInsert);

        view.write("Success");
    }
}
