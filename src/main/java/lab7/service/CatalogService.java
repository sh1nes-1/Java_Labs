package lab7.service;

import lab7.connection.ConnectionFactory;
import lab7.dao.CatalogDao;
import lab7.dao.jdbc.CatalogDaoJdbc;
import lab7.exception.DaoException;
import lab7.exception.DatabaseConnectionException;
import lab7.exception.ServiceException;
import lab7.model.Catalog;
import lab7.model.Shop;
import lab7.model.SmartPhone;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class CatalogService {

    private CatalogDao catalogDao;

    public CatalogService() throws ServiceException {
        try {
            Connection connection = ConnectionFactory.getConnectionBuilder().getConnection();
            catalogDao = new CatalogDaoJdbc(connection);
        } catch (DatabaseConnectionException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public Optional<Catalog> findById(Long id) throws ServiceException {
        try {
            return catalogDao.findById(id);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public List<Catalog> findAll() throws ServiceException {
        try {
            return catalogDao.findAll();
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public Long insert(Catalog catalog) throws ServiceException {
        try {
            return catalogDao.insert(catalog);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public int update(Catalog catalog) throws ServiceException {
        try {
            return catalogDao.update(catalog);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public int delete(Long id) throws ServiceException {
        try {
            return catalogDao.delete(id);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public Optional<Catalog> findByIdEager(Long id) throws ServiceException {
        try {
            return catalogDao.findByIdEager(id);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public Optional<Shop> getShop(Catalog catalog) throws ServiceException {
        try {
            return catalogDao.getShop(catalog);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public Integer getSmartPhonePrice(Catalog catalog, SmartPhone smartPhone) throws ServiceException {
        try {
            return catalogDao.getSmartPhonePrice(catalog, smartPhone);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public Integer getSmartPhoneCount(Catalog catalog, SmartPhone smartPhone) throws ServiceException {
        try {
            return catalogDao.getSmartPhoneCount(catalog, smartPhone);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public int addSmartPhone(Catalog catalog, SmartPhone smartPhone, Integer price, Integer count) throws ServiceException {
        try {
            int result = catalogDao.addSmartPhone(catalog, smartPhone, price, count);
            if (result > 0) catalog.addSmartPhone(smartPhone, price, count);
            return result;
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public int removeSmartPhone(Catalog catalog, SmartPhone smartPhone) throws ServiceException {
        try {
            int result = catalogDao.removeSmartPhone(catalog, smartPhone);
            if (result > 0) catalog.removeSmartPhone(smartPhone);
            return result;
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public int changeSmartPhonePrice(Catalog catalog, SmartPhone smartPhone, Integer newPrice) throws ServiceException {
        try {
            int result = catalogDao.changeSmartPhonePrice(catalog, smartPhone, newPrice);
            if (result > 0) catalog.getSmartPhoneInfo(smartPhone).ifPresent(catalogItem -> catalogItem.setPrice(newPrice));
            return result;
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public int changeSmartPhoneCount(Catalog catalog, SmartPhone smartPhone, Integer newCount) throws ServiceException {
        try {
            int result = catalogDao.changeSmartPhoneCount(catalog, smartPhone, newCount);
            if (result > 0) catalog.getSmartPhoneInfo(smartPhone).ifPresent(catalogItem -> catalogItem.setCount(newCount));
            return result;
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
}
