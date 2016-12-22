package org.xcolab.view.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import org.xcolab.client.members.exceptions.UncheckedMemberNotFoundException;
import org.xcolab.client.members.pojo.Member;

@Service
public class AuthenticationContext {

    public boolean isLoggedIn() {
        return getMemberOrNull() != null;
    }

    public Member getMemberOrNull() {
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

    public Member getMemberOrThrow() {
        Member member = getMemberOrNull();
        if (member != null) {
            return member;
        }

        throw new UncheckedMemberNotFoundException("No member logged in - check before calling");
    }
}
