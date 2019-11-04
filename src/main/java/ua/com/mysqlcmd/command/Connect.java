package ua.com.mysqlcmd.command;

import ua.com.mysqlcmd.model.manager.DatabaseManager;
import ua.com.mysqlcmd.view.View;

public class Connect implements Command {

    private static String COMMAND_SAMPLE = "connect sqlcmd root root";

    private DatabaseManager manager;
    private View view;

    public Connect(DatabaseManager manager, View view) {
        this.manager = manager;
        this.view = view;

    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("connect|");
    }

    @Override
    public void process(String command) {

        try {
            String[] strings = command.split("\\|");
            if (strings.length != count()) {
                throw new IllegalArgumentException(String.format("the wrong number of parameters, wait &s," +
                                " but there are: $s",
                        count(), strings.length));
            }
            String databaseName = strings[1];
            String userName = strings[2];
            String password = strings[3];
            manager.connect(databaseName, userName, password);
            view.write("Success");

        } catch (Exception e) {
            String message = e.getMessage();
            view.write("Failure due:" + message);
            view.write("Try again");
        }
    }
    private int count() {
        return COMMAND_SAMPLE.split("\\s").length;
    }
}
