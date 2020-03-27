package ua.com.mysqlcmd.view;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Console implements View, FormatConsole{

    @Override
    public void write(String message) {
        System.out.println(message);
    }

    @Override
    public String read() {
        try {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
        }catch (NoSuchElementException e){
            return null;
        }
    }
}
