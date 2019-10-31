package lab2;

import lab2.exception.ConvertException;
import lab2.model.Catalog;
import lab2.model.SmartPhone;
import lab2.service.converter.XmlConverter;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class TestXmlConverter {

    private XmlConverter<SmartPhone> xmlConverter;
    private SmartPhone smartPhone;

    @BeforeTest
    public void beforeTest() {
        xmlConverter = new XmlConverter<>(SmartPhone.class);
    }

    @BeforeMethod
    public void beforeMethod() {
        smartPhone = new SmartPhone.Builder()
                .setId(1)
                .setName("Samsung Galaxy A30")
                .setDiagonal(6.4)
                .setColor(SmartPhone.Color.BLACK)
                .setRam(3072)
                .setReleaseDate(LocalDate.of(2019, 6, 15))
                .setPrice(5500)
                .build();
    }

    @Test
    public void serializeToStringTest() throws ConvertException {
        String expected = "<SmartPhone><id>1</id><name>Samsung Galaxy A30</name><price>5500</price><releaseDate>2019-06-15</releaseDate><color>BLACK</color><ram>3072</ram><diagonal>6.4</diagonal></SmartPhone>";
        String actual = xmlConverter.serializeToString(smartPhone);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void deserializeStringTest() throws ConvertException {
        String jsonString = "<SmartPhone><id>1</id><name>Samsung Galaxy A30</name><price>5500</price><releaseDate>2019-06-15</releaseDate><color>BLACK</color><ram>3072</ram><diagonal>6.4</diagonal></SmartPhone>";
        SmartPhone expected = smartPhone;
        SmartPhone actual = xmlConverter.deserializeString(jsonString);
        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ConvertException.class)
    public void negativeDeserializeStringTest() throws ConvertException {
        String jsonString = "<SmartPhone><name>Samsung Galaxy A30</name><releaseDate>2019-06-15</releaseDate><color>BLACK</color><ram>3072</ram><diagonal>6.4</diagonal></SmartPhone>";
        SmartPhone expected = smartPhone;
        SmartPhone actual = xmlConverter.deserializeString(jsonString);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void serializeCatalogTest() throws ConvertException {
        Catalog catalog = new Catalog();
        catalog.addGoodsItem(smartPhone, 2);
        System.out.println(xmlConverter.serializeToString(catalog));
        throw new ConvertException("Check me");
    }
}
