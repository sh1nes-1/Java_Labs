package lab5;

import lab5.dao.SmartPhoneDao;
import lab5.exception.DaoException;
import lab5.model.SmartPhone;
import lab5.dao.jdbc.SmartPhoneDaoJdbc;
import lab5.exception.DatabaseConnectionException;
import lab5.connection.ConnectionFactory;
import lab5.utils.DatabaseStructure;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.Optional;

public class TestSmartPhoneDaoJdbc {

    private Connection connection;
    private SmartPhoneDao smartPhoneDao;

    private SmartPhone redmiNote7;
    private SmartPhone iphoneX;
    private SmartPhone redmi7;

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

        smartPhoneDao = new SmartPhoneDaoJdbc(connection);

        // inserting data
        redmiNote7 = new SmartPhone.Builder()
                .setName("Xiaomi Redmi Note 7")
                .setDiagonal(6.3)
                .setColor(SmartPhone.Color.BLUE)
                .setRam(3072)
                .setReleaseDate(LocalDate.of(2019, 5, 1))
                .setPrice(4500)
                .build();

        iphoneX = new SmartPhone.Builder()
                .setName("iPhone X")
                .setDiagonal(5.8)
                .setColor(SmartPhone.Color.WHITE)
                .setRam(3072)
                .setReleaseDate(LocalDate.of(2017, 9, 12))
                .setPrice(20000)
                .build();

        redmi7 = new SmartPhone.Builder()
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
    public void findSmartPhoneByIdTest() throws DaoException {
        Optional<SmartPhone> result = smartPhoneDao.findById(redmiNote7.getId());
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(result.get(), redmiNote7);
    }

    @Test
    public void negativeFindSmartPhoneByIdTest() throws DaoException {
        Optional<SmartPhone> result = smartPhoneDao.findById(-1L);
        Assert.assertFalse(result.isPresent());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void findAllSmartPhonesTest() throws DaoException {
        Set<SmartPhone> expected = new HashSet<>(Arrays.asList(redmiNote7, iphoneX, redmi7));
        Set<SmartPhone> actual =  new HashSet<>(smartPhoneDao.findAll());
        Assert.assertEquals(actual, expected);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void insertSmartPhoneTest() throws DaoException {
        SmartPhone samsungA30 = new SmartPhone.Builder()
                .setName("Samsung Galaxy A30")
                .setDiagonal(6.4)
                .setColor(SmartPhone.Color.BLACK)
                .setRam(3072)
                .setReleaseDate(LocalDate.of(2019, 6, 15))
                .setPrice(5500)
                .build();

        smartPhoneDao.insert(samsungA30);

        Set<SmartPhone> expected = new HashSet<>(Arrays.asList(redmiNote7, iphoneX, redmi7, samsungA30));
        Set<SmartPhone> actual = new HashSet<>(smartPhoneDao.findAll());
        Assert.assertEquals(actual, expected);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void updateSmartPhoneTest() throws DaoException {
        String name = redmiNote7.getName();

        redmiNote7.setName("New Redmi Note 7");

        smartPhoneDao.update(redmiNote7);
        Optional<SmartPhone> actual = smartPhoneDao.findById(redmiNote7.getId());

        Assert.assertTrue(actual.isPresent());
        Assert.assertEquals(actual.get(), redmiNote7);

        redmiNote7.setName(name);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void deleteSmartPhoneTest() throws DaoException {
        smartPhoneDao.delete(redmiNote7.getId());

        Set<SmartPhone> expected = new HashSet<>(Arrays.asList(iphoneX, redmi7));
        Set<SmartPhone> actual =  new HashSet<>(smartPhoneDao.findAll());

        Assert.assertEquals(actual, expected);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void findByNameTest() throws DaoException {
        Set<SmartPhone> expected = new HashSet<>(Arrays.asList(redmi7, redmiNote7));
        Set<SmartPhone> actual = new HashSet<>(smartPhoneDao.findAllWithNameLike("Xiaomi%"));
        Assert.assertEquals(actual, expected);
    }

}
