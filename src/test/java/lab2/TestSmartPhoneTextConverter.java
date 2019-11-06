package lab2;

import lab2.model.SmartPhone;
import lab2.exception.ConvertException;
import lab2.service.converter.SmartPhoneTextConverter;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class TestSmartPhoneTextConverter {

    private SmartPhoneTextConverter smartPhoneTextConverter;
    private SmartPhone smartPhone;

    {
        smartPhoneTextConverter = new SmartPhoneTextConverter();
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
        String expected = "1-Samsung Galaxy A30-BLACK-6.4-3072-5500-2019\\-06\\-15";
        String actual = smartPhoneTextConverter.serializeToString(smartPhone);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void deserializeStringTest() throws ConvertException {
        String serialized = "1-Samsung Galaxy A30-BLACK-6.4-3072-5500-2019\\-06\\-15";
        SmartPhone actual = smartPhoneTextConverter.deserializeString(serialized);
        Assert.assertEquals(actual, smartPhone);
    }


    @DataProvider
    public Object[][] negativeDeserializeStringDataProvider() {
        return new Object[][] {
                { "Samsung Galaxy A30-JNKS-6.4-3072-5500-2019\\-36\\-15" },
                { "Samsung Galaxy A30-6.4-3072-5500-2019-36-15" },
                { "Samsung Galaxy A30-6.4-string-5500-2019\\-36\\-15" },
                { "Samsung Galaxy A30-6.4-3072.0-5500-2019\\-36\\-15" },
                { "Samsung Galaxy A30-3072-5500-2019\\-36\\-15" }
        };
    }

    @Test(expectedExceptions = ConvertException.class, dataProvider = "negativeDeserializeStringDataProvider")
    public void negativeDeserializeStringTest(String serializedString) throws ConvertException {
        smartPhoneTextConverter.deserializeString(serializedString);
    }
}
