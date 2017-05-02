package org.xcolab.view.auth;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.exceptions.UncheckedMemberNotFoundException;
import org.xcolab.client.members.pojo.Member;

import javax.servlet.http.HttpServletRequest;

public final class MemberAuthUtil {

    private static final AuthenticationContext authenticationContext = new AuthenticationContext();

    private MemberAuthUtil() {
    }

    public static long getMemberId(HttpServletRequest request) {
        final Member memberOrNull = authenticationContext.getMemberOrNull(request);
        if (memberOrNull == null) {
            return 0L;
        }
        return memberOrNull.getId_();
    }

    public static Member getMemberOrThrow(HttpServletRequest request)
            throws UncheckedMemberNotFoundException {
        return authenticationContext.getMemberOrThrow(request);
    }

    public static Member getMemberOrNull(HttpServletRequest request) {
        try {
            return MembersClient.getMember(getMemberId(request));
        } catch (MemberNotFoundException e) {
            return null;
        }
    }
}
