package org.xcolab.view.auth.login.spring;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class MemberDetails implements UserDetails {

    private static final long serialVersionUID = 1L;

    transient private Member member;
    transient private Set<GrantedAuthority> authorities;

    public MemberDetails(Member member) {
        init(member);
    }

    private void init(Member member) {
        Assert.notNull(member, "Member cannot be null");
        this.member = member;
        authorities = Collections.unmodifiableSet(getAuthoritiesForMember(member.getId_()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return member.getHashedPassword();
    }

    @Override
    public String getUsername() {
        return member.getScreenName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return member.isActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    private static SortedSet<GrantedAuthority> getAuthoritiesForMember(long memberId) {
        // Ensure array iteration order is predictable (as per
        // UserDetails.getAuthorities() contract and SEC-717)
        SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<>(
                new AuthorityComparator());

        sortedAuthorities.add(new SimpleGrantedAuthority("ROLE_MEMBER"));

        if (PermissionsClient.canAdminAll(memberId)) {
            sortedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return sortedAuthorities;
    }

    private static class AuthorityComparator implements Comparator<GrantedAuthority>,
            Serializable {
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

    public Member getMember() {
        return new Member(member);
    }

    private void writeObject(ObjectOutputStream oos)
            throws IOException {
        oos.defaultWriteObject();
        oos.writeLong(member.getId_());
    }

    private void readObject(ObjectInputStream ois)
            throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        final long memberId = ois.readLong();
        try {
            member = MembersClient.getMember(memberId);
        } catch (MemberNotFoundException e) {
            throw new InvalidObjectException("Member with id " + memberId + " does not exist");
        }
        init(member);
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

        return member.getId_() == that.member.getId_();
    }

    @Override
    public int hashCode() {
        return (int) (member.getId_() ^ member.getId_() >>> 32);
    }
}
