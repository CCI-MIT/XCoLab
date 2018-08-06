package org.xcolab.service.members.test.unit;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultDSLContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.xcolab.service.members.domain.loginlog.LoginLogDaoImpl;
import org.xcolab.service.members.domain.member.UserDaoImpl;
import org.xcolab.service.members.domain.role.RoleDaoImpl;
import org.xcolab.service.members.service.member.UserService;

import java.security.NoSuchAlgorithmException;

public class UserServiceTest {

    private UserService memberService;

    @Before
    public void setUp() {
        DSLContext dslContext = new DefaultDSLContext(SQLDialect.MYSQL);
        memberService = new UserService(new UserDaoImpl(dslContext),
                new RoleDaoImpl(dslContext), new LoginLogDaoImpl(dslContext), null);
    }

    @Test
    public void validatePassword_sha1() throws NoSuchAlgorithmException {
        Assert.assertTrue(memberService.validatePassword("colab123", "{SHA-1}NS2PVQOqtxe9YlFzf3xNa8/6XDo="));
    }

    @Test
    public void hashPassword_null() {
        Assert.assertNull(memberService.hashPassword(null));
    }
}
