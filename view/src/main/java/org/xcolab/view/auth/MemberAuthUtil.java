package org.xcolab.view.auth;

import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;

public final class MemberAuthUtil {

    private static final AuthenticationContext authenticationContext = new AuthenticationContext();

    private MemberAuthUtil() {
    }

    public static long getUserId() {
        final UserWrapper memberOrNull = authenticationContext.getMemberOrNull();
        if (memberOrNull == null) {
            return 0L;
        }
        return memberOrNull.getId();
    }

    public static UserWrapper getMemberOrNull() {
        try {
            return StaticUserContext.getUserClient().getUser(getUserId());
        } catch (MemberNotFoundException e) {
            return null;
        }
    }
}
