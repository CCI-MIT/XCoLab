package org.xcolab.view.auth;

import org.xcolab.client.user.MembersClient;
import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.client.user.exceptions.UncheckedMemberNotFoundException;
import org.xcolab.client.user.pojo.Member;

import javax.servlet.http.HttpServletRequest;

public final class MemberAuthUtil {

    private static final AuthenticationContext authenticationContext = new AuthenticationContext();

    private MemberAuthUtil() {
    }

    public static long getuserId(HttpServletRequest request) {
        final Member memberOrNull = authenticationContext.getMemberOrNull(request);
        if (memberOrNull == null) {
            return 0L;
        }
        return memberOrNull.getId();
    }

    public static Member getMemberOrThrow(HttpServletRequest request)
            throws UncheckedMemberNotFoundException {
        return authenticationContext.getMemberOrThrow(request);
    }

    public static Member getMemberOrNull(HttpServletRequest request) {
        try {
            return MembersClient.getMember(getuserId(request));
        } catch (MemberNotFoundException e) {
            return null;
        }
    }
}
