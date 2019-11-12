package lab5.service.connection.builder;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import lab5.exception.DatabaseConnectionException;
import lab5.service.GlobalConfig;
import lab5.service.connection.ConnectionBuilder;

import java.sql.Connection;

public class ComboPooledConnectionBuilder implements ConnectionBuilder {

    private ComboPooledDataSource dataSource;

    public ComboPooledConnectionBuilder() throws DatabaseConnectionException {
        try {
            GlobalConfig.loadGlobalConfig("src/main/resources/database.properties");

            dataSource = new ComboPooledDataSource();
            dataSource.setDriverClass(GlobalConfig.getProperty("connection.driver.class"));
            dataSource.setJdbcUrl(GlobalConfig.getProperty("connection.url"));
            dataSource.setUser(GlobalConfig.getProperty("connection.login"));
            dataSource.setPassword(GlobalConfig.getProperty("connection.password"));
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