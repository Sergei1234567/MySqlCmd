package ua.com.mysqlcmd.controller;

import ua.com.mysqlcmd.command.*;
import ua.com.mysqlcmd.model.manager.DatabaseManager;
import ua.com.mysqlcmd.view.View;

public class MainController {
    private Command[] commands;
    private View view;
    private DatabaseManager manager;

    public MainController(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
        this.commands = new Command[]{new Exit(view),
                new Help(view),
                new GetTableNames(manager, view),
                new DisplayingTableContent(manager, view),
                new Connect(manager, view),
                new Unsupported(view)};
    }

    public void run() {
        connect();
        while (true) {
            view.write("Enter the command (or help for help)");
            String input = view.read();

            for (Command command : commands){
                if (command.canProcess(input)) {
                    command.process(input);
                    break;
                }
            }
//            if (commands[0].canProcess(command)) {
//                commands[0].process(command);
//            } else if (commands[1].canProcess(command)) {
//                commands[1].process(command);
//            } else if (commands[2].canProcess(command)) {
//                commands[2].process(command);
//            } else if (commands[3].canProcess(command)) {
//                commands[3].process(command);
//            } else if (commands[4].canProcess(command)) {
//                commands[4].process(command);
//            } else {
//                view.write("Non-existent team: " + command);
//            }
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
}
