package lab1;

import lab1.model.Catalog;
import lab1.model.SmartPhone;
import lab1.service.CatalogService;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TestCatalog {

    Catalog smartPhonesCatalog;

    @BeforeTest()
    public void createCatalog() {
        Set<SmartPhone> smartPhones = new HashSet<>();

        SmartPhone redmiNote7 = new SmartPhone.Builder()
                .setName("Xiaomi Redmi Note 7")
                .setDiagonal(6.3)
                .setColor(SmartPhone.Color.BLUE)
                .setRam(3072)
                .setReleaseDate(LocalDate.of(2019, 5, 1))
                .setPrice(4500)
                .build();
        smartPhones.add(redmiNote7);

        SmartPhone iphoneX = new SmartPhone.Builder()
                .setName("iPhone X")
                .setDiagonal(5.8)
                .setColor(SmartPhone.Color.WHITE)
                .setRam(3072)
                .setReleaseDate(LocalDate.of(2017, 9, 12))
                .setPrice(20000)
                .build();
        smartPhones.add(iphoneX);

        SmartPhone redmi7 = new SmartPhone.Builder()
                .setName("Xiaomi Redmi 7")
                .setDiagonal(6.26)
                .setColor(SmartPhone.Color.BLACK)
                .setRam(2048)
                .setReleaseDate(LocalDate.of(2018, 8, 2))
                .setPrice(3600)
                .build();
        smartPhones.add(redmi7);

        SmartPhone samsungA30 = new SmartPhone.Builder()
                .setName("Samsung Galaxy A30")
                .setDiagonal(6.4)
                .setColor(SmartPhone.Color.BLACK)
                .setRam(3072)
                .setReleaseDate(LocalDate.of(2019, 6, 15))
                .setPrice(5500)
                .build();
        smartPhones.add(samsungA30);

        for (SmartPhone x : smartPhones) {
            smartPhonesCatalog.addGoodsItem(x, 1 + (int)(Math.random() * 100));
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                         Catalog Tests                                                          //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void increaseCountTest() {
        SmartPhone smartPhone = (SmartPhone) catalog.getGoods().keySet().toArray()[0];
        int count = catalog.getGoods().get(smartPhone) + 5;
        catalog.addItemCount(smartPhone, 5);
        Assert.assertEquals(catalog.getGoods().get(smartPhone).intValue(),count);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                      CatalogService Tests                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void sortByPriceAscendingTest() {
        System.out.println("Sort by price ascending result: ");

        Integer prev = null;
        for (Map.Entry<SmartPhone, Integer> x : new CatalogService(smartPhonesCatalog).getGoodsSortedByPrice(true).entrySet()) {
            System.out.println(x.getKey());

            if (prev == null) {
                prev = x.getKey().getPrice();
            }
            else {
                Assert.assertTrue(x.getKey().getPrice() >= prev);
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void sortByPriceDescendingTest() {
        System.out.println("Sort by price descending result: ");

        Integer prev = null;
        for (Map.Entry<SmartPhone, Integer> x : new CatalogService(smartPhonesCatalog).getGoodsSortedByPrice(false).entrySet()) {
            System.out.println(x.getKey());

            if (prev == null) {
                prev = x.getKey().getPrice();
            }


            else {
                Assert.assertTrue(x.getKey().getPrice() <= prev);
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void sortByReleaseDateAscendingTest() {
        System.out.println("Sort by release date ascending result: ");

        LocalDate prev = null;
        for (Map.Entry<SmartPhone, Integer> x : new CatalogService(smartPhonesCatalog).getGoodsSortedByReleaseDate(true).entrySet()) {
            System.out.println(x.getKey());

            if (prev == null) {
                prev = x.getKey().getReleaseDate();
            }
            else {
                Assert.assertTrue(x.getKey().getReleaseDate().compareTo(prev) > 0);
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void searchByCriteriaTest() {
        Catalog searchResult1 = new CatalogService(smartPhonesCatalog).searchGoodsByName("Xiaomi", false);
        Catalog searchResult2 = new CatalogService(searchResult1).searchGoodsWithColor(SmartPhone.Color.BLACK);

        System.out.println("Search by criteria (Xiaomi, Black) test result: ");
        System.out.println(searchResult2);

        Assert.assertEquals(searchResult2.getGoods().size(), 1);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
