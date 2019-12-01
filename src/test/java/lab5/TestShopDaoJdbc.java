package lab5;

import lab5.connection.ConnectionFactory;
import lab5.dao.CatalogDao;
import lab5.dao.Dao;
import lab5.dao.ShopDao;
import lab5.dao.jdbc.CatalogDaoJdbc;
import lab5.dao.jdbc.ShopDaoJdbc;
import lab5.exception.DaoException;
import lab5.exception.DatabaseConnectionException;
import lab5.model.Catalog;
import lab5.model.Shop;
import lab5.utils.DatabaseStructure;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.Optional;

public class TestShopDaoJdbc {
    private Connection connection;

    private ShopDao shopDao;

    private Shop rozetka;
    private Shop allo;
    private Shop multimedia;

    @BeforeClass
    public void beforeTest() throws DatabaseConnectionException, SQLException, DaoException {
        // creating connection
        connection = ConnectionFactory.getTestConnectionBuilder().getConnection();

        // creating schema
        try {
            DatabaseStructure.dropTables(connection);
        } catch (Exception ignored) {
        }
        DatabaseStructure.createTables(connection);

        // shop
        shopDao = new ShopDaoJdbc(connection);

        rozetka = new Shop("Rozetka");
        rozetka.setImageUrl("1.jpg");
        rozetka.setId(shopDao.insert(rozetka));

        allo = new Shop("Allo");
        allo.setImageUrl("2.jpg");
        allo.setId(shopDao.insert(allo));

        multimedia = new Shop("Multimedia");
        multimedia.setImageUrl("3.jpg");
        multimedia.setId(shopDao.insert(multimedia));
    }

    @AfterClass
    public void afterTest() throws SQLException {
        connection.close();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @BeforeMethod
    public void beforeMethod() throws SQLException {
        connection.setAutoCommit(false);
    }

    @AfterMethod
    public void afterMethod() throws SQLException {
        connection.rollback();
        connection.setAutoCommit(true);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void findShopByIdTest() throws DaoException {
        Optional<Shop> actual = shopDao.findById(rozetka.getId());
        Assert.assertTrue(actual.isPresent());
        Assert.assertEquals(actual.get(), rozetka);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void findAllShopsTest() throws DaoException {
        Set<Shop> expected = new HashSet<>(Arrays.asList(rozetka, allo, multimedia));
        Set<Shop> actual = new HashSet<>(shopDao.findAll());
        Assert.assertEquals(actual, expected);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void insertShopTest() throws DaoException {
        Shop shop = new Shop("New Shop");
        shop.setImageUrl("newshop.jpg");
        shop.setId(shopDao.insert(shop));

        Set<Shop> expected = new HashSet<>(Arrays.asList(rozetka, allo, multimedia, shop));
        Set<Shop> actual = new HashSet<>(shopDao.findAll());
        Assert.assertEquals(actual, expected);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void updateShopTest() throws DaoException {
        String name = rozetka.getName();
        String imageUrl = rozetka.getImageUrl();

        rozetka.setName("New Rozetka Name");
        rozetka.setImageUrl("newimage.jpg");
        shopDao.update(rozetka);

        Optional<Shop> actual = shopDao.findById(rozetka.getId());
        Assert.assertTrue(actual.isPresent());
        Assert.assertEquals(actual.get(), rozetka);

        rozetka.setName(name);
        rozetka.setImageUrl(imageUrl);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void deleteShopTest() throws DaoException {
        shopDao.delete(rozetka.getId());

        Set<Shop> expected = new HashSet<>(Arrays.asList(allo, multimedia));
        Set<Shop> actual = new HashSet<>(shopDao.findAll());
        Assert.assertEquals(actual, expected);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void addCatalogTest() throws DaoException {
        Catalog catalog = new Catalog();
        catalog.setName("Main");
        CatalogDao catalogDao = new CatalogDaoJdbc(connection);
        catalog.setId(catalogDao.insert(catalog));

        shopDao.addCatalog(rozetka, catalog);

        Object[] actual = shopDao.getCatalogs(rozetka).toArray();
        Object[] expected = { catalog };

        Assert.assertEqualsNoOrder(actual, expected);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void removeCatalogTest() throws DaoException {
        Catalog catalog = new Catalog();
        catalog.setName("Main");
        CatalogDao catalogDao = new CatalogDaoJdbc(connection);
        catalog.setId(catalogDao.insert(catalog));

        shopDao.addCatalog(rozetka, catalog);
        int c = shopDao.removeCatalog(rozetka, catalog);
        Assert.assertEquals(c, 1);

        Object[] actual = shopDao.getCatalogs(rozetka).toArray();
        Object[] expected = { };

        Assert.assertEqualsNoOrder(actual, expected);
    }

}
