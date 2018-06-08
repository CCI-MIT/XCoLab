package org.xcolab.entity.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LinkUtilsTest {

    private static final String BASE_URL = "https://climatecolab.org";

    @Test
    public void testGetRelativeUri() throws Exception {
        final String result = LinkUtils.getRelativeUri(BASE_URL + "/contests");
        assertEquals("Relative URI not correct", "/contests", result);
    }

    @Test
    public void testGetRelativeUri__queryParam() throws Exception {
        final String result = LinkUtils.getRelativeUri(BASE_URL + "/contests?test=1&test2=2");
        assertEquals("Query params not handled correctly", "/contests?test=1&test2=2", result);
    }

    @Test
    public void testGetRelativeUri__emptyPath__shouldReturnRoot() throws Exception {
        final String result = LinkUtils.getRelativeUri(BASE_URL);
        assertEquals("Empty path not handled correctly", "/", result);
    }

    @Test
    public void testGetRelativeUri__relativeUri__shouldReturnItself() throws Exception {
        final String result = LinkUtils.getRelativeUri("/contests");
        assertEquals("Relative uri not returned unchanged", "/contests", result);
    }

    @Test
    public void testGetRelativeUri__empty__shouldReturnRoot() throws Exception {
        final String result = LinkUtils.getRelativeUri("");
        assertEquals("Empty uri handled incorrectly", "/", result);
    }

    @Test
    public void testGetRelativeUri__blank__shouldReturnRoot() throws Exception {
        final String result = LinkUtils.getRelativeUri(" ");
        assertEquals("Blank uri handled incorrectly", "/", result);
    }

    @Test
    public void testGetRelativeUri__null__shouldReturnNull() throws Exception {
        final String result = LinkUtils.getRelativeUri(null);
        assertEquals("Null uri handled incorrectly", null, result);
    }
}
