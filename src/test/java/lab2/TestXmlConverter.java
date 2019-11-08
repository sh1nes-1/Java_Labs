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

    private XmlConverter<SmartPhone> smartPhoneXmlConverter;
    private SmartPhone smartPhone1;
    private SmartPhone smartPhone2;

    private XmlConverter<Catalog> catalogXmlConverter;
    private Catalog catalog;

    @BeforeTest
    public void beforeTest() {
        smartPhoneXmlConverter = new XmlConverter<>(SmartPhone.class);
        catalogXmlConverter = new XmlConverter<>(Catalog.class);
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

        // In catalog only item because items in catalog store in set (unordered) and after serialization order may be random
        catalog = new Catalog();
        catalog.addGoodsItem(smartPhone1, 5);
    }

    @Test
    public void serializeToStringTest() throws ConvertException {
        String expected = "<SmartPhone><id>1</id><name>Samsung Galaxy A30</name><price>5500</price><releaseDate>2019-06-15</releaseDate><color>BLACK</color><ram>3072</ram><diagonal>6.4</diagonal></SmartPhone>";
        String actual = smartPhoneXmlConverter.serializeToString(smartPhone1);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void deserializeStringTest() throws ConvertException {
        String jsonString = "<SmartPhone><id>1</id><name>Samsung Galaxy A30</name><price>5500</price><releaseDate>2019-06-15</releaseDate><color>BLACK</color><ram>3072</ram><diagonal>6.4</diagonal></SmartPhone>";
        SmartPhone actual = smartPhoneXmlConverter.deserializeString(jsonString);
        Assert.assertEquals(actual, smartPhone1);
    }

    @Test(expectedExceptions = ConvertException.class)
    public void negativeDeserializeStringTest() throws ConvertException {
        String jsonString = "<SmartPhone><name>Samsung Galaxy A30</name><releaseDate>2019-06-15</releaseDate><color>BLACK</color><ram>3072</ram><diagonal>6.4</diagonal></SmartPhone>";
        SmartPhone actual = smartPhoneXmlConverter.deserializeString(jsonString);
        Assert.assertEquals(actual, smartPhone1);
    }

    @Test
    public void serializeCatalogTest() throws ConvertException {
        String expected = "<Catalog><SmartPhones><item><smartPhone><id>1</id><name>Samsung Galaxy A30</name><price>5500</price><releaseDate>2019-06-15</releaseDate><color>BLACK</color><ram>3072</ram><diagonal>6.4</diagonal></smartPhone><amount>5</amount></item></SmartPhones></Catalog>";
        String actual = catalogXmlConverter.serializeToString(catalog);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void deserializeCatalogTest() throws ConvertException {
        String xmlString = "<Catalog><SmartPhones><item><smartPhone><id>1</id><name>Samsung Galaxy A30</name><price>5500</price><releaseDate>2019-06-15</releaseDate><color>BLACK</color><ram>3072</ram><diagonal>6.4</diagonal></smartPhone><amount>5</amount></item></SmartPhones></Catalog>";
        Catalog actual = catalogXmlConverter.deserializeString(xmlString);
        Assert.assertEquals(actual, catalog);
    }
}
