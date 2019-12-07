package lab7.dao;

import lab7.exception.DaoException;
import lab7.model.Catalog;
import lab7.model.Shop;

import java.util.Set;

public interface ShopDao extends Dao<Shop> {

    int addCatalog(Shop shop, Catalog catalog) throws DaoException;

    int removeCatalog(Shop shop, Catalog catalog) throws DaoException;

    Set<Catalog> getCatalogs(Shop shop) throws DaoException;

}
