package lab5.connection;

import lab5.exception.DatabaseConnectionException;

import java.sql.Connection;

public interface ConnectionBuilder {

    Connection getConnection() throws DatabaseConnectionException;

}