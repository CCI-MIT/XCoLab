package org.xcolab.view.auth;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.exceptions.UncheckedMemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.view.auth.login.spring.MemberDetails;

import java.util.Objects;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Service
public class AuthenticationContext {

    public static final String IMPERSONATE_MEMBER_ID_COOKIE_NAME = "X-Impersonate-memberId";

    public boolean isLoggedIn() {
        return getRealMemberOrNull() != null;
    }

    public boolean isImpersonating(HttpServletRequest request) {
        return !Objects.equals(getMemberOrNull(request), getRealMemberOrNull());
    }

    public Member getMemberOrNull(HttpServletRequest request) {
        final Member realMemberOrNull = getRealMemberOrNull();
        if (PermissionsClient.canAdminAll(realMemberOrNull)) {
            Long impersonatedMemberId = getImpersonatedMemberId(request);
            if (impersonatedMemberId != null) {
                try {
                    return MembersClient.getMember(impersonatedMemberId);
                } catch (MemberNotFoundException e) {
                    return realMemberOrNull;
                }
            }
        }
        return realMemberOrNull;
    }

    private static Long getImpersonatedMemberId(HttpServletRequest request) {
        final Cookie cookie = WebUtils.getCookie(request, IMPERSONATE_MEMBER_ID_COOKIE_NAME);

        Long impersonatedMemberId = null;
        if (cookie != null && StringUtils.isNumeric(cookie.getValue())) {
            impersonatedMemberId = Long.parseLong(cookie.getValue());
        }
        return impersonatedMemberId;
    }

    public Member getRealMemberOrNull() {
        final Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        if (authentication != null
                && authentication.isAuthenticated()
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

    public void authenticate(HttpServletRequest request, Member member) {
        //initialize session if it doesn't exist
        request.getSession();

        final MemberDetails memberDetails = new MemberDetails(member);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                memberDetails, null, memberDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
