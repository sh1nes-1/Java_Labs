package lab1;

import lab1.model.Catalog;
import lab1.model.SmartPhone;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TestSmartPhone {

    @DataProvider
    public Object[][] smartPhoneDataProvider() {

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

        return new Object[][] { { smartPhones } };
    }

    @Test(dataProvider = "smartPhoneDataProvider")
    public void sortByPriceAscendingTest(Set<SmartPhone> smartPhones) {
        Catalog catalog = new Catalog();
        for (SmartPhone x : smartPhones) {
            catalog.addGoodsItem(x, 1 + (int)(Math.random() * 100));
        }

        System.out.println("Sort by price ascending result: ");

        int prev = 0;
        boolean flag = false;
        for (Map.Entry<SmartPhone, Integer> x : catalog.getGoodsSortedByPrice(true).entrySet()) {
            System.out.println(x.getKey());

            if (!flag) {
                prev = x.getKey().getPrice();
                flag = true;
            }
            else {
                Assert.assertTrue(x.getKey().getPrice() >= prev);
            }
        }
    }

    @Test(dataProvider = "smartPhoneDataProvider")
    public void sortByPriceDescendingTest(Set<SmartPhone> smartPhones) {
        Catalog catalog = new Catalog();
        for (SmartPhone x : smartPhones) {
            catalog.addGoodsItem(x, 1 + (int)(Math.random() * 100));
        }

        System.out.println("Sort by price descending result: ");

        int prev = 0;
        boolean flag = false;
        for (Map.Entry<SmartPhone, Integer> x : catalog.getGoodsSortedByPrice(false).entrySet()) {
            System.out.println(x.getKey());

            if (!flag) {
                prev = x.getKey().getPrice();
                flag = true;
            }
            else {
                Assert.assertTrue(x.getKey().getPrice() <= prev);
            }
        }
    }

    @Test(dataProvider = "smartPhoneDataProvider")
    public void sortByReleaseDateAscendingTest(Set<SmartPhone> smartPhones) {
        Catalog catalog = new Catalog();
        for (SmartPhone x : smartPhones) {
            catalog.addGoodsItem(x, 1 + (int)(Math.random() * 100));
        }

        System.out.println("Sort by release date ascending result: ");

        LocalDate prev = LocalDate.now();
        boolean flag = false;
        for (Map.Entry<SmartPhone, Integer> x : catalog.getGoodsSortedByReleaseDate(true).entrySet()) {
            System.out.println(x.getKey());

            if (!flag) {
                prev = x.getKey().getReleaseDate();
                flag = true;
            }
            else {
                Assert.assertTrue(x.getKey().getReleaseDate().compareTo(prev) > 0);
            }
        }
    }

    @Test(dataProvider = "smartPhoneDataProvider")
    public void searchByCriteriaTest(Set<SmartPhone> smartPhones) {
        Catalog catalog = new Catalog();
        for (SmartPhone x : smartPhones) {
            catalog.addGoodsItem(x, 1 + (int)(Math.random() * 100));
        }

        Catalog searchResult = catalog.
                searchGoodsByName("Xiaomi", false).
                searchGoodsWithColor(SmartPhone.Color.BLACK);

        System.out.println("Search by criteria (Xiaomi, Black) test result: ");
        System.out.println(searchResult);

        Assert.assertEquals(searchResult.getGoods().size(), 1);
    }
}
