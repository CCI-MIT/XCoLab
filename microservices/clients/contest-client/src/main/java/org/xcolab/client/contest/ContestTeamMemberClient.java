package org.xcolab.client.contest;

import org.xcolab.client.contest.pojo.IContestTeamMember;
import org.xcolab.client.contest.pojo.IContestTeamMemberRole;
import org.xcolab.client.members.permissions.SystemRole;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ContestTeamMemberClient {

    private final RestResource<IContestTeamMember, Long> contestTeamMemberResource = null; // contestTeamMembers
    private final RestResource<IContestTeamMemberRole, Long> contestTeamMemberRoleResource = null; // contestTeamMemberRoles

    public IContestTeamMember createContestTeamMember(IContestTeamMember contestTeamMember) {
        final IContestTeamMember result =
                contestTeamMemberResource.create(contestTeamMember)
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

    public IContestTeamMemberRole getContestTeamMemberRole(long id) {
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
        for (IContestTeamMember ctm : getTeamMembers(null, contestId, null)) {
            List<Long> roleUsers =
                    teamRoleToUsersMap.computeIfAbsent(ctm.getRoleId(), k -> new ArrayList<>());

            roleUsers.add(ctm.getUserId());
        }
        return teamRoleToUsersMap;
    }

    public List<IContestTeamMember> getTeamMembers(Long userId, Long contestId, Long roleId) {
        return contestTeamMemberResource.list()
                .optionalQueryParam("userId", userId)
                .optionalQueryParam("contestId", contestId)
                .optionalQueryParam("roleId", roleId)
                .withCache(CacheName.CONTEST_DETAILS)
                .execute();
    }
    public List<IContestTeamMember> getTeamMembers(Long categoryId, Long contestYear) {
        return null;
//        return contestTeamMemberResource.collectionService("getByContestYear", IContestTeamMember.TYPES.getTypeReference())
//                .optionalQueryParam("categoryId", categoryId)
//                .optionalQueryParam("contestYear", contestYear)
//                .getList();
    }




}
