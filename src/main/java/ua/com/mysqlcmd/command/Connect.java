package ua.com.mysqlcmd.command;

import ua.com.mysqlcmd.model.manager.DatabaseManager;
import ua.com.mysqlcmd.view.View;

public class Connect implements Command {

    private DatabaseManager manager;
    private View view;

    public Connect(DatabaseManager manager, View view) {
        this.manager = manager;
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("connect ");
    }

    @Override
    public void process(String command) {
        while (true) {
            view.write("Hello user!");
            view.write("Please enter a command in the format: connect databaseName userName password\n__________________");
            command = view.read();
            String[] strings = command.split("\\s");
            String connectCommand = strings[0];
            String databaseName = strings[1];
            String userName = strings[2];
            String password = strings[3];

            if (connectCommand.equals("connect")) {
                try {
                    manager.connect(databaseName, userName, password);
                    break;
                } catch (Exception e) {
                    String message = e.getMessage();
                    view.write("Failure due:" + message);
                    view.write("Try again");
                }
            } else {
                view.write("Command[" + connectCommand + "]Not found. Try again");
            }
        }
        view.write("Success");
    }
}
