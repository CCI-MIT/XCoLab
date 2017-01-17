package org.xcolab.view.auth.login.spring;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;

public class MemberDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
            return new MemberDetails(MembersClient.findMemberByScreenName(s));
        } catch (MemberNotFoundException e) {
            throw new UsernameNotFoundException("User " + s + " not found");
        }
    }
}
