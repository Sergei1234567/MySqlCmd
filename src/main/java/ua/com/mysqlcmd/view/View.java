package ua.com.mysqlcmd.view;

public interface View<T> {

    void write(T t);

    String read();

    default void logError(String message) {
        System.err.print(message);
    }
}
