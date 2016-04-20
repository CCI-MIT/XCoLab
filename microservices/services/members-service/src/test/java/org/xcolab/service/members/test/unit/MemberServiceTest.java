package org.xcolab.service.members.test.unit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xcolab.service.members.domain.member.MemberDaoImpl;
import org.xcolab.service.members.service.member.MemberService;

import java.security.NoSuchAlgorithmException;

public class MemberServiceTest {

    private MemberService memberService;

    @Before
    public void setUp() {
        memberService = new MemberService(new MemberDaoImpl(), null);
    }

    @Test
    public void hashPassword() throws NoSuchAlgorithmException {
        final String hash = memberService.hashPassword("colab123");
        Assert.assertEquals("{SHA-1}NS2PVQOqtxe9YlFzf3xNa8/6XDo=", hash);
    }

    @Test
    public void validatePassword() throws NoSuchAlgorithmException {
        Assert.assertTrue(memberService.validatePassword("colab123", "{SHA-1}NS2PVQOqtxe9YlFzf3xNa8/6XDo="));
    }

}