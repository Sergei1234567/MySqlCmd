package ua.com.mysqlcmd;

import view.Console;
import view.View;

public class MainController {
    public static void main(String[] args) {
        View view = new Console();
        DatabaseManager manager = new DatabaseManager();

        view.write("Hi user!");
        while (true) {
            System.out.println("Please enter the database name, username, password in the format:" +
                    "connect databaseName userName  password\n__________________");
            String command = view.read();
            String[] splittedCommand = command.split("\\s");
            String connectCommand = splittedCommand[0];
            String databaseName = splittedCommand[1];
            String userName = splittedCommand[2];
            String password = splittedCommand[3];
            try {
                manager.connect(databaseName, userName, password);
                manager.closeConnection();
                break;
            } catch (Exception e) {
                String message = e.getMessage();
                if(e.getCause() != null){
                    message += " " + e.getCause().getMessage();
                }
                view.write("Failure due to:" + message);
                view.write("Try again");
            }
        }
        view.write("Connection successful");
    }
}
