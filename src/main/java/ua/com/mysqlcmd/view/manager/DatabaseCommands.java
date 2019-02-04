package ua.com.mysqlcmd.view.manager;

public class DatabaseCommands {
    private String command;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }


    public DatabaseCommands(String command) {
        this.command = command;
    }

}
