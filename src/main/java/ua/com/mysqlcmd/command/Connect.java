package ua.com.mysqlcmd.command;

import ua.com.mysqlcmd.model.manager.DatabaseManager;
import ua.com.mysqlcmd.view.View;

import java.util.Arrays;
import java.util.List;

public class Connect implements Command {

    private static String COMMAND_SAMPLE = "connect|sqlcmd|root|root";

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
            List<String> data = Arrays.asList(command.split("\\|"));
            if (data.size() != countValidCommandSize()) {
                throw new IllegalArgumentException(String.format("the wrong number of parameters, wait %s, but there are: %s",
                        countValidCommandSize(), data.size()));
            }
            String databaseName = data.get(1);
            String userName = data.get(2);
            String password = data.get(3);
            manager.connect(databaseName, userName, password);
            view.write("Success");
        } catch (Exception e) {
            String message = e.getMessage();
            view.write("Failure due:" + message + "\n" + "Try again");
        }
    }

    private int countValidCommandSize() {
        return COMMAND_SAMPLE.split("\\|").length;
    }
}
