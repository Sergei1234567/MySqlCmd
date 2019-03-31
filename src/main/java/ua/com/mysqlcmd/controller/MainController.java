package ua.com.mysqlcmd.controller;

import ua.com.mysqlcmd.model.Column;
import ua.com.mysqlcmd.model.Table;
import ua.com.mysqlcmd.model.manager.DatabaseManager;
import ua.com.mysqlcmd.view.View;

import java.util.List;
import java.util.Set;

public class MainController {
    private View view;
    private DatabaseManager manager;

    public MainController(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }

    public void run() {
        connectToDb();
        getTableNamesDb();
        displayingTableContents();
    }

    private void connectToDb() {
        while (true) {
            view.write("Hello user!");
            view.write("Please enter a command in the format: connect databaseName userName password\n__________________");
            String command = view.read();
            String[] strings = command.split("\\s");
            String connectCommand = strings[0];
            String databaseName = strings[1];
            String userName = strings[2];
            String password = strings[3];

            if (connectCommand.equals("connect")) {
                try {
                    manager.connect(databaseName, userName, password);
                    break;
                } catch (Exception e) {
                    String message = e.getMessage();
                    view.write("Failure due:" + message);
                    view.write("Try again");
                }
            } else {
                view.write("Command[" + connectCommand + "]Not found. Try again");
            }
        }
        view.write("Success");
    }

    private void getTableNamesDb() {
        while (true) {
            view.write(" To get a list of all tables enter the command in the format: getTableCommand \n_______");
            String getTableCommand = view.read();
            if (getTableCommand.equals("tables")) {
                try {
                    Set<String> tables = manager.getTableNames();
                    view.write(tables.toString());
                    break;
                } catch (Exception e) {
                    String message = e.getMessage();
                    if (e.getCause() != null) {
                        message += " " + e.getCause().getMessage();
                    }
                    view.write("due:" + message);
                    view.write("Try again");
                }
            } else {
                view.write("command [" + getTableCommand + "] not found.\n try again");
            }
        }
    }

    private void displayingTableContents() {
        Table table = manager.getTable("user");
        System.out.print("\n");
        for (Column column : table.getColumns()) {
            System.out.printf("%1$-25s", column.getName());
        }
        System.out.print("\n");
        for (List<Table.Data> row : table.getData()) {
            for (Table.Data data : row) {
                System.out.printf("%1$-25s", data.getValue());
            }
            System.out.print("\n");
        }
    }

}
