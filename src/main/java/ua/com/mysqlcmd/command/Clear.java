package ua.com.mysqlcmd.command;

import ua.com.mysqlcmd.model.manager.DatabaseManager;
import ua.com.mysqlcmd.view.View;

public class Clear implements Command {

    private final DatabaseManager manager;
    private final View view;

    public Clear(DatabaseManager manager, View view) {
        this.manager = manager;
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("clear");
    }

    @Override
    public void process(String command) {
       String[] data = command.split("\\s");
       if(data.length != 2){
           throw new  IllegalArgumentException("Command format 'clear tableName',and you entered: " + command);
       }
        manager.clear(data[1]);

       view.write(String.format("The table %s has been cleared successfully.", data[1]));
    }
}
