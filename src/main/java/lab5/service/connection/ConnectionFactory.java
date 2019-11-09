package lab5.service.connection;

import lab5.exception.DatabaseConnectionException;
import lab5.service.connection.builder.SimpleConnectionBuilder;

public class ConnectionFactory {

    public static ConnectionBuilder getConnectionBuilder() throws DatabaseConnectionException {
        return new SimpleConnectionBuilder();
    }

}
