package lab5.connection.builder;

import lab5.exception.DatabaseConnectionException;
import lab5.utils.GlobalConfig;
import lab5.connection.ConnectionBuilder;

import java.sql.Connection;
import java.sql.DriverManager;

public class SimpleConnectionBuilder implements ConnectionBuilder {

    public SimpleConnectionBuilder() throws DatabaseConnectionException {
        try {
            GlobalConfig.loadGlobalConfig("src/main/resources/database.properties");
            Class.forName(GlobalConfig.getProperty("connection.driver.class"));
        } catch (Exception ex) {
            throw new DatabaseConnectionException(ex.getMessage());
        }
    }

    @Override
    public Connection getConnection() throws DatabaseConnectionException {
        String url = GlobalConfig.getProperty("connection.url");
        String login = GlobalConfig.getProperty("connection.login");
        String password = GlobalConfig.getProperty("connection.password");

        try {
            return DriverManager.getConnection(url, login, password);
        } catch (Exception ex) {
            throw new DatabaseConnectionException(ex.getMessage());
        }
    }

}
