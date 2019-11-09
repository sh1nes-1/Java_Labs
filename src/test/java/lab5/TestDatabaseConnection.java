package lab5;

import lab5.exception.DatabaseConnectionException;
import lab5.service.connection.ConnectionBuilder;
import lab5.service.connection.ConnectionFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Connection;

public class TestDatabaseConnection {

    @Test
    public void databaseConnectionTest() throws DatabaseConnectionException {
        ConnectionBuilder connectionBuilder = ConnectionFactory.getConnectionBuilder();
        Connection connection = connectionBuilder.getConnection();
        Assert.assertNotNull(connection);
    }

}
