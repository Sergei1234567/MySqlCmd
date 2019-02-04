package ua.com.mysqlcmd.view;

import ua.com.mysqlcmd.view.manager.ConnectToDatabase;
import ua.com.mysqlcmd.view.manager.MySqlDatabaseManager;
import ua.com.mysqlcmd.view.view.Console;
import ua.com.mysqlcmd.view.view.View;


public class MainController {
    public static void main(String[] args) {
        View view = new Console();
        view.write("Hello user!");
        ConnectToDatabase database = new ConnectToDatabase();
        database.connectCommand(new MySqlDatabaseManager());
        database.getTableNamesCommand(new MySqlDatabaseManager());

    }

}
