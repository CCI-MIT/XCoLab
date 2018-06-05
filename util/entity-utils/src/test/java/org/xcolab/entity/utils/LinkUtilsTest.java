package org.xcolab.entity.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LinkUtilsTest {

    private static final String BASE_URL = "https://climatecolab.org";

    @Test
    public void testGetRelativeUri() {
        final String result = LinkUtils.getRelativeUri(BASE_URL + "/contests");
        assertEquals("Relative URI not correct", "/contests", result);
    }

    @Test
    public void testGetRelativeUri__queryParam() {
        final String result = LinkUtils.getRelativeUri(BASE_URL + "/contests?test=1&test2=2");
        assertEquals("Query params not handled correctly", "/contests?test=1&test2=2", result);
    }

    @Test
    public void testGetRelativeUri__emptyPath__shouldReturnRoot() {
        final String result = LinkUtils.getRelativeUri(BASE_URL);
        assertEquals("Empty path not handled correctly", "/", result);
    }

    @Test
    public void testGetRelativeUri__relativeUri__shouldReturnItself() {
        final String result = LinkUtils.getRelativeUri("/contests");
        assertEquals("Relative uri not returned unchanged", "/contests", result);
    }

    @Test
    public void testGetRelativeUri__empty__shouldReturnRoot() {
        final String result = LinkUtils.getRelativeUri("");
        assertEquals("Empty uri handled incorrectly", "/", result);
    }

    @Test
    public void testGetRelativeUri__blank__shouldReturnRoot() {
        final String result = LinkUtils.getRelativeUri(" ");
        assertEquals("Blank uri handled incorrectly", "/", result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetRelativeUri__null__shouldFail() {
        LinkUtils.getRelativeUri(null);
    }
}
