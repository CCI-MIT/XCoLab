package org.xcolab.view.util.validation;

import org.junit.Assert;
import org.junit.Test;

public class ValidScreenNameValidatorTest {

    @Test
    public void testScreenNameTester() {
        ValidScreenNameValidator v = new ValidScreenNameValidator();
        Assert.assertEquals(true, v.validateScreenName("test123AA"));

        Assert.assertEquals(false, v.validateScreenName("test test"));
        Assert.assertEquals(false, v.validateScreenName("test?"));
        Assert.assertEquals(false, v.validateScreenName("test!"));
        Assert.assertEquals(false, v.validateScreenName("test@"));
        Assert.assertEquals(false, v.validateScreenName(""));
        Assert.assertEquals(false, v.validateScreenName(null));
    }
}