package ua.com.mysqlcmd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class DatabaseConnect {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println("enter the command: connect database username password\n__________");
        Scanner scan = new Scanner(System.in);
        String comand = scan.nextLine();
        String[] data = comand.split("|");
        String databaseName = data[0];
        String userName = data[1];
        String password = data[2];

        Connection connection = getConnection(databaseName, userName, password);
        connection.close();
    }

    private static Connection getConnection(String databaseName, String userName, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName, userName, password);
        if (connection != null) {
            System.out.println("You made it, take control your databaseName now!");
        } else {
            System.out.println("Failed to make connection!");

        }
        return connection;
    }

}
