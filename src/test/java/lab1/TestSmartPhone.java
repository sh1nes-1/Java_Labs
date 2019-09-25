package lab1;

import lab1.model.SmartPhone;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestSmartPhone {

    @Test
    public void builderTest() {
        SmartPhone smartPhone = new SmartPhone.Builder()
                .setColor(SmartPhone.Color.GOLD)
                .setRam(2048)
                .build();

        Assert.assertNotEquals(smartPhone, null);
    }

}
