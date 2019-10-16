package ua.com.mysqlcmd.controller;

import ua.com.mysqlcmd.model.manager.DatabaseManager;
import ua.com.mysqlcmd.model.manager.MySqlDatabaseManager;
import ua.com.mysqlcmd.view.Console;
import ua.com.mysqlcmd.view.View;

public class Main {
    public static void main(String[] args) {
        View view = new Console();
        DatabaseManager manager = new MySqlDatabaseManager();
        MainController controller = new MainController(view, manager);
        controller.run();
//        manager.closeConnection();
    }

}
