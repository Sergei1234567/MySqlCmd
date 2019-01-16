package ua.com.mysqlcmd;

import ua.com.mysqlcmd.DatabaseConnect;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class MainController {
    public static void main(String[] args) throws SQLException{
        DatabaseConnect manager = new DatabaseConnect();
        System.out.println("Hi user!");
        System.out.println("Please enter the database name, username, password in the format:" +
                "connect databaseName userName  password\n__________________");
        Scanner scan = new Scanner(System.in);
        String comand = scan.nextLine();
        String[] data = comand.split("\\s");
        String connectUser = data[0];
        String databaseName = data[1];
        String userName = data[2];
        String password = data[3];
        System.out.println(connectUser + ":" + "Connection successful!");

        manager.connection(databaseName, userName, password);
        Connection connection = manager.getConnection();
        connection.close();
    }

}
