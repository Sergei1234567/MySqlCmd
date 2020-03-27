package ua.com.mysqlcmd.controller;

import ua.com.mysqlcmd.command.Command;
import ua.com.mysqlcmd.command.ExitException;
import ua.com.mysqlcmd.view.View;

public class MainController {
    private Command[] commands;
    private View view;

    public MainController(View view, Command... commands) {
        this.view = view;
        this.commands = commands;
    }

    public void run() {
        try {
            doWork();
        } catch (ExitException e) {
            //do nothing
        }
    }

    private void doWork() {
        view.write("Hello user!");
        view.write("Please enter a command in the format: connect databaseName userName password");
        while (true) {
               String input = view.read();
                for (Command command : commands) {
                    try {
                        if (command.canProcess(input)) {
                            command.process(input);
                            break;
                        }
                    } catch (Exception e) {
                        if (e instanceof ExitException) {
                            throw e;
                        }
                        printError(e);
                        break;
                    }
                }
                view.write("Enter the command (or help for help)");
        }
    }

    private void printError(Exception e) {
        String message = e.getMessage();
        Throwable cause = e.getCause();
        if (cause != null) {
            message += " " + cause.getMessage();
        }
        view.write("Failure! because of: " + message);
        view.write("Please try again.");
    }
}
