package org.xcolab.view.util.validation;

import org.junit.Assert;
import org.junit.Test;

public class ValidScreenNameValidatorTest {

    private ValidScreenNameValidator v = new ValidScreenNameValidator(null);

    @Test
    public void testValidateScreenName__givenAlphanumericString__shouldReturnTrue() {
        Assert.assertEquals("Valid screen name rejected",
                true, v.validateScreenName("test123AA"));
    }

    @Test
    public void testValidateScreenName__givenSpecialCharacter__shouldReturnFalse() {
        Assert.assertEquals("Space allowed",
                false, v.validateScreenName("test test"));
        Assert.assertEquals("Question mark allowed",
                false, v.validateScreenName("test?"));
        Assert.assertEquals("Exclamation mark allowed",
                false, v.validateScreenName("test!"));
        Assert.assertEquals("Special character allowed",
                false, v.validateScreenName("test@"));
    }

    @Test
    public void testValidateScreenName__givenEmptyString__shouldReturnTrue() {
        Assert.assertEquals("Empty string allowed",
                false, v.validateScreenName(""));
        Assert.assertEquals("Null allowed",
                false, v.validateScreenName(null));
    }
}
