package lab5.connection.builder;

import lab5.exception.DatabaseConnectionException;
import lab5.utils.GlobalConfig;
import lab5.connection.ConnectionBuilder;

import java.sql.Connection;
import java.sql.DriverManager;

public class SimpleConnectionBuilder implements ConnectionBuilder {

    private final String url;
    private final String login;
    private final String password;

    public SimpleConnectionBuilder() throws DatabaseConnectionException {
        try {
            GlobalConfig config = new GlobalConfig();
            config.loadGlobalConfig("database.properties");
            Class.forName(config.getProperty("connection.driver.class"));
            url = config.getProperty("connection.url");
            login = config.getProperty("connection.login");
            password = config.getProperty("connection.password");
        } catch (Exception ex) {
            throw new DatabaseConnectionException(ex.getMessage());
        }
    }

    @Override
    public Connection getConnection() throws DatabaseConnectionException {
        try {
            return DriverManager.getConnection(url, login, password);
        } catch (Exception ex) {
            throw new DatabaseConnectionException(ex.getMessage());
        }
    }

}
