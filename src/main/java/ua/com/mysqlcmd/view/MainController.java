package ua.com.mysqlcmd.view;

import ua.com.mysqlcmd.view.manager.Connect;
import ua.com.mysqlcmd.view.manager.MySqlDatabaseManager;
import ua.com.mysqlcmd.view.manager.Tables;
import ua.com.mysqlcmd.view.view.Console;
import ua.com.mysqlcmd.view.view.View;

public class MainController {
    public static void main(String[] args) {
        MySqlDatabaseManager manager = new MySqlDatabaseManager();
        Connect connect = new Connect();
        Tables tables = new Tables();
        View view = new Console();
        view.write("Hello user!");

        connect.connectCommand(manager);
        tables.getTableNamesCommand(manager);
        manager.getTableData();
        manager.closeConnection();
    }
}
