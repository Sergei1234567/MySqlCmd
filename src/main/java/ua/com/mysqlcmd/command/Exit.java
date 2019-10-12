package ua.com.mysqlcmd.command;

import ua.com.mysqlcmd.view.View;

public class Exit implements Command {

        private View view;

        public Exit(View view) {
            this.view = view;
        }

        @Override
        public boolean canProcess(String command) {
            return command.equals("exit");
        }

        @Override
        public void process(String command) {
            view.write("До скорой встречи!");
            throw new ExitException();
        }
}