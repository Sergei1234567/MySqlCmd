package ua.com.mysqlcmd.view;

import ua.com.mysqlcmd.view.manager.DatabaseManager;
import ua.com.mysqlcmd.view.view.Console;
import ua.com.mysqlcmd.view.view.View;

public class MainController {
    public static void main(String[] args) {
        View view = new Console();
        DatabaseManager manager = new DatabaseManager();

        view.write("Hi user!");
        while (true) {
            view.write("Please enter the connectCommand, database name, username, password in the format:" +
                    "connectCommand databaseName userName  password\n__________________");
            String command = view.read();
            String[] splittedCommand = command.split("\\s");
            String connectCommand = splittedCommand[0];
            String databaseName = splittedCommand[1];
            String userName = splittedCommand[2];
            String password = splittedCommand[3];

            if (connectCommand.equals("connect") == true) {
                try {
                    manager.connect(databaseName, userName, password);
                    manager.closeConnection();

                    break;
                } catch (Exception e) {
                    String message = e.getMessage();
                    if (e.getCause() != null) {
                        message += " " + e.getCause().getMessage();
                    }
                    view.write("Failure due to:" + message);
                    view.write("Try again");
                }
            } else {
                view.write("You entered not right connectCommand");
            }
        }
        view.write("Connection successful");
    }
}
