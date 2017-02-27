package org.xcolab.client.contest;

import org.xcolab.client.contest.pojo.team.ContestTeamMember;
import org.xcolab.client.contest.pojo.team.ContestTeamMemberDto;
import org.xcolab.client.contest.pojo.team.ContestTeamMemberRole;
import org.xcolab.client.contest.pojo.team.ContestTeamMemberRoleDto;
import org.xcolab.client.members.legacy.enums.MemberRole;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.dto.DtoUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ContestTeamMemberClient {

    private static final Map<RestService, ContestTeamMemberClient> instances = new HashMap<>();

    private final RestService contestService;

    private final RestResource<ContestTeamMemberDto, Long> contestTeamMemberResource;
    private final RestResource<ContestTeamMemberRoleDto, Long> contestTeamMemberRoleResource;

    private ContestTeamMemberClient(RestService contestService) {
        this.contestService = contestService;
        contestTeamMemberResource = new RestResource1<>(this.contestService,
                "contestTeamMembers", ContestTeamMemberDto.TYPES);
        contestTeamMemberRoleResource = new RestResource1<>(this.contestService,
                "contestTeamMemberRoles", ContestTeamMemberRoleDto.TYPES);
    }

    public static ContestTeamMemberClient fromService(RestService contestService) {
        return instances
                .computeIfAbsent(contestService, k -> new ContestTeamMemberClient(contestService));
    }

    public ContestTeamMember createContestTeamMember(ContestTeamMember contestTeamMember) {
        final ContestTeamMember result =
                contestTeamMemberResource.create(new ContestTeamMemberDto(contestTeamMember))
                        .execute().toPojo(contestService);
        //TODO: fine-grained cache removal
        ServiceRequestUtils.clearCache(CacheName.CONTEST_DETAILS);
        return result;
    }

    public void deleteContestTeamMember(Long contestTeamMemberId) {
        contestTeamMemberResource.delete(contestTeamMemberId).execute();
        //TODO: fine-grained cache removal
        ServiceRequestUtils.clearCache(CacheName.CONTEST_DETAILS);
    }

    public ContestTeamMemberRole getContestTeamMemberRole(long id) {
        return contestTeamMemberRoleResource.get(id)
                .withCache(CacheName.CONTEST_DETAILS)
                .execute().toPojo(contestService);
    }

    public List<Long> getAdvisorsForContest(Long contestId) {
        return getRoleForContestTeam(contestId, MemberRole.ADVISOR.getRoleId());
    }

    public List<Long> getJudgesForContest(Long contestId) {
        return getRoleForContestTeam(contestId, MemberRole.JUDGE.getRoleId());
    }

    public List<Long> getFellowsForContest(Long contestId) {
        return getRoleForContestTeam(contestId, MemberRole.FELLOW.getRoleId());
    }

    public List<Long> getContestManagersForContest(Long contestId) {
        return getRoleForContestTeam(contestId, MemberRole.CONTEST_MANAGER.getRoleId());
    }

    public List<Long> getIAFellowsForContest(Long contestId) {
        return getRoleForContestTeam(contestId, MemberRole.IMPACT_ASSESSMENT_FELLOW.getRoleId());
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
        for (ContestTeamMember ctm : getTeamMembers(contestId)) {
            List<Long> roleUsers =
                    teamRoleToUsersMap.computeIfAbsent(ctm.getRoleId(), k -> new ArrayList<>());

            roleUsers.add(ctm.getUserId());
        }
        return teamRoleToUsersMap;
    }

    public List<ContestTeamMember> getTeamMembers(Long contestId) {
        return DtoUtil.toPojos(contestTeamMemberResource.list()
                .optionalQueryParam("contestId", contestId)
                .withCache(CacheName.CONTEST_DETAILS)
                .execute(), contestService);
    }
}
