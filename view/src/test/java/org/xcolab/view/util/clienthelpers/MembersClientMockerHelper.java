package org.xcolab.view.util.clienthelpers;

import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.view.util.TestUtil;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;

public class MembersClientMockerHelper {

    public static Member getDefaultMember(){
        Member member = new Member();
        member.setScreenName(TestUtil.createStringWithLength(10));
        member.setFirstName(TestUtil.createStringWithLength(10));
        member.setLastName(TestUtil.createStringWithLength(10));
        member.setId(100L);
        member.setHashedPassword(TestUtil.createStringWithLength(10));
        return member;
    }

    public static void mockMembersClient() throws Exception {
        PowerMockito.mockStatic(MembersClient.class);
        Mockito.when(MembersClient.findMemberByScreenNameNoRole(anyString()))
                .thenAnswer(invocation -> getDefaultMember());

        Mockito.when(MembersClient.findMemberByEmailAddress(anyString()))
                .thenAnswer(invocation -> getDefaultMember());

        Mockito.when(MembersClient.register(any(Member.class)))
                .thenAnswer(invocation -> getDefaultMember());

        Mockito.when(MembersClient.findMemberByScreenName(anyString()))
                .thenAnswer(invocation -> {

                    if (invocation.getArguments()[0] == null || invocation.getArguments()[0].equals("")){
                        throw new MemberNotFoundException("");
                    } else {
                        return getDefaultMember();
                    }
                });

        Mockito.when(MembersClient.getMemberUnchecked(anyLong()))
                .thenAnswer(invocationOnMock-> getDefaultMember());
        Mockito.when(MembersClient.getMember(anyLong()))
                .thenAnswer(invocationOnMock-> getDefaultMember());
        Mockito.when(MembersClient.getMemberOrNull(anyLong()))
                .thenAnswer(invocationOnMock-> getDefaultMember());
    }
}
