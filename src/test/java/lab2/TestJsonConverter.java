package lab2;

import lab2.exception.ConvertException;
import lab2.model.Catalog;
import lab2.model.SmartPhone;
import lab2.service.converter.JsonConverter;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class TestJsonConverter {

    private JsonConverter<SmartPhone> smartPhoneJsonConverter;
    private SmartPhone smartPhone1;

    private JsonConverter<Catalog> catalogJsonConverter;
    private Catalog catalog;

    @BeforeTest
    public void beforeTest() {
        smartPhoneJsonConverter = new JsonConverter<>(SmartPhone.class);
        catalogJsonConverter = new JsonConverter<>(Catalog.class);
    }

    @BeforeMethod
    public void beforeMethod() {
        smartPhone1 = new SmartPhone.Builder()
                .setId(1)
                .setName("Samsung Galaxy A30")
                .setDiagonal(6.4)
                .setColor(SmartPhone.Color.BLACK)
                .setRam(3072)
                .setReleaseDate(LocalDate.of(2019, 6, 15))
                .setPrice(5500)
                .build();

        SmartPhone smartPhone2 = new SmartPhone.Builder()
                .setId(2)
                .setName("Xiaomi Redmi Note 7")
                .setDiagonal(6.8)
                .setColor(SmartPhone.Color.RED)
                .setRam(4096)
                .setReleaseDate(LocalDate.of(2019, 8, 16))
                .setPrice(6800)
                .build();

        catalog = new Catalog();
        catalog.addGoodsItem(smartPhone1, 5);
        catalog.addGoodsItem(smartPhone2, 10);
    }

    @Test
    public void serializeToStringTest() throws ConvertException {
        String expected = "{\"id\":1,\"name\":\"Samsung Galaxy A30\",\"price\":5500,\"releaseDate\":\"2019-06-15\",\"color\":\"BLACK\",\"ram\":3072,\"diagonal\":6.4}";
        String actual = smartPhoneJsonConverter.serializeToString(smartPhone1);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void deserializeStringTest() throws ConvertException {
        String jsonString = "{\"id\":1,\"name\":\"Samsung Galaxy A30\",\"price\":5500,\"releaseDate\":\"2019-06-15\",\"color\":\"BLACK\",\"ram\":3072,\"diagonal\":6.4}";
        SmartPhone actual = smartPhoneJsonConverter.deserializeString(jsonString);
        Assert.assertEquals(actual, smartPhone1);
    }

    @Test(expectedExceptions = ConvertException.class)
    public void negativeDeserializeStringTest() throws ConvertException {
        String jsonString = "{\"name\":\"Samsung Galaxy A30\",\"releaseDate\":\"2019-06-15\",\"color\":\"BLACK\",\"ram\":3072,\"diagonal\":6.4}";
        smartPhoneJsonConverter.deserializeString(jsonString);
    }

    @Test
    public void serializeCatalogTest() throws ConvertException, JSONException {
        String expected = "{\"SmartPhones\":[{\"smartPhone\":{\"id\":1,\"name\":\"Samsung Galaxy A30\",\"price\":5500,\"releaseDate\":\"2019-06-15\",\"color\":\"BLACK\",\"ram\":3072,\"diagonal\":6.4},\"amount\":5},{\"smartPhone\":{\"id\":2,\"name\":\"Xiaomi Redmi Note 7\",\"price\":6800,\"releaseDate\":\"2019-08-16\",\"color\":\"RED\",\"ram\":4096,\"diagonal\":6.8},\"amount\":10}]}";
        String actual = catalogJsonConverter.serializeToString(catalog);
        JSONAssert.assertEquals(actual, expected, false);
    }

    @Test
    public void deserializeCatalogTest() throws ConvertException {
        String jsonString = "{\"SmartPhones\":[{\"smartPhone\":{\"id\":1,\"name\":\"Samsung Galaxy A30\",\"price\":5500,\"releaseDate\":\"2019-06-15\",\"color\":\"BLACK\",\"ram\":3072,\"diagonal\":6.4},\"amount\":5},{\"smartPhone\":{\"id\":2,\"name\":\"Xiaomi Redmi Note 7\",\"price\":6800,\"releaseDate\":\"2019-08-16\",\"color\":\"RED\",\"ram\":4096,\"diagonal\":6.8},\"amount\":10}]}";
        Catalog actual = catalogJsonConverter.deserializeString(jsonString);
        Assert.assertEquals(actual, catalog);
    }
}
