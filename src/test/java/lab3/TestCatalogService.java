package lab3;

import lab3.model.Catalog;
import lab3.model.SmartPhone;
import lab3.service.CatalogService;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.Set;
import java.util.SortedMap;

public class TestCatalogService {

    private SmartPhone redmiNote7;
    private SmartPhone iphoneX;
    private SmartPhone redmi7;
    private SmartPhone samsungA30;

    private Catalog catalog;
    private CatalogService catalogService;

    {
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

        samsungA30 = new SmartPhone.Builder()
                .setName("Samsung Galaxy A30")
                .setDiagonal(6.4)
                .setColor(SmartPhone.Color.BLACK)
                .setRam(3072)
                .setReleaseDate(LocalDate.of(2019, 6, 15))
                .setPrice(5500)
                .build();
    }

    @BeforeMethod
    public void createCatalog() {
        catalog = new Catalog();
        catalog.addGoodsItem(redmiNote7, 15);
        catalog.addGoodsItem(iphoneX, 20);
        catalog.addGoodsItem(redmi7, 25);
        catalog.addGoodsItem(samsungA30, 30);
        catalogService = new CatalogService(catalog);
    }

    @Test
    public void searchByNameTest() {
        Catalog catalog = catalogService.searchGoodsByName("Xiaomi", false);

        Set<SmartPhone> actual = catalog.getSmartPhones();
        Set<Object> expected = Set.of(new Object[]{redmiNote7, redmi7});

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void sortByPriceAscendingTest() {
        SortedMap<SmartPhone, Integer> sortedGoods = catalogService.getGoodsSortedByPrice(true);

        Object[] actual = sortedGoods.keySet().toArray();
        Object[] expected = {redmi7, redmiNote7, samsungA30, iphoneX};

        Assert.assertEquals(actual, expected);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void sortByPriceDescendingTest() {
        SortedMap<SmartPhone, Integer> sortedGoods = catalogService.getGoodsSortedByPrice(false);

        Object[] actual = sortedGoods.keySet().toArray();
        Object[] expected = {iphoneX, samsungA30, redmiNote7, redmi7};

        Assert.assertEquals(actual, expected);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void sortByReleaseDateAscendingTest() {
        SortedMap<SmartPhone, Integer> sortedGoods = catalogService.getGoodsSortedByReleaseDate(true);

        Object[] actual = sortedGoods.keySet().toArray();
        Object[] expected = {iphoneX, redmi7, redmiNote7, samsungA30};

        Assert.assertEquals(actual, expected);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void searchByCriteriaTest() {
        Catalog resultCatalog1 = catalogService.searchGoodsByName("Xiaomi", false);
        Catalog resultCatalog2 = new CatalogService(resultCatalog1).searchGoodsWithColor(SmartPhone.Color.BLACK);

        Object[] actual = resultCatalog2.getSmartPhones().toArray();
        Object[] expected = {redmi7};

        Assert.assertEquals(actual, expected);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
