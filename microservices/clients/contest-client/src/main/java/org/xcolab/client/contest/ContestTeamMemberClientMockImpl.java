package org.xcolab.client.contest;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.xcolab.client.contest.pojo.IContestTeamMember;
import org.xcolab.client.contest.pojo.IContestTeamMemberRole;

import java.util.Collections;
import java.util.List;

@Component
@Profile("test")
public class ContestTeamMemberClientMockImpl implements IContestTeamMemberClient {

    @Override
    public IContestTeamMember createContestTeamMember(IContestTeamMember contestTeamMember) {
        return null;
    }

    @Override
    public boolean deleteContestTeamMember(Long contestTeamUserId) {
        return false;
    }

    @Override
    public IContestTeamMemberRole getContestTeamMemberRole(Long contestTeamMemberRoleId) {
        return null;
    }

    @Override
    public List<IContestTeamMember> getTeamMembers(Long userId, Long contestId, Long roleId) {
        return Collections.emptyList();
    }

    @Override
    public List<IContestTeamMember> getTeamMembers(Long categoryId, Long contestYear) {
        return Collections.emptyList();
    }
}
