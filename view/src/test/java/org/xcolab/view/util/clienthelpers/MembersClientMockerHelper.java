package org.xcolab.view.util.clienthelpers;

import org.mockito.Mockito;

import org.xcolab.client.user.IUserClient;
import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.view.util.TestUtil;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;

public class MembersClientMockerHelper {

    public static UserWrapper getDefaultMember(){
        UserWrapper member = new UserWrapper();
        member.setScreenName(TestUtil.createStringWithLength(10));
        member.setFirstName(TestUtil.createStringWithLength(10));
        member.setLastName(TestUtil.createStringWithLength(10));
        member.setId(100L);
        member.setHashedPassword(TestUtil.createStringWithLength(10));
        return member;
    }

    public static void mockMembersClient() throws Exception {

        IUserClient userClient = Mockito.mock(IUserClient.class);
        Mockito.when(userClient.getUserByScreenNameNoRole(anyString()))
                .thenAnswer(invocation -> getDefaultMember());

        Mockito.when(userClient.findMemberByEmailAddress(anyString()))
                .thenAnswer(invocation -> getDefaultMember());

        Mockito.when(userClient.register(any(UserWrapper.class)))
                .thenAnswer(invocation -> getDefaultMember());

        Mockito.when(userClient.findMemberByScreenName(anyString()))
                .thenAnswer(invocation -> {

                    if (invocation.getArguments()[0] == null || invocation.getArguments()[0].equals("")){
                        throw new MemberNotFoundException("");
                    } else {
                        return getDefaultMember();
                    }
                });

        Mockito.when(userClient.getMemberUnchecked(anyLong()))
                .thenAnswer(invocationOnMock-> getDefaultMember());
        Mockito.when(userClient.getMember(anyLong()))
                .thenAnswer(invocationOnMock-> getDefaultMember());
        Mockito.when(userClient.getMemberOrNull(anyLong()))
                .thenAnswer(invocationOnMock-> getDefaultMember());
    }
}
