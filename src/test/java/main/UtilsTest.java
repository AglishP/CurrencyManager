package main;

import org.junit.Assert;
import org.junit.Test;

public class UtilsTest {

    @Test
    public void convertToCurrencyTest() {
        Assert.assertEquals("not EUR", CurrencyType.EUR, Utils.convertToCurrency("eur"));
        Assert.assertEquals("not EUR", CurrencyType.EUR, Utils.convertToCurrency("EUR"));
        Assert.assertEquals("not RUB", CurrencyType.EUR, Utils.convertToCurrency(""));
        Assert.assertEquals("not EUR", CurrencyType.EUR, Utils.convertToCurrency("eur"));
        Assert.assertEquals("not EUR", CurrencyType.EUR, Utils.convertToCurrency("eur"));
    }
}
