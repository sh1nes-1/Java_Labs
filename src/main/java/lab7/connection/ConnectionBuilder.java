package lab7.connection;

import lab7.exception.DatabaseConnectionException;

import java.sql.Connection;

public interface ConnectionBuilder {

    Connection getConnection() throws DatabaseConnectionException;

}