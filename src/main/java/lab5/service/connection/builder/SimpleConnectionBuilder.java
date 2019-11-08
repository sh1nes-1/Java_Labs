package lab5.service.connection.builder;

import lab5.service.GlobalConfig;
import lab5.service.connection.ConnectionBuilder;
import lab5.service.connection.exception.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
        }
        catch (Exception ex) {
            throw new DatabaseConnectionException(ex.getMessage());
        }
    }

}
