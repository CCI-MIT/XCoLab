package org.xcolab.view.auth.login;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;

import org.xcolab.client.user.exceptions.MemberNotFoundException;

public enum AuthenticationError {
    CREDENTIALS("Authentication failed, please check your screen name and password.",
            BadCredentialsException.class, MemberNotFoundException.class),
    LOCKOUT("This account has been locked", LockedException.class),
    UNKNOWN("An error occurred");

    private final String message;
    private final Class<? extends Throwable>[] exceptions;

    @SafeVarargs
    AuthenticationError(String message, Class<? extends Throwable>... exceptions) {
        this.message = message;
        this.exceptions = exceptions;
    }

    public String getMessage() {
        return message;
    }

    public static AuthenticationError fromException(Throwable exception) {
        for (AuthenticationError error : values()) {
            for (Class<? extends Throwable> throwable : error.exceptions) {
                if (throwable.equals(exception.getClass())) {
                    return error;
                }
            }
        }
        return UNKNOWN;
    }

    public static AuthenticationError fromName(String name) {
        if (StringUtils.isNotBlank(name)) {
            try {
                return valueOf(name);
            } catch (IllegalArgumentException e) {
                return UNKNOWN;
            }
        }
        return null;
    }
}
