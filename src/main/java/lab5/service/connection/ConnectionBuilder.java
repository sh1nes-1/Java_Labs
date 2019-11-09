package lab5.service.connection;

import lab5.exception.DatabaseConnectionException;
import java.sql.Connection;

public interface ConnectionBuilder {

    Connection getConnection() throws DatabaseConnectionException;

}