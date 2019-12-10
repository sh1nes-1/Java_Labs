package lab7.service;

import lab7.connection.ConnectionFactory;
import lab7.dao.CatalogDao;
import lab7.dao.jdbc.CatalogDaoJdbc;
import lab7.dto.CatalogDTO;
import lab7.dto.ShopDTO;
import lab7.exception.DaoException;
import lab7.exception.DatabaseConnectionException;
import lab7.exception.ServiceException;
import lab7.mapper.CatalogMapper;
import lab7.mapper.ShopMapper;
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

    public Optional<CatalogDTO> findById(Long id) throws ServiceException {
        try {
            Optional<CatalogDTO> result = Optional.empty();
            Optional<Catalog> optionalCatalog = catalogDao.findById(id);
            if (optionalCatalog.isPresent()) {
                result = Optional.of(CatalogMapper.INSTANCE.getDto(optionalCatalog.get()));
            }
            return result;
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public List<CatalogDTO> findAll() throws ServiceException {
        try {
            return CatalogMapper.INSTANCE.getDtoList(catalogDao.findAll());
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public Long insert(CatalogDTO catalogDto) throws ServiceException {
        try {
            Catalog catalog = CatalogMapper.INSTANCE.getCatalog(catalogDto);
            return catalogDao.insert(catalog);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public int update(CatalogDTO catalogDto) throws ServiceException {
        try {
            Catalog catalog = CatalogMapper.INSTANCE.getCatalog(catalogDto);
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

    public Optional<CatalogDTO> findByIdEager(Long id) throws ServiceException {
        try {
            Optional<CatalogDTO> result = Optional.empty();
            Optional<Catalog> optionalCatalog = catalogDao.findByIdEager(id);
            if (optionalCatalog.isPresent()) {
                result = Optional.of(CatalogMapper.INSTANCE.getDto(optionalCatalog.get()));
            }
            return result;
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public Optional<Shop> getShop(CatalogDTO catalogDto) throws ServiceException {
        try {
            Catalog catalog = CatalogMapper.INSTANCE.getCatalog(catalogDto);
            return catalogDao.getShop(catalog);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public Integer getSmartPhonePrice(CatalogDTO catalogDto, SmartPhone smartPhone) throws ServiceException {
        try {
            Catalog catalog = CatalogMapper.INSTANCE.getCatalog(catalogDto);
            return catalogDao.getSmartPhonePrice(catalog, smartPhone);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public Integer getSmartPhoneCount(CatalogDTO catalogDto, SmartPhone smartPhone) throws ServiceException {
        try {
            Catalog catalog = CatalogMapper.INSTANCE.getCatalog(catalogDto);
            return catalogDao.getSmartPhoneCount(catalog, smartPhone);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public int addSmartPhone(CatalogDTO catalogDto, SmartPhone smartPhone, Integer price, Integer count) throws ServiceException {
        try {
            Catalog catalog = CatalogMapper.INSTANCE.getCatalog(catalogDto);
            int result = catalogDao.addSmartPhone(catalog, smartPhone, price, count);
            if (result > 0) catalog.addSmartPhone(smartPhone, price, count);
            return result;
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public int removeSmartPhone(CatalogDTO catalogDto, SmartPhone smartPhone) throws ServiceException {
        try {
            Catalog catalog = CatalogMapper.INSTANCE.getCatalog(catalogDto);
            int result = catalogDao.removeSmartPhone(catalog, smartPhone);
            if (result > 0) catalog.removeSmartPhone(smartPhone);
            return result;
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public int changeSmartPhonePrice(CatalogDTO catalogDto, SmartPhone smartPhone, Integer newPrice) throws ServiceException {
        try {
            Catalog catalog = CatalogMapper.INSTANCE.getCatalog(catalogDto);
            int result = catalogDao.changeSmartPhonePrice(catalog, smartPhone, newPrice);
            if (result > 0) catalog.getSmartPhoneInfo(smartPhone).ifPresent(catalogItem -> catalogItem.setPrice(newPrice));
            return result;
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public int changeSmartPhoneCount(CatalogDTO catalogDto, SmartPhone smartPhone, Integer newCount) throws ServiceException {
        try {
            Catalog catalog = CatalogMapper.INSTANCE.getCatalog(catalogDto);
            int result = catalogDao.changeSmartPhoneCount(catalog, smartPhone, newCount);
            if (result > 0) catalog.getSmartPhoneInfo(smartPhone).ifPresent(catalogItem -> catalogItem.setCount(newCount));
            return result;
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
}
