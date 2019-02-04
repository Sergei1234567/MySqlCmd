package ua.com.mysqlcmd.view.manager;

import ua.com.mysqlcmd.view.view.Console;
import ua.com.mysqlcmd.view.view.View;

public class TeamList {
    public void viewCommands() {
        View view = new Console();
        view.write("list of all tables: tables");
    }
}
