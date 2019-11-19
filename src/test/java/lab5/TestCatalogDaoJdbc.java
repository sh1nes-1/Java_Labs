package lab5;

import lab5.connection.ConnectionFactory;
import lab5.dao.CatalogDao;
import lab5.dao.jdbc.CatalogDaoJdbc;
import lab5.exception.DaoException;
import lab5.exception.DatabaseConnectionException;
import lab5.utils.DatabaseStructure;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.sql.Connection;
import java.sql.SQLException;

public class TestCatalogDaoJdbc {

    private Connection connection;
    private CatalogDao catalogDao;

    @BeforeTest
    public void beforeTest() throws DatabaseConnectionException, SQLException, DaoException {
        // creating schema
        try {
            DatabaseStructure.dropTables();
        } catch (Exception ignored) {
        }
        DatabaseStructure.createTables();

        // creating connection
        connection = ConnectionFactory.getConnectionBuilder().getConnection();
        catalogDao = new CatalogDaoJdbc(connection);
    }

    @AfterTest
    public void afterTest() throws SQLException {
        connection.close();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @BeforeMethod
    public void beforeMethod() throws SQLException {
        connection.setAutoCommit(false);
    }

    @AfterMethod
    public void afterMethod() throws SQLException {
        connection.rollback();
        connection.setAutoCommit(true);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
