package lab5.service;

import lab5.exception.DatabaseConnectionException;
import lab5.service.connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static java.util.Objects.isNull;

public class CreateTables {

    private static final String SQL_CREATE_TABLE_SMARTPHONES = "CREATE TABLE smartphones (id integer NOT NULL PRIMARY KEY,name VARCHAR(100) NOT NULL,price integer NOT NULL,releaseDate DATE NOT NULL,color VARCHAR(15) NOT NULL,ram integer NOT NULL,diagonal FLOAT NOT NULL);";
    private static final String SQL_DROP_TABLE_SMARTPHONES = "DROP TABLE smartphones";

    private static final String SQL_CREATE_TABLE_SHOPS = "CREATE TABLE shops (id integer NOT NULL PRIMARY KEY,name integer NOT NULL);";
    private static final String SQL_DROP_TABLE_SHOPS = "DROP TABLE shops";

    private static final String SQL_CREATE_TABLE_SMARTPHONES_SHOPS = "CREATE TABLE smartphones_shops (id integer NOT NULL PRIMARY KEY,smartphone_id integer NOT NULL REFERENCES smartphones(id),shop_id integer NOT NULL REFERENCES shops(id));";
    private static final String SQL_DROP_TABLE_SMARTPHONES_SHOPS = "DROP TABLE smartphones_shops";


    private static Connection cachedConnection;


    public static Connection getConnection() throws DatabaseConnectionException {
        return isNull(cachedConnection) ? ConnectionFactory.getConnectionBuilder().getConnection() : cachedConnection;
    }

    public static void createSmartphonesTable() throws DatabaseConnectionException, SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(SQL_CREATE_TABLE_SMARTPHONES);
    }

    public static void createShopsTable() throws DatabaseConnectionException, SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(SQL_CREATE_TABLE_SHOPS);
    }

    public static void createSmartphonesShopsTable() throws DatabaseConnectionException, SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(SQL_CREATE_TABLE_SMARTPHONES_SHOPS);
    }


    private static void dropTables() throws DatabaseConnectionException, SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(SQL_DROP_TABLE_SMARTPHONES_SHOPS);
        statement.executeUpdate(SQL_DROP_TABLE_SMARTPHONES);
        statement.executeUpdate(SQL_DROP_TABLE_SHOPS);
    }


    public static void main(String[] args) throws DatabaseConnectionException, SQLException {
        dropTables();

        createSmartphonesTable();
        createShopsTable();
        createSmartphonesShopsTable();
    }

}
