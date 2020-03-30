package ua.com.mysqlcmd.util;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public enum ServerProperty {

    DATABASE_DRIVER("database.driver"),
    DATABASE_DATA_URL("database.dataURL"),
    DATABASE_USE_SSL("database.useSSL");

    private final String key;

    ServerProperty(String key) {
        this.key = key;
    }

    private static Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(Objects.requireNonNull(ServerProperty.class.getClassLoader().getResourceAsStream("application.properties")));
        } catch (IOException e) {
            throw new RuntimeException("wrong file path application.properties.");
        }
    }

    public String getValue() {
        return properties.getProperty(key);
    }
}