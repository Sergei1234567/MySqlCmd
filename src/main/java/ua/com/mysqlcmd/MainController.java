package ua.com.mysqlcmd;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class MainController {
    public static void main(String[] args) throws SQLException {
        DatabaseManager manager = new DatabaseManager();
        System.out.println("Hi user!");
        while (true) {
            System.out.println("Please enter the database name, username, password in the format:" +
                    "connect databaseName userName  password\n__________________");
            Scanner scan = new Scanner(System.in);
            String command = scan.nextLine();
            String[] splittedCommand = command.split("\\s");
            String connectCommand = splittedCommand[0];
            String databaseName = splittedCommand[1];
            String userName = splittedCommand[2];
            String password = splittedCommand[3];
            try {
                manager.connection(databaseName, userName, password);
                Connection connection = manager.getConnection();
                connection.close();
                break;
            } catch (Exception e) {
                String message = e.getMessage();
                if(e.getCause() != null){
                    message += " " + e.getCause().getMessage();
                }
                System.out.println("Failure due to:" + message);
                System.out.println("Try again");
            }
        }
        System.out.println("Connection successful");
    }
}
