package org.xcolab.view.auth.login.spring;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class MemberDetails implements UserDetails {

    private static final long serialVersionUID = 1L;

    private final long userId;
    transient private Set<GrantedAuthority> authorities;

    public MemberDetails(UserWrapper member) {
        Assert.notNull(member, "Member cannot be null");
        userId = member.getId();
    }

    public UserWrapper getMember() {
        try {
            return StaticUserContext.getUserClient().getMember(userId);
        } catch (MemberNotFoundException e) {
            throw new IllegalStateException("Member with id " + userId + " does not exist");
        }
    }

    @Override
    public String getPassword() {
        return getMember().getHashedPassword();
    }

    @Override
    public String getUsername() {
        return getMember().getScreenName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return getMember().isActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities == null) {
            authorities = Collections.unmodifiableSet(getAuthoritiesForMember(userId));
        }
        return authorities;
    }

    private static SortedSet<GrantedAuthority> getAuthoritiesForMember(long userId) {
        // Ensure array iteration order is predictable (as per
        // UserDetails.getAuthorities() contract and SEC-717)
        SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<>(new AuthorityComparator());

        if (StaticUserContext.getPermissionClient().isMember(userId)) {
            sortedAuthorities.add(new SimpleGrantedAuthority("ROLE_MEMBER"));
        } else if (StaticUserContext.getPermissionClient().isGuest(userId)) {
            sortedAuthorities.add(new SimpleGrantedAuthority("ROLE_GUEST"));
        }

        if (StaticUserContext.getPermissionClient().canAdminAll(userId)) {
            sortedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return sortedAuthorities;
    }

    private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {

        private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

        @Override
        public int compare(GrantedAuthority g1, GrantedAuthority g2) {
            // Neither should ever be null as each entry is checked before adding it to
            // the set.
            // If the authority is null, it is a custom authority and should precede
            // others.
            if (g2.getAuthority() == null) {
                return -1;
            }

            if (g1.getAuthority() == null) {
                return 1;
            }

            return g1.getAuthority().compareTo(g2.getAuthority());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MemberDetails)) {
            return false;
        }

        MemberDetails that = (MemberDetails) o;

        return userId == that.userId;
    }

    @Override
    public int hashCode() {
        return (int) (userId ^ userId >>> 32);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userId", userId)
                .append("screenName", getMember().getScreenName())
                .append("authorities", authorities)
                .toString();
    }
}
