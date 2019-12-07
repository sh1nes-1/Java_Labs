package lab7.connection;

import lab7.connection.builder.SimpleConnectionBuilder;
import lab7.connection.builder.TestConnectionBuilder;
import lab7.exception.DatabaseConnectionException;

public class ConnectionFactory {

    public static ConnectionBuilder getConnectionBuilder() throws DatabaseConnectionException {
        return new SimpleConnectionBuilder();
    }

    public static ConnectionBuilder getTestConnectionBuilder() throws DatabaseConnectionException {
        return new TestConnectionBuilder();
    }

}
