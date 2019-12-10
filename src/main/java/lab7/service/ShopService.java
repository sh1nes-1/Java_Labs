package lab7.service;

import lab7.connection.ConnectionFactory;
import lab7.dao.ShopDao;
import lab7.dao.jdbc.ShopDaoJdbc;
import lab7.dto.ShopDTO;
import lab7.exception.DaoException;
import lab7.exception.DatabaseConnectionException;
import lab7.exception.ServiceException;
import lab7.mapper.ShopMapper;
import lab7.model.Catalog;
import lab7.model.Shop;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ShopService {

    private ShopDao shopDao;

    public ShopService() throws ServiceException {
        try {
            Connection connection = ConnectionFactory.getConnectionBuilder().getConnection();
            shopDao = new ShopDaoJdbc(connection);
        } catch (DatabaseConnectionException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public Optional<ShopDTO> findById(Long id) throws ServiceException {
        try {
            Optional<ShopDTO> result = Optional.empty();
            Optional<Shop> optionalShop = shopDao.findById(id);
            if (optionalShop.isPresent()) {
                result = Optional.of(ShopMapper.INSTANCE.getDto(optionalShop.get()));
            }
            return result;
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public List<ShopDTO> findAll() throws ServiceException {
        try {
            return ShopMapper.INSTANCE.getDtoList(shopDao.findAll());
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public Long insert(ShopDTO shopDto) throws ServiceException {
        try {
            Shop shop = ShopMapper.INSTANCE.getShop(shopDto);
            return shopDao.insert(shop);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public int update(ShopDTO shopDto) throws ServiceException {
        try {
            Shop shop = ShopMapper.INSTANCE.getShop(shopDto);
            return shopDao.update(shop);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public int delete(Long id) throws ServiceException {
        try {
            return shopDao.delete(id);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public int addCatalog(ShopDTO shopDto, Catalog catalog) throws ServiceException {
        try {
            Shop shop = ShopMapper.INSTANCE.getShop(shopDto);
            int result = shopDao.addCatalog(shop, catalog);
            if (result > 0) shop.addCatalog(catalog);
            return result;
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public int removeCatalog(ShopDTO shopDto, Catalog catalog) throws ServiceException {
        try {
            Shop shop = ShopMapper.INSTANCE.getShop(shopDto);
            int result = shopDao.removeCatalog(shop, catalog);
            if (result > 0) shop.removeCatalog(catalog);
            return result;
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public Set<Catalog> getCatalogs(ShopDTO shopDto) throws ServiceException {
        try {
            Shop shop = ShopMapper.INSTANCE.getShop(shopDto);
            return shopDao.getCatalogs(shop);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
}
