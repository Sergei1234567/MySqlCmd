package ua.com.mysqlcmd.command;

import ua.com.mysqlcmd.view.View;

public class Unsupported implements Command {
    private View<String> view;

    public Unsupported(View<String> view) {
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return true;
    }

    @Override
    public void process(String command) {
        view.write("Non-existent team: " + command);
    }
}
