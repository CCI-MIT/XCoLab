package org.xcolab.view.auth.login.spring;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.xcolab.client.members.MembersClient;

public class MemberPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return MembersClient.hashPassword(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return MembersClient.validatePassword(rawPassword.toString(), encodedPassword);
    }
}
