package org.xcolab.view.auth.login.spring;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.xcolab.client.members.MembersClient;
import org.xcolab.util.http.exceptions.EurekaNotInitializedException;

public class MemberPasswordEncoder implements PasswordEncoder {

    public static final String USER_NOT_FOUND_PASSWORD_HASH =
            "PBKDF2_160_128000_zQCr9UfQwfo=_aY3Tccnr+/dYhp3dOx7QUU0vr40=";

    @Override
    public String encode(CharSequence rawPassword) {
        try {
          return MembersClient.hashPassword(rawPassword.toString());
        } catch (EurekaNotInitializedException e) {
            // Spring Security calls this service one during startup before Eureka is initialized
            return USER_NOT_FOUND_PASSWORD_HASH;
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return MembersClient.validatePassword(rawPassword.toString(), encodedPassword);
    }
}
