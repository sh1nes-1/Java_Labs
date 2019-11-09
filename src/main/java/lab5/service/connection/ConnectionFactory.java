package lab5.service.connection;

import lab5.service.connection.builder.SimpleConnectionBuilder;
import lab5.exception.DatabaseConnectionException;

public class ConnectionFactory {

    public static ConnectionBuilder getConnectionBuilder() throws DatabaseConnectionException {
        return new SimpleConnectionBuilder();
    }

}
