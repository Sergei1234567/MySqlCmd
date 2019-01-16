package ua.com.mysqlcmd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class DatabaseConnect {
    private Connection connection;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
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

    private void connection(String databaseName, String userName, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Please add jdbc jar to project.");
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName, userName, password);
        } catch (SQLException e) {
            System.out.println(String.format("Cant get connection for database:%s user:%s,", databaseName, userName));
            e.printStackTrace();
            connection = null;
        }
    }

    private Connection getConnection() {
        return connection;
    }
}
