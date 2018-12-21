package org.xcolab.view.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;
import org.springframework.security.web.authentication.switchuser.SwitchUserGrantedAuthority;

import org.xcolab.client.members.pojo.Member;
import org.xcolab.view.auth.login.spring.MemberDetails;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AuthenticationContext {

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
        List<? extends GrantedAuthority> prevAdmins = authentication.getAuthorities().stream()
                .filter(authority -> authority.getAuthority().equals(
                        SwitchUserFilter.ROLE_PREVIOUS_ADMINISTRATOR)).collect(Collectors.toList());

        if (prevAdmins.size() == 1 && prevAdmins.get(0) instanceof SwitchUserGrantedAuthority) {
            SwitchUserGrantedAuthority prevAdmin = (SwitchUserGrantedAuthority) prevAdmins.get(0);
            if (prevAdmin.getSource().getPrincipal() instanceof MemberDetails) {
                MemberDetails memberDetails = (MemberDetails) prevAdmin.getSource().getPrincipal();
                return memberDetails.getMember();
                //returns real user (admin)
            }
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
            // returns user which is logged in (impersonated)
        } else {
            return null;
        }
    }
}
