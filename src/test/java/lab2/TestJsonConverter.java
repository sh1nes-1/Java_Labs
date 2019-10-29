package lab2;

import lab2.model.Catalog;
import lab2.exception.ConvertException;
import lab2.model.SmartPhone;
import lab2.service.JsonConverter;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.LocalDate;

public class TestJsonConverter {

    private JsonConverter<SmartPhone> smartPhoneJsonConverter;
    private SmartPhone smartPhone;

    private JsonConverter<Catalog> catalogJsonConverter;
    private Catalog catalog;

    @BeforeTest
    public void beforeTest() {
        smartPhoneJsonConverter = new JsonConverter<>(SmartPhone.class);
        catalogJsonConverter = new JsonConverter<>(Catalog.class);
    }

    @BeforeMethod
    public void beforeMethod() {
        smartPhone = new SmartPhone.Builder()
                .setName("Samsung Galaxy A30")
                .setDiagonal(6.4)
                .setColor(SmartPhone.Color.BLACK)
                .setRam(3072)
                .setReleaseDate(LocalDate.of(2019, 6, 15))
                .setPrice(5500)
                .build();

        catalog = new Catalog();
        catalog.addGoodsItem(smartPhone, 5);
    }

    @Test
    public void serializeToStringTest() throws ConvertException {
        String expected = "{\"name\":\"Samsung Galaxy A30\",\"price\":5500,\"releaseDate\":\"2019-06-15\",\"color\":\"BLACK\",\"ram\":3072,\"diagonal\":6.4}";
        String actual = smartPhoneJsonConverter.serializeToString(smartPhone);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void deserializeStringTest() throws ConvertException {
        String jsonString = "{\"name\":\"Samsung Galaxy A30\",\"price\":5500,\"releaseDate\":\"2019-06-15\",\"color\":\"BLACK\",\"ram\":3072,\"diagonal\":6.4}";
        SmartPhone expected = smartPhone;
        SmartPhone actual = smartPhoneJsonConverter.deserializeString(jsonString);
        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ConvertException.class)
    public void negativeDeserializeStringTest() throws ConvertException {
        String jsonString = "{\"name\":\"Samsung Galaxy A30\",\"releaseDate\":\"2019-06-15\",\"color\":\"BLACK\",\"ram\":3072,\"diagonal\":6.4}";
        SmartPhone expected = smartPhone;
        SmartPhone actual = smartPhoneJsonConverter.deserializeString(jsonString);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void serializeCatalogTest() throws ConvertException {
        String expected = "{\"availableGoods\":{\"{\\\"name\\\":\\\"Samsung Galaxy A30\\\",\\\"price\\\":5500,\\\"releaseDate\\\":\\\"2019-06-15\\\",\\\"color\\\":\\\"BLACK\\\",\\\"ram\\\":3072,\\\"diagonal\\\":6.4}\":5}}";
        String actual = catalogJsonConverter.serializeToString(catalog);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void deserializeCatalogTest() throws ConvertException {
        String jsonString = "{\"availableGoods\":{\"{\\\"name\\\":\\\"Samsung Galaxy A30\\\",\\\"price\\\":5500,\\\"releaseDate\\\":\\\"2019-06-15\\\",\\\"color\\\":\\\"BLACK\\\",\\\"ram\\\":3072,\\\"diagonal\\\":6.4}\":5}}";
        Catalog expected = catalog;
        Catalog actual = catalogJsonConverter.deserializeString(jsonString);
        Assert.assertEquals(actual, expected);
    }
}
