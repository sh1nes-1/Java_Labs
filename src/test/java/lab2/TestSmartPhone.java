package lab2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lab1.model.SmartPhone;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.LocalDate;


public class TestSmartPhone {

    private Gson gson;
    private SmartPhone smartPhone;

    @BeforeTest
    public void createGson() {
        gson = new GsonBuilder().create();
    }

    @BeforeMethod
    public void createSmartPhoneAndService() {
        smartPhone = new SmartPhone.Builder()
                .setName("Xiaomi Redmi Note 7")
                .setDiagonal(6.3)
                .setColor(SmartPhone.Color.BLUE)
                .setRam(3072)
                .setReleaseDate(LocalDate.of(2019, 5, 1))
                .setPrice(4500)
                .build();
    }

    @Test
    public void testSerializeJsonSmartPhone() {
        String result = gson.toJson(smartPhone);
        String expectedResult = "{\"name\":\"Xiaomi Redmi Note 7\",\"price\":4500,\"releaseDate\":{\"year\":2019,\"month\":5,\"day\":1},\"color\":\"BLUE\",\"ram\":3072,\"diagonal\":6.3}";
        Assert.assertEquals(result, expectedResult);
    }

    @Test
    public void testDeserializeJsonSmartPhone() {
        String jsonString = "{\"name\":\"Xiaomi Redmi Note 7\",\"price\":4500,\"releaseDate\":{\"year\":2019,\"month\":5,\"day\":1},\"color\":\"BLUE\",\"ram\":3072,\"diagonal\":6.3}";
        SmartPhone xiaomiRedmi = gson.fromJson(jsonString, SmartPhone.class);
        Assert.assertEquals(xiaomiRedmi, smartPhone);
    }
}
