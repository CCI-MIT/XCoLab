package org.xcolab.client.contest;

import org.xcolab.client.contest.pojo.ContestTeamMember;
import org.xcolab.client.contest.pojo.ContestTeamMemberRole;
import org.xcolab.client.members.legacy.enums.MemberRole;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheRetention;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public final class ContestTeamMemberClient {

    private static final RestService contestService = new RestService("contest-service");

    private static final RestResource<ContestTeamMember, Long> contestTeamMemberResource = new RestResource1<>(contestService,
            "contestTeamMembers", ContestTeamMember.TYPES);

    private static final RestResource<ContestTeamMemberRole, Long> contestTeamMemberRoleResource = new RestResource1<>(contestService,
            "contestTeamMemberRoles", ContestTeamMemberRole.TYPES);

    public static List<ContestTeamMember> getTeamMembers(Long contestId) {
        return contestTeamMemberResource.list()
                .optionalQueryParam("contestId", contestId)
                .execute();
    }

    public static ContestTeamMember createContestTeamMember(ContestTeamMember contestTeamMember) {
        return contestTeamMemberResource.create(contestTeamMember).execute();
    }

    public static void deleteContestTeamMember(Long contestTeamMemberId) {
        contestTeamMemberResource.delete(contestTeamMemberId).execute();
    }

    public static ContestTeamMemberRole getContestTeamMemberRole(long id) {
        return contestTeamMemberRoleResource.get(id)
                .withCache(CacheKeys.of(ContestTeamMemberRole.class, id), CacheRetention.LONG)
                .execute();
    }

    public static List<Long> getRoleForContestTeam(Long contestId, Long roleId) {
        Map<Long, List<Long>> teamRoleToUsersMap = getContestTeamMembersByRole(contestId);
        List<Long> members = teamRoleToUsersMap.get(roleId);
        if (members == null) {
            return new ArrayList<>();
        } else {
            return members;
        }
    }

    public static List<Long> getAdvisorsForContest(Long contestId) {
        return getRoleForContestTeam(contestId, MemberRole.ADVISOR.getRoleId());

    }

    public static List<Long> getJudgesForContest(Long contestId) {
        return getRoleForContestTeam(contestId, MemberRole.JUDGE.getRoleId());
    }

    public static List<Long> getFellowsForContest(Long contestId) {
        return getRoleForContestTeam(contestId, MemberRole.FELLOW.getRoleId());
    }

    public static List<Long> getContestManagersForContest(Long contestId) {
        return getRoleForContestTeam(contestId, MemberRole.CONTEST_MANAGER.getRoleId());

    }

    public static Map<Long, List<Long>> getContestTeamMembersByRole(Long contestId) {
        Map<Long, List<Long>> teamRoleToUsersMap = new TreeMap<>();
        for (ContestTeamMember ctm : getTeamMembers(contestId)) {
            List<Long> roleUsers = teamRoleToUsersMap.get(ctm.getRoleId());

            if (roleUsers == null) {
                roleUsers = new ArrayList<>();
                teamRoleToUsersMap.put(ctm.getRoleId(), roleUsers);
            }

            roleUsers.add(ctm.getUserId());

        }
        return teamRoleToUsersMap;
    }
}
