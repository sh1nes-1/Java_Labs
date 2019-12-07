package lab7.connection.builder;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import lab7.connection.ConnectionBuilder;
import lab7.exception.DatabaseConnectionException;
import lab7.utils.GlobalConfig;

import java.sql.Connection;

public class ComboPooledConnectionBuilder implements ConnectionBuilder {

    private ComboPooledDataSource dataSource;

    public ComboPooledConnectionBuilder() throws DatabaseConnectionException {
        try {
            GlobalConfig config = new GlobalConfig();
            config.loadGlobalConfig("database.properties");

            dataSource = new ComboPooledDataSource();
            dataSource.setDriverClass(config.getProperty("connection.driver.class"));
            dataSource.setJdbcUrl(config.getProperty("connection.url"));
            dataSource.setUser(config.getProperty("connection.login"));
            dataSource.setPassword(config.getProperty("connection.password"));
            dataSource.setMinPoolSize(1);
            dataSource.setMaxPoolSize(1);
        } catch (Exception ex) {
            throw new DatabaseConnectionException(ex.getMessage());
        }
    }

    @Override
    public Connection getConnection() throws DatabaseConnectionException {
        try {
            return dataSource.getConnection();
        } catch (Exception ex) {
            throw new DatabaseConnectionException(ex.getMessage());
        }
    }

}