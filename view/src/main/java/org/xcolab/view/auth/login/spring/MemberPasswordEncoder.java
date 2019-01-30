package org.xcolab.view.auth.login.spring;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.xcolab.client.user.StaticUserContext;
import org.xcolab.util.http.ServiceRequestUtils;

public class MemberPasswordEncoder implements PasswordEncoder {

    private static final String USER_NOT_FOUND_PASSWORD_HASH =
            "PBKDF2_160_128000_zQCr9UfQwfo=_aY3Tccnr+/dYhp3dOx7QUU0vr40=";

    @Override
    public String encode(CharSequence rawPassword) {
        if (ServiceRequestUtils.isInitialized()) {
            return StaticUserContext.getUserLoginRegister().hashPassword(rawPassword.toString());
        } else {
            // Spring Security calls this service once during startup before Eureka is initialized
            return USER_NOT_FOUND_PASSWORD_HASH;
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return StaticUserContext.getUserLoginRegister().validatePassword(rawPassword.toString(), encodedPassword);
    }
}
