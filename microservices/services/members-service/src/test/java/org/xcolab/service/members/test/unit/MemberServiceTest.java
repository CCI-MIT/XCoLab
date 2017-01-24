package org.xcolab.service.members.test.unit;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultDSLContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.xcolab.service.members.domain.loginlog.LoginLogDaoImpl;
import org.xcolab.service.members.domain.member.MemberDaoImpl;
import org.xcolab.service.members.domain.role.RoleDaoImpl;
import org.xcolab.service.members.service.member.MemberService;

import java.security.NoSuchAlgorithmException;

public class MemberServiceTest {

    private MemberService memberService;

    @Before
    public void setUp() {
        DSLContext dslContext = new DefaultDSLContext(SQLDialect.MYSQL);
        memberService = new MemberService(new MemberDaoImpl(dslContext),
                new RoleDaoImpl(dslContext), new LoginLogDaoImpl(dslContext), null);
    }

    @Test
    public void hashPassword_liferayCompatible() throws NoSuchAlgorithmException {
        final String hash = memberService.hashPassword("colab123", true);
        Assert.assertEquals("{SHA-1}NS2PVQOqtxe9YlFzf3xNa8/6XDo=", hash);
    }

    @Test
    public void validatePassword() throws NoSuchAlgorithmException {
        Assert.assertTrue(memberService.validatePassword("colab123", "{SHA-1}NS2PVQOqtxe9YlFzf3xNa8/6XDo="));
    }

}