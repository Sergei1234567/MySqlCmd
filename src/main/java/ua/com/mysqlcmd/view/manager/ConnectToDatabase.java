package ua.com.mysqlcmd.view.manager;

import ua.com.mysqlcmd.view.view.Console;
import ua.com.mysqlcmd.view.view.View;

import java.util.Arrays;

public class ConnectToDatabase {
    public void connectCommand(MySqlDatabaseManager manager) {
        View view = new Console();
        while (true) {
            view.write("Please enter a command in the format: connect databaseName userName password\n__________________");
            String command = view.read();
            String[] splittedCommand = command.split("\\s");
            String connectCommand = splittedCommand[0];
            String databaseName = splittedCommand[1];
            String userName = splittedCommand[2];
            String password = splittedCommand[3];

            if (connectCommand.equals("connect")) {
                try {
                    manager.connect(databaseName, userName, password);
                    break;
                } catch (Exception e) {
                    String message = e.getMessage();
                    if (e.getCause() != null) {
                        message += " " + e.getCause().getMessage();
                    }
                    view.write("Failure due:" + message);
                    view.write("Try again");
                }
            } else {
                view.write("Command[" + connectCommand + "]Not found. Try again");
            }
        }
        view.write("Success");
    }
    public void getTableNamesCommand(MySqlDatabaseManager manager){
        View view = new Console();
        while (true) {
            view.write(" To get a list of all tables enter the command in the format: getTableCommand \n_______");
            String getTableCommand = view.read();
            if (getTableCommand.equals("tables")) {
                try {
                    manager.getTableNames();
//                    view.write(Arrays.toString(tables));
                    manager.closeConnection();
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                view.write("command [" + getTableCommand + "] not found. try again");
            }
        }
    }
}
