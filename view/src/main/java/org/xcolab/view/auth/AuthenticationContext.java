package org.xcolab.view.auth;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.util.WebUtils;

import org.xcolab.client.user.MembersClient;
import org.xcolab.client.user.PermissionsClient;
import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.client.user.exceptions.UncheckedMemberNotFoundException;
import org.xcolab.client.user.pojo.Member;
import org.xcolab.view.auth.login.spring.MemberDetails;

import java.util.Objects;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class AuthenticationContext {

    public static final String IMPERSONATE_MEMBER_ID_COOKIE_NAME = "X-Impersonate-userId";

    public boolean isLoggedIn() {
        return getRealMemberOrNull() != null;
    }

    public boolean isImpersonating(HttpServletRequest request) {
        return !Objects.equals(getMemberOrNull(request), getRealMemberOrNull());
    }

    public Member getMemberOrNull(HttpServletRequest request) {
        final Member realMemberOrNull = getRealMemberOrNull();
        if (PermissionsClient.canAdminAll(realMemberOrNull)) {
            Long impersonateduserId = getImpersonateduserId(request);
            if (impersonateduserId != null) {
                try {
                    return MembersClient.getMember(impersonateduserId);
                } catch (MemberNotFoundException e) {
                    return realMemberOrNull;
                }
            }
        }
        return realMemberOrNull;
    }

    private static Long getImpersonateduserId(HttpServletRequest request) {
        final Cookie cookie = WebUtils.getCookie(request, IMPERSONATE_MEMBER_ID_COOKIE_NAME);

        Long impersonatedUserId = null;
        if (cookie != null && StringUtils.isNumeric(cookie.getValue())) {
            impersonatedUserId = Long.parseLong(cookie.getValue());
        }
        return impersonatedUserId;
    }

    public Member getRealMemberOrNull() {
        final Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return getRealMemberOrNull(authentication);
    }

    public Member getRealMemberOrNull(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()
                && authentication.getPrincipal() instanceof MemberDetails) {
            MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();
            return memberDetails.getMember();
        } else {
            return null;
        }
    }

    public Member getMemberOrThrow(HttpServletRequest request) {
        Member member = getMemberOrNull(request);
        if (member != null) {
            return member;
        }

        throw new UncheckedMemberNotFoundException("No member logged in - check before calling");
    }
}
