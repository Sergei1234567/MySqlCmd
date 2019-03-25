package ua.com.mysqlcmd.view;

import java.util.Scanner;

public class Console implements View{

    @Override
    public void write(String message) {
        System.out.println(message);
    }

    @Override
    public String read() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }
}
