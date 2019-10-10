package ua.com.mysqlcmd.controller;

import ua.com.mysqlcmd.command.*;
import ua.com.mysqlcmd.model.manager.DatabaseManager;
import ua.com.mysqlcmd.view.View;

public class MainController {
    private Command[] commands;
    private View view;

    public MainController(View view, DatabaseManager manager) {
        this.view = view;
        this.commands = new Command[]{
                new Connect(manager, view),
                new isConnected(manager, view),
                new Exit(view),
                new Help(view),
                new GetTableNames(manager, view),
                new DisplayingTableContent(manager, view),
                new Unsupported(view)};
    }

    public void run() {
        while (true) {
            view.write("Enter the command (or help for help)");
            String input = view.read();

            for (Command command : commands) {
                if (command.canProcess(input)) {
//                    connect();
                    command.process(input);
                    break;
                }
            }
        }
    }

}
