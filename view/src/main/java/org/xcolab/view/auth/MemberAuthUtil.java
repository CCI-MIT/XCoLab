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

    public static long getuserId() {
        final Member memberOrNull = authenticationContext.getMemberOrNull();
        if (memberOrNull == null) {
            return 0L;
        }
        return memberOrNull.getId();
    }

    public static Member getMemberOrNull() {
        try {
            return MembersClient.getMember(getuserId());
        } catch (MemberNotFoundException e) {
            return null;
        }
    }
}
