package ua.com.mysqlcmd.command;

import ua.com.mysqlcmd.view.View;

public class Exit implements Command {

    private View<String> view;

    public Exit(View<String> view) {
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return command.equals("exit");
    }

    @Override
    public void process(String command) {
        view.write("See you soon!");
        throw new ExitException();
    }
}
