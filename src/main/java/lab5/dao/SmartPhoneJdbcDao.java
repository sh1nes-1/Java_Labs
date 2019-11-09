package lab5.dao;

import lab5.exception.DaoException;
import lab5.exception.DatabaseConnectionException;
import lab5.model.SmartPhone;
import lab5.service.connection.ConnectionFactory;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class SmartPhoneJdbcDao implements Dao<SmartPhone> {

    private Connection connection;

    public SmartPhoneJdbcDao() throws DatabaseConnectionException {
        connection = ConnectionFactory.getConnectionBuilder().getConnection();
    }

    @Override
    public Optional<SmartPhone> findById(int id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public List<SmartPhone> findAll() throws DaoException {
        return null;
    }

    @Override
    public boolean insert(SmartPhone smartPhone) throws DaoException {
        return false;
    }

    @Override
    public boolean update(SmartPhone smartPhone) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(SmartPhone smartPhone) throws DaoException {
        return false;
    }

}
