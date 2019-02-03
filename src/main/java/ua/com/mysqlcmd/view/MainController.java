package ua.com.mysqlcmd.view;

import ua.com.mysqlcmd.view.manager.MySqlDatabaseManager;
import ua.com.mysqlcmd.view.view.Console;
import ua.com.mysqlcmd.view.view.View;

public class MainController {
    public static void main(String[] args) {
        View view = new Console();
        MySqlDatabaseManager manager = new MySqlDatabaseManager();

        view.write("Привет пользователь!");

        while (true) {
            view.write("Пожалуйста, введите команду в формате: connect databaseName userName  password\n__________________");
            String command = view.read();
            String[] splittedCommand = command.split("\\s");
            String connectCommand = splittedCommand[0];
            String databaseName = splittedCommand[1];
            String userName = splittedCommand[2];
            String password = splittedCommand[3];

            if (connectCommand.equals("connect")) {
                try {
                    manager.connect(databaseName, userName, password);

                    break;
                } catch (Exception e) {
                    String message = e.getMessage();
                    if (e.getCause() != null) {
                        message += " " + e.getCause().getMessage();
                    }
                    view.write("Отказ из-за:" + message);
                    view.write("Попробуйте снова");
                }
            } else {
                view.write("команда [" + connectCommand + "]Не найдено. Попробуйте снова");
            }
        }
        view.write("Соединение успешно");

        while (true) {
            view.write(" чтобы получить список всех таблиц введите команду в формате: getTableCommand \n_______");
            String getTableCommand = view.read();
            if (getTableCommand.equals("tables")) {
                try {
                    manager.getTableNames();
                    manager.closeConnection();
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                view.write("команда [" + getTableCommand + " не найдена.Попробуйте снова]");
            }
        }
    }

}
