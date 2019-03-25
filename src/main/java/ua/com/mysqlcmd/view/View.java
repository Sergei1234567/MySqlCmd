package ua.com.mysqlcmd.view;

public interface View {

    void write(String message);

    String read();
}
