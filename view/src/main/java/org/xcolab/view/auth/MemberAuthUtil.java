package org.xcolab.view.auth;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.WebUtils;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.exceptions.UncheckedMemberNotFoundException;
import org.xcolab.client.members.pojo.Member;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public final class MemberAuthUtil {

    public static final String IMPERSONATE_MEMBER_ID_COOKIE_NAME = "X-Impersonate-memberId";

    private static final AuthenticationContext authenticationContext = new AuthenticationContext();

    private MemberAuthUtil() {
    }

    private static Long getImpersonatedMemberId(HttpServletRequest request) {
        //TODO: port impersonation
        final Cookie cookie = WebUtils.getCookie(request, IMPERSONATE_MEMBER_ID_COOKIE_NAME);

        Long impersonatedMemberId = null;
        if (cookie != null && StringUtils.isNumeric(cookie.getValue())) {
            impersonatedMemberId = Long.parseLong(cookie.getValue());
        }
        return impersonatedMemberId;
    }

    private static Member getRemoteMember() {
        return authenticationContext.getMemberOrNull();
    }

    private static long getRemoteMemberId() {
        final Member remoteMember = getRemoteMember();
        if (remoteMember != null) {
            return remoteMember.getId_();
        }
        return 0L;
    }

    public static long getMemberId(HttpServletRequest request) {
        final long loggedInMemberId = getRemoteMemberId();
        if (PermissionsClient.canAdminAll(loggedInMemberId)) {
            Long impersonatedMemberId = getImpersonatedMemberId(request);
            if (impersonatedMemberId != null) {
                return impersonatedMemberId;
            }
        }
        return loggedInMemberId;
    }

    public static Member getMemberOrThrow(HttpServletRequest request)
            throws UncheckedMemberNotFoundException {
        return MembersClient.getMemberUnchecked(getMemberId(request));
    }

    public static Member getMemberOrNull(HttpServletRequest request) {
        try {
            return MembersClient.getMember(getMemberId(request));
        } catch (MemberNotFoundException e) {
            return null;
        }
    }

    public static long getRealMemberId(HttpServletRequest request) {
        return getRemoteMemberId();
    }

    public static boolean isImpersonating(HttpServletRequest request) {
        return getMemberId(request) != getRemoteMemberId();
    }
}
