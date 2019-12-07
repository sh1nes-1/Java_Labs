package lab7.service;

import lab7.connection.ConnectionFactory;
import lab7.dao.SmartPhoneDao;
import lab7.dao.jdbc.SmartPhoneDaoJdbc;
import lab7.exception.DaoException;
import lab7.exception.DatabaseConnectionException;
import lab7.exception.ServiceException;
import lab7.model.SmartPhone;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class SmartPhoneService {

    private SmartPhoneDao smartPhoneDao;

    public SmartPhoneService() throws ServiceException {
        try {
            Connection connection = ConnectionFactory.getConnectionBuilder().getConnection();
            smartPhoneDao = new SmartPhoneDaoJdbc(connection);
        } catch (DatabaseConnectionException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public Optional<SmartPhone> findById(Long id) throws ServiceException {
        try {
            return smartPhoneDao.findById(id);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public List<SmartPhone> findAll() throws ServiceException {
        try {
            return smartPhoneDao.findAll();
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public Long insert(SmartPhone smartPhone) throws ServiceException {
        try {
            return smartPhoneDao.insert(smartPhone);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public int update(SmartPhone smartPhone) throws ServiceException {
        try {
            return smartPhoneDao.update(smartPhone);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public int delete(Long id) throws ServiceException {
        try {
            return smartPhoneDao.delete(id);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public List<SmartPhone> findAllWithNameLike(String name) throws ServiceException {
        try {
            return smartPhoneDao.findAllWithNameLike(name);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
}
