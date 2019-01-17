package org.xcolab.client.contest;

import org.xcolab.client.contest.pojo.ContestTeamMember;
import org.xcolab.client.contest.pojo.ContestTeamMemberRole;
import org.xcolab.client.contest.resources.ContestResource;
import org.xcolab.client.members.permissions.SystemRole;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ContestTeamMemberClient {

    private final RestResource<ContestTeamMember, Long> contestTeamMemberResource;
    private final RestResource<ContestTeamMemberRole, Long> contestTeamMemberRoleResource;

    public ContestTeamMemberClient() {
        contestTeamMemberResource = new RestResource1<>(ContestResource.CONTEST_TEAM_MEMBER,
                ContestTeamMember.TYPES);
        contestTeamMemberRoleResource = new RestResource1<>(
                ContestResource.CONTEST_TEAM_MEMBER_ROLE, ContestTeamMemberRole.TYPES);
    }

    public ContestTeamMember createContestTeamMember(ContestTeamMember contestTeamMember) {
        final ContestTeamMember result =
                contestTeamMemberResource.create(new ContestTeamMember(contestTeamMember))
                        .execute();
        //TODO COLAB-2589: fine-grained cache removal
        ServiceRequestUtils.clearCache(CacheName.CONTEST_DETAILS);
        return result;
    }

    public void deleteContestTeamMember(Long contestTeamuserId) {
        contestTeamMemberResource.delete(contestTeamuserId).execute();
        //TODO COLAB-2589: fine-grained cache removal
        ServiceRequestUtils.clearCache(CacheName.CONTEST_DETAILS);
    }

    public ContestTeamMemberRole getContestTeamMemberRole(long id) {
        return contestTeamMemberRoleResource.get(id)
                .withCache(CacheName.CONTEST_DETAILS)
                .execute();
    }

    public List<Long> getAdvisorsForContest(Long contestId) {
        return getRoleForContestTeam(contestId, SystemRole.ADVISOR.getRoleId());
    }

    public List<Long> getJudgesForContest(Long contestId) {
        return getRoleForContestTeam(contestId, SystemRole.JUDGE.getRoleId());
    }

    public List<Long> getFellowsForContest(Long contestId) {
        return getRoleForContestTeam(contestId, SystemRole.FELLOW.getRoleId());
    }

    public List<Long> getContestManagersForContest(Long contestId) {
        return getRoleForContestTeam(contestId, SystemRole.CONTEST_MANAGER.getRoleId());
    }

    public List<Long> getIAFellowsForContest(Long contestId) {
        return getRoleForContestTeam(contestId, SystemRole.IMPACT_ASSESSMENT_FELLOW.getRoleId());
    }

    public List<Long> getRoleForContestTeam(Long contestId, Long roleId) {
        Map<Long, List<Long>> teamRoleToUsersMap = getContestTeamMembersByRole(contestId);
        List<Long> members = teamRoleToUsersMap.get(roleId);
        if (members == null) {
            return new ArrayList<>();
        } else {
            return members;
        }
    }

    public Map<Long, List<Long>> getContestTeamMembersByRole(Long contestId) {
        Map<Long, List<Long>> teamRoleToUsersMap = new TreeMap<>();
        for (ContestTeamMember ctm : getTeamMembers(null, contestId, null)) {
            List<Long> roleUsers =
                    teamRoleToUsersMap.computeIfAbsent(ctm.getRoleId(), k -> new ArrayList<>());

            roleUsers.add(ctm.getUserId());
        }
        return teamRoleToUsersMap;
    }

    public List<ContestTeamMember> getTeamMembers(Long userId, Long contestId, Long roleId) {
        return contestTeamMemberResource.list()
                .optionalQueryParam("userId", userId)
                .optionalQueryParam("contestId", contestId)
                .optionalQueryParam("roleId", roleId)
                .withCache(CacheName.CONTEST_DETAILS)
                .execute();
    }
    public List<ContestTeamMember> getTeamMembers(Long categoryId, Long contestYear) {
        return contestTeamMemberResource.collectionService("getByContestYear",ContestTeamMember.TYPES.getTypeReference())
                .optionalQueryParam("categoryId", categoryId)
                .optionalQueryParam("contestYear", contestYear)
                .getList();
    }




}
