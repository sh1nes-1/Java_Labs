package lab5;

import lab5.service.connection.ConnectionBuilder;
import lab5.service.connection.ConnectionFactory;
import lab5.service.connection.exception.DatabaseConnectionException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class TestDatabaseConnection {

    @Test
    public void databaseConnectionTest() throws DatabaseConnectionException {
        ConnectionBuilder connectionBuilder = ConnectionFactory.getConnectionBuilder();
        Connection connection = connectionBuilder.getConnection();
        Assert.assertNotNull(connection);
    }

}
