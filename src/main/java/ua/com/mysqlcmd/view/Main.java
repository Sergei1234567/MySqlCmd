package ua.com.mysqlcmd.view;

import java.sql.*;
import java.util.Random;

public class Main {
    public static void main(String[] argv) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sqlcmd?useSSL=false", "root",
                "root");

        // insert
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO user (userName, password)" +
                "VALUES ('Stiven', 'Pupkin')");
        stmt.close();

        // select
        stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE id > 10");
        while (rs.next()) {
            System.out.println("id:" + rs.getString("id"));
            System.out.println("userName:" + rs.getString("userName"));
            System.out.println("password:" + rs.getString("password"));
            System.out.println("-----");
        }
        rs.close();
        stmt.close();

        // table names
        stmt = connection.createStatement();
        rs = stmt.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema='sqlcmd'");
        while (rs.next()) {
            System.out.println(rs.getString("table_name"));
        }
        System.out.println(" ");
        rs.close();
        stmt.close();

        // delete
        stmt = connection.createStatement();
        stmt.executeUpdate("DELETE FROM user " +
                "WHERE userName = 'Stiven' AND password = 'Pupkin'");
        stmt.close();

        // update
        PreparedStatement ps = connection.prepareStatement(
                "UPDATE user SET password = ? WHERE id > 3");
        String pass = "password_" + new Random().nextInt();
        ps.setString(1, pass);
        ps.executeUpdate();
        ps.close();
        boolean status = stmt.isClosed();
        boolean status1 = ps.isClosed();
        boolean staus3 = rs.isClosed();

        connection.close();
        boolean status2 = connection.isClosed();
        System.out.println("stm.isClosed: " + status + "\n" + "ps.isClosed: " + status1 + "\n" + "connect.isClosed: "
                + status2 + "\n" + "rs.isClosed: " + staus3 );
    }
}
