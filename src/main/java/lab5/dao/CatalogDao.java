package lab5.dao;

import lab5.exception.DaoException;
import lab5.model.Catalog;
import lab5.model.SmartPhone;

import java.util.Optional;

public interface CatalogDao extends Dao<Catalog> {
    // отриимати всі каталоги разом з Set всередині за доп одного sql запиту
    // забрати id з таблиці catalog_items
    // додати айтем
    // видалити айтем
    // змінити кількість смартфонів в CatalogItem тут якщо заберу id

    /**
     * Finds needed catalog by id
     *
     * @param id id of needed catalog
     * @return catalog with all filled fields
     */
    Optional<Catalog> findByIdEager(Long id) throws DaoException;

    /**
     * Adds smartphone to catalog
     *
     * @param catalog catalog to which add
     * @param smartPhone smartphone which add
     * @param price price of one smartphone
     * @param count count of smartphones
     */
    int addSmartPhone(Catalog catalog, SmartPhone smartPhone, Integer price, Integer count) throws DaoException;

    /**
     * Removes smartphone from catalog
     *
     * @param catalog catalog from which remove
     * @param smartPhone smartphone which remove
     */
    int removeSmartPhone(Catalog catalog, SmartPhone smartPhone) throws DaoException;

    int changeSmartPhonePrice(Catalog catalog, SmartPhone smartPhone, Integer newPrice) throws DaoException;

    int changeSmartPhoneCount(Catalog catalog, SmartPhone smartPhone, Integer newCount) throws DaoException;

}
