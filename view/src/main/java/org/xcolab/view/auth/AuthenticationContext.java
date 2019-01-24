package org.xcolab.view.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;
import org.springframework.security.web.authentication.switchuser.SwitchUserGrantedAuthority;

import org.xcolab.client.members.pojo.Member;
import org.xcolab.view.auth.login.spring.MemberDetails;

import java.util.Objects;

public class AuthenticationContext {
q
    public boolean isLoggedIn() {
        return getMemberOrNull() != null;
    }

    public boolean isImpersonating() {
        return !Objects.equals(getRealMemberOrNull(), getMemberOrNull());
    }

    public Member getRealMemberOrNull() {
        Member impersonatedUser = getImpersonatedUser();
        if (impersonatedUser != null) {
            return impersonatedUser;
        }
        return getMemberOrNull();
    }

    private Member getImpersonatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return authentication.getAuthorities().stream()
                    .filter(authority -> authority.getAuthority().equals(
                            SwitchUserFilter.ROLE_PREVIOUS_ADMINISTRATOR))
                    .filter(authority -> authority instanceof SwitchUserGrantedAuthority)
                    .map(authority -> ((SwitchUserGrantedAuthority) authority))
                    .map(SwitchUserGrantedAuthority::getSource)
                    .map(Authentication::getPrincipal)
                    .filter(principal -> principal instanceof MemberDetails)
                    .map(principal -> (MemberDetails) principal)
                    .map(MemberDetails::getMember)
                    .findAny().orElse(null);
        }
        return null;
    }

    public Member getMemberOrNull() {
        final Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication
                .getPrincipal() instanceof MemberDetails) {
            MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();
            return memberDetails.getMember();
        } else {
            return null;
        }
    }
}
