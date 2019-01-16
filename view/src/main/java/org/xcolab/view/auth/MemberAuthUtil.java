package org.xcolab.view.auth;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;

public final class MemberAuthUtil {

    private static final AuthenticationContext authenticationContext = new AuthenticationContext();

    private MemberAuthUtil() {
    }

    public static long getUserId() {
        final Member memberOrNull = authenticationContext.getMemberOrNull();
        if (memberOrNull == null) {
            return 0L;
        }
        return memberOrNull.getId();
    }

    public static Member getMemberOrNull() {
        try {
            return MembersClient.getMember(getUserId());
        } catch (MemberNotFoundException e) {
            return null;
        }
    }
}
