package lab2;

import com.fasterxml.jackson.core.JsonProcessingException;
import lab1.model.SmartPhone;
import lab2.model.JsonConverter;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.LocalDate;

public class TestJsonConverter {

    private JsonConverter<SmartPhone> jsonConverter;
    private SmartPhone smartPhone;

    @BeforeTest
    public void beforeTest() {
        jsonConverter = new JsonConverter<>(SmartPhone.class);
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
    public void serializeToStringTest() throws JsonProcessingException {
        String expected = "{\"name\":\"Samsung Galaxy A30\",\"price\":5500,\"releaseDate\":\"2019-06-15\",\"color\":\"BLACK\",\"ram\":3072,\"diagonal\":6.4}";
        String actual = jsonConverter.serializeToString(smartPhone);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void deserializeStringTest() throws IOException {
        String jsonString = "{\"name\":\"Samsung Galaxy A30\",\"price\":5500,\"releaseDate\":\"2019-06-15\",\"color\":\"BLACK\",\"ram\":3072,\"diagonal\":6.4}";
        SmartPhone expected = smartPhone;
        SmartPhone actual = jsonConverter.deserializeString(jsonString);
        Assert.assertEquals(actual, expected);
    }

    // чи робити тести з файлами
}
