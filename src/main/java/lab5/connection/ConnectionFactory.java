package lab5.connection;

import lab5.exception.DatabaseConnectionException;
import lab5.connection.builder.SimpleConnectionBuilder;

public class ConnectionFactory {

    public static ConnectionBuilder getConnectionBuilder() throws DatabaseConnectionException {
        return new SimpleConnectionBuilder();
    }

}
