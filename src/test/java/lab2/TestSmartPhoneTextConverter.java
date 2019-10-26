package lab2;

import lab2.model.SmartPhone;
import lab2.exception.ConvertException;
import lab2.service.Converter;
import lab2.service.SmartPhoneTextConverter;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
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
        String expected = "Samsung Galaxy A30-BLACK-6.4-3072-5500-2019\\-06\\-15";
        String actual = smartPhoneTextConverter.serializeToString(smartPhone);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void deserializeStringTest() throws ConvertException {
        String serialized = "Samsung Galaxy A30-BLACK-6.4-3072-5500-2019\\-06\\-15";
        SmartPhone actual = smartPhoneTextConverter.deserializeString(serialized);
        Assert.assertEquals(actual, smartPhone);
    }

    @Test(expectedExceptions = ConvertException.class)
    public void negativeDeserializeStringTest() throws ConvertException {
        String serialized = "Str-Samsung Galaxy A30-BLACK-6.4-3072-5500-2019\\-06\\-15";
        SmartPhone actual = smartPhoneTextConverter.deserializeString(serialized);
        Assert.assertEquals(actual, smartPhone);
    }
}
