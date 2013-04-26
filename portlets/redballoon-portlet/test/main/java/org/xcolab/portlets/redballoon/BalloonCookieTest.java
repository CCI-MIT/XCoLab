/**
 * 
 */
package org.xcolab.portlets.redballoon;

import javax.servlet.http.Cookie;

import junit.framework.Assert;

import org.junit.Test;

/**
 * @author pdeboer
 * 
 */
public class BalloonCookieTest {
	@Test
	public void testCookieDecodeNoMatchinName() {
		Cookie[] testCookies = new Cookie[] { new Cookie("test", "nothing") };
		Assert.assertNull(BalloonCookie.fromCookieArray(testCookies));
	}

	@Test
	public void testCookieDecodeMatchinNameWrongValue() {
		Cookie[] testCookies = new Cookie[] { new Cookie(
				BalloonCookie.COOKIE_NAME, "nothing") };
		Assert.assertNull(BalloonCookie.fromCookieArray(testCookies));
	}

	@Test
	public void testCookieDecodeCorrect() {
		Cookie[] testCookies = new Cookie[] { new Cookie("something", "bla"),
				new Cookie(BalloonCookie.COOKIE_NAME, "uuid\\email") };
		BalloonCookie bc = BalloonCookie.fromCookieArray(testCookies);
		Assert.assertNotNull(bc);
		Assert.assertEquals("uuid", bc.getUuid());
		Assert.assertEquals("email", bc.getEmail());
	}

}
