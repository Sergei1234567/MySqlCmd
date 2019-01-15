package ua.com.mysqlcmd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class DatabaseConnect {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println("enter the command: connect database username password\n__________");
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        System.out.println(s);
    }

    private static Connection getConnection(String database, String user, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + database, user, password);
        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");

        }
        return connection;
    }

}
