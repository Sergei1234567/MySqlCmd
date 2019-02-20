package ua.com.mysqlcmd.view.manager;

import ua.com.mysqlcmd.view.view.Console;
import ua.com.mysqlcmd.view.view.View;

import java.util.Set;

public class Tables {
    public void getTableNamesCommand(MySqlDatabaseManager manager) {
        View view = new Console();
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
                    if(e.getCause() != null){
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
}
