package ua.com.mysqlcmd.command;

import ua.com.mysqlcmd.view.View;

public class Help implements Command {
    private View<String> view;

    public Help(View<String> view) {
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("help");
    }

    @Override
    public void process(String command) {
        if (!command.equals("help")) {
            throw new IllegalArgumentException("Command format 'help',and you entered: " + command);
        }

        view.write("Existing teams:");

        view.write("\tconnect|databaseName|userName|password");
        view.write("\t\tto connect to the database with which we will work");

        view.write("\ttables");
        view.write("\t\tto get a list of all the tables of the database to which you are connected");

        view.write("\tfind|");
        view.write("\t\tto get the contents of the table'tableName'");

        view.write("\tclear|tableName|");
        view.write("\t\tto clear the whole table");

        view.write("\tcreate|tableName|column1:value1|column2:value2|...|columnN:valueN|");
        view.write("\t\tto create table in database, enter column description in SQL format\n" +
                "example: user|id:INTEGER|username:varchar(225) NOT NULL UNIQUE|" +
                "password:varchar(225)");

        view.write("\tupdate|tableName|column1|value1|column2|value2|...|columnN|valueN|");
        view.write("\t\tto update table data");

        view.write("\tinsert|tableName|column1|value1|column2|value2|...|columnN|valueN|");
        view.write("\t\tto create a table entry");

        view.write("\thelp");
        view.write("\t\tto display this list");

        view.write("\texit");
        view.write("\t\tto exit the program");

    }

}
