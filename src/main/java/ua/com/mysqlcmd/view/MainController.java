package ua.com.mysqlcmd.view;

import ua.com.mysqlcmd.view.model.manager.Connect;
import ua.com.mysqlcmd.view.model.manager.MySqlDatabaseManager;
import ua.com.mysqlcmd.view.model.manager.TableName;
import ua.com.mysqlcmd.view.view.Console;
import ua.com.mysqlcmd.view.view.View;

public class MainController {
    public static void main(String[] args) {
        MySqlDatabaseManager manager = new MySqlDatabaseManager();
        Connect connect = new Connect();
        TableName tables = new TableName();
        View view = new Console();
        view.write("Hello user!");

        connect.connectCommand(manager);
        tables.getTableNamesCommand(manager);
        manager.getTableData();
        manager.closeConnection();
    }
}
