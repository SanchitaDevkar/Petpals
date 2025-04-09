package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBPropertyUtil {

    public static String getConnectionString(String propertyFileName) {
        Properties prop = new Properties();
        try (FileInputStream input = new FileInputStream(propertyFileName)) {
            prop.load(input);
            String url = prop.getProperty("db.url");
            String username = prop.getProperty("db.username");
            String password = prop.getProperty("db.password");
            return url + "?user=" + username + "&password=" + password;
        } catch (IOException e) {
            System.out.println("Error reading property file: " + e.getMessage());
            return null;
        }
    }
}
