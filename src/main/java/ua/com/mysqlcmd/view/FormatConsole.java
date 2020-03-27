package ua.com.mysqlcmd.view;

public interface FormatConsole {
    default void write(String format,String message) {
        System.out.printf(format,message);
    }
}
