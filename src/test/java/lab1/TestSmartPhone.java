package lab1;

import lab1.model.Color;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestSmartPhone {

    @Test
    public void builderTest() {
        SmartPhone smartPhone = SmartPhone.newBuilder()
                .setColor(new Color(255, 0, 0))
                .setRam(2048)
                .build();

        Assert.assertNotEquals(smartPhone, null);
    }

}
