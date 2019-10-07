package ua.com.mysqlcmd.command;

public interface Command {
    boolean canProcess(String command);

    void process(String command);
}
