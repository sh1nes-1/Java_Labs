package lab5.dao;

import lab5.exception.DaoException;
import lab5.model.Catalog;
import lab5.model.Shop;

import java.util.Set;

public interface ShopDao extends Dao<Shop> {

    int addCatalog(Shop shop, Catalog catalog) throws DaoException;

    int removeCatalog(Shop shop, Catalog catalog) throws DaoException;

    Set<Catalog> getCatalogs(Shop shop) throws DaoException;

}
