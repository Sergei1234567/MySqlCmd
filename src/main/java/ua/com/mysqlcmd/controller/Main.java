package ua.com.mysqlcmd.controller;

import ua.com.mysqlcmd.command.*;
import ua.com.mysqlcmd.model.Table;
import ua.com.mysqlcmd.model.manager.DatabaseManager;
import ua.com.mysqlcmd.model.manager.MySqlDatabaseManager;
import ua.com.mysqlcmd.view.Console;
import ua.com.mysqlcmd.view.TableView;
import ua.com.mysqlcmd.view.View;

public class Main {
    public static void main(String[] args) {
        View<String> view = new Console();
        View<Table> tableView = new TableView();
        DatabaseManager manager = new MySqlDatabaseManager();
        MainController controller = new MainController(view,
                new Connect(manager, view),
                new Help(view),
                new Exit(view),
                new IsConnected(manager, view),
                new GetTableNames(manager, view),
                new Clear(manager, view),
                new CreateTable(manager, view),
                new Insert(manager, view),
                new Update(manager, view),
                new DisplayingTableContent(manager, tableView),
                new DropTable(manager, view),
                new Unsupported(view));
        controller.run();
    }
}
