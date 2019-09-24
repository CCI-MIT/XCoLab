package org.xcolab.service.members.test.unit;

import org.junit.Assert;
import org.junit.Test;
import org.xcolab.service.members.util.UsernameGenerator;

public class UsernameGeneratorTest {
    @Test
    public void getFull() throws Exception {
        UsernameGenerator usernameGenerator =
                new UsernameGenerator(new String[]{"john steve", "doe"}, true, Integer.MAX_VALUE);
        Assert.assertEquals(usernameGenerator.getFull(), "johnstevedoe");
    }

    @Test
    public void getFullest() throws Exception {
        UsernameGenerator usernameGenerator =
                new UsernameGenerator(new String[]{"john", "doe"}, true, Integer.MAX_VALUE);
        Assert.assertEquals("johndoe", usernameGenerator.getFullest());
    }

    @Test
    public void getNext() throws Exception {
        UsernameGenerator usernameGenerator =
                new UsernameGenerator(new String[]{"john", "doe"}, true, Integer.MAX_VALUE);
        Assert.assertEquals("johndoe", usernameGenerator.getNext());
        Assert.assertEquals("johndoe1", usernameGenerator.getNext());
        Assert.assertEquals("johndoe2", usernameGenerator.getNext());
    }

    @Test
    public void getNext__sizeLimit__exact() throws Exception {
        UsernameGenerator usernameGenerator =
                new UsernameGenerator(new String[]{"john", "doe"}, true, 7);
        Assert.assertEquals("johndoe", usernameGenerator.getNext());
        Assert.assertEquals("johnd1", usernameGenerator.getNext());
        Assert.assertEquals("johnd2", usernameGenerator.getNext());
    }

    @Test
    public void getNext__sizeLimit__oneBelow() throws Exception {
        UsernameGenerator usernameGenerator =
                new UsernameGenerator(new String[]{"john", "doe"}, true, 6);
        Assert.assertEquals("johnd", usernameGenerator.getNext());
        Assert.assertEquals("johnd1", usernameGenerator.getNext());
        Assert.assertEquals("johnd2", usernameGenerator.getNext());
    }

    @Test
    public void getNext__sizeLimit__oneAbove() throws Exception {
        UsernameGenerator usernameGenerator =
                new UsernameGenerator(new String[]{"john", "doe"}, true, 8);
        Assert.assertEquals("johndoe", usernameGenerator.getNext());
        Assert.assertEquals("johndoe1", usernameGenerator.getNext());
        Assert.assertEquals("johndoe2", usernameGenerator.getNext());
    }

}