package ua.com.mysqlcmd.controller;

import ua.com.mysqlcmd.command.Command;
import ua.com.mysqlcmd.command.Exit;
import ua.com.mysqlcmd.command.GetTableNames;
import ua.com.mysqlcmd.command.Help;
import ua.com.mysqlcmd.model.Column;
import ua.com.mysqlcmd.model.Table;
import ua.com.mysqlcmd.model.manager.DatabaseManager;
import ua.com.mysqlcmd.view.View;

import java.util.List;
import java.util.Set;

public class MainController {
    private Command[] commands;
    private View view;
    private DatabaseManager manager;
    private Set<String> tables;

    public MainController(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
        this.commands = new Command[]{new Exit(view), new Help(view), new GetTableNames(manager, view)};
    }

    public void run() {
        connect();
//        displayingTableContent();
        while (true) {
            view.write("Enter the command (or help for help)");
            String command = view.read();
            if (commands[0].canProcess(command)) {
                commands[0].process(command);
            } else if (commands[1].canProcess(command)) {
                commands[1].process(command);
            } else if (commands[2].canProcess(command)) {
                commands[2].process(command);

            } else {
                view.write("Non-existent team: " + command);
            }
        }
    }

    private void connect() {
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


    private void displayingTableContent() {
        while (true) {
            view.write("To view data from one of the tables, enter the name of the table in the format: table name\n___________");
            String command = view.read();
            if (command.equals(command)) {
                try {
                    Table table = manager.getTable(command);
                    view.write("\n");
                    for (Column column : table.getColumns()) {
                        view.write(String.format("%1$-25s", column.getName()));
                    }
                    for (List<Table.Data> row : table.getData()) {
                        for (Table.Data data : row) {
                            view.write(String.format("%1$-25s", data.getValue()));
                        }
                    }
                    break;
                } catch (Exception e) {
                    String message = e.getMessage();
                    view.write("Failure due:" + message);
                    view.write("Try again");
                }
            } else {
                view.write("command [" + command + "] not found.\n try again");
            }

        }

    }
}
