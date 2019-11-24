package lab5;

import lab5.connection.ConnectionFactory;
import lab5.dao.CatalogDao;
import lab5.dao.ShopDao;
import lab5.dao.SmartPhoneDao;
import lab5.dao.jdbc.CatalogDaoJdbc;
import lab5.dao.jdbc.ShopDaoJdbc;
import lab5.dao.jdbc.SmartPhoneDaoJdbc;
import lab5.exception.DaoException;
import lab5.exception.DatabaseConnectionException;
import lab5.model.Catalog;
import lab5.model.Shop;
import lab5.model.SmartPhone;
import lab5.utils.DatabaseStructure;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class TestCatalogDaoJdbc {

    private Connection connection;

    private CatalogDao catalogDao;
    private ShopDao shopDao;

    private Catalog mainCatalog;
    private Catalog blackFridayCatalog;

    @BeforeClass
    public void beforeTest() throws DatabaseConnectionException, SQLException, DaoException {
        // creating schema
        try {
            DatabaseStructure.dropTables();
        } catch (Exception ignored) {
        }
        DatabaseStructure.createTables();

        // creating connection
        connection = ConnectionFactory.getConnectionBuilder().getConnection();

        // smartphones
        SmartPhoneDao smartPhoneDao = new SmartPhoneDaoJdbc(connection);

        SmartPhone redmiNote7 = new SmartPhone.Builder()
                .setName("Xiaomi Redmi Note 7")
                .setDiagonal(6.3)
                .setColor(SmartPhone.Color.BLUE)
                .setRam(3072)
                .setReleaseDate(LocalDate.of(2019, 5, 1))
                .setPrice(4500)
                .build();

        SmartPhone iphoneX = new SmartPhone.Builder()
                .setName("iPhone X")
                .setDiagonal(5.8)
                .setColor(SmartPhone.Color.WHITE)
                .setRam(3072)
                .setReleaseDate(LocalDate.of(2017, 9, 12))
                .setPrice(20000)
                .build();

        SmartPhone redmi7 = new SmartPhone.Builder()
                .setName("Xiaomi Redmi 7")
                .setDiagonal(6.26)
                .setColor(SmartPhone.Color.BLACK)
                .setRam(2048)
                .setReleaseDate(LocalDate.of(2018, 8, 2))
                .setPrice(3600)
                .build();

        redmiNote7.setId(smartPhoneDao.insert(redmiNote7));
        iphoneX.setId(smartPhoneDao.insert(iphoneX));
        redmi7.setId(smartPhoneDao.insert(redmi7));

        // shop
        shopDao = new ShopDaoJdbc(connection);
        Shop shop = new Shop("Rozetka");
        shop.setId(shopDao.insert(shop));

        // catalog
        catalogDao = new CatalogDaoJdbc(connection);

        mainCatalog = new Catalog();
        mainCatalog.setName("Main Catalog");
        mainCatalog.setShop(shop);
        mainCatalog.setId(catalogDao.insert(mainCatalog));

        catalogDao.addSmartPhone(mainCatalog, redmi7, 1000, 10);
        catalogDao.addSmartPhone(mainCatalog, iphoneX, 2000, 5);

        blackFridayCatalog = new Catalog();
        blackFridayCatalog.setName("Black Friday");
        blackFridayCatalog.setShop(shop);
        blackFridayCatalog.setId(catalogDao.insert(blackFridayCatalog));

        catalogDao.addSmartPhone(blackFridayCatalog, redmiNote7, 800, 7);
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
    public void findCatalogByIdTest() throws DaoException {
        Optional<Catalog> result = catalogDao.findById(mainCatalog.getId());
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(result.get(), mainCatalog);
    }

    @Test
    public void negativeFindCatalogByIdTest() throws DaoException {
        Optional<Catalog> result = catalogDao.findById(-1L);
        Assert.assertFalse(result.isPresent());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void findCatalogByIdEagerTest() throws DaoException {
        Optional<Catalog> result = catalogDao.findByIdEager(mainCatalog.getId());
        Assert.assertTrue(result.isPresent());
        System.out.println(result.get());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void findAllCatalogsTest() throws DaoException {
        Set<Catalog> expected = new HashSet<>(Arrays.asList(mainCatalog, blackFridayCatalog));
        Set<Catalog> actual = new HashSet<>(catalogDao.findAll());
        Assert.assertEquals(actual, expected);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void insertCatalogTest() throws DaoException {
        Shop shop = new Shop("Some shop");
        shop.setImageUrl("newshop.jpg");
        shop.setId(shopDao.insert(shop));

        Catalog catalog = new Catalog();
        catalog.setName("Some catalog");
        catalog.setShop(shop);
        catalog.setId(catalogDao.insert(catalog));

        Set<Catalog> expected = new HashSet<>(Arrays.asList(mainCatalog, blackFridayCatalog, catalog));
        Set<Catalog> actual = new HashSet<>(catalogDao.findAll());
        Assert.assertEquals(actual, expected);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void updateCatalogTest() throws DaoException {
        String name = mainCatalog.getName();
        mainCatalog.setName("New Main Catalog");
        catalogDao.update(mainCatalog);

        Optional<Catalog> actual = catalogDao.findById(mainCatalog.getId());
        Assert.assertTrue(actual.isPresent());
        Assert.assertEquals(actual.get(), mainCatalog);
        mainCatalog.setName(name);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void deleteCatalogTest() throws DaoException {
        catalogDao.delete(mainCatalog.getId());

        Set<Catalog> expected = new HashSet<>(Collections.singletonList(blackFridayCatalog));
        Set<Catalog> actual = new HashSet<>(catalogDao.findAll());
        Assert.assertEquals(actual, expected);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


}
