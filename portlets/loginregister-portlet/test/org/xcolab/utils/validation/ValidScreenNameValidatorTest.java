/**
 * 
 */
package org.xcolab.utils.validation;

import junit.framework.Assert;

import org.junit.Test;

/**
 * @author pdeboer
 * 
 */
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
