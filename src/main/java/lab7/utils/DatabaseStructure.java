package lab7.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseStructure {

    private static final String CREATE_SMARTPHONES = "CREATE TABLE smartphones (id SERIAL NOT NULL PRIMARY KEY,name VARCHAR(100) NOT NULL,price integer NOT NULL,releaseDate DATE NOT NULL,color VARCHAR(15) NOT NULL,ram integer NOT NULL,diagonal FLOAT NOT NULL)";
    private static final String DROP_SMARTPHONES = "DROP TABLE smartphones";

    private static final String CREATE_CATALOGS = "CREATE TABLE catalogs (id SERIAL NOT NULL PRIMARY KEY,name VARCHAR(30) NOT NULL,shop_id INTEGER REFERENCES shops(id))";
    private static final String DROP_CATALOGS = "DROP TABLE catalogs";

    private static final String CREATE_SHOPS = "CREATE TABLE shops (id SERIAL NOT NULL PRIMARY KEY,name VARCHAR(30) NOT NULL,image VARCHAR(30))";
    private static final String DROP_SHOPS = "DROP TABLE shops";

    private static final String CREATE_CATALOG_ITEMS = "CREATE TABLE catalog_items (catalog_id integer NOT NULL REFERENCES catalogs(id) ON DELETE CASCADE,smartphone_id integer NOT NULL REFERENCES smartphones(id),smartphone_price integer NOT NULL,smartphone_count integer NOT NULL)";
    private static final String DROP_CATALOG_ITEMS = "DROP TABLE catalog_items";

    public static void createTables(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(CREATE_SMARTPHONES);
        statement.executeUpdate(CREATE_SHOPS);
        statement.executeUpdate(CREATE_CATALOGS);
        statement.executeUpdate(CREATE_CATALOG_ITEMS);
    }

    public static void dropTables(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(DROP_CATALOG_ITEMS);
        statement.executeUpdate(DROP_CATALOGS);
        statement.executeUpdate(DROP_SHOPS);
        statement.executeUpdate(DROP_SMARTPHONES);
    }


}
