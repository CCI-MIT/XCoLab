package org.xcolab.client.contest;

import org.xcolab.client.contest.pojo.team.ContestTeamMember;
import org.xcolab.client.contest.pojo.team.ContestTeamMemberDto;
import org.xcolab.client.contest.pojo.team.ContestTeamMemberRole;
import org.xcolab.client.contest.pojo.team.ContestTeamMemberRoleDto;
import org.xcolab.client.contest.resources.ContestResource;
import org.xcolab.client.members.permissions.SystemRole;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.dto.DtoUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ContestTeamMemberClient {

    private static final Map<ServiceNamespace, ContestTeamMemberClient> instances = new HashMap<>();

    private final ServiceNamespace serviceNamespace;

    private final RestResource<ContestTeamMemberDto, Long> contestTeamMemberResource;
    private final RestResource<ContestTeamMemberRoleDto, Long> contestTeamMemberRoleResource;

    private ContestTeamMemberClient(ServiceNamespace serviceNamespace) {
        this.serviceNamespace = serviceNamespace;
        contestTeamMemberResource = new RestResource1<>(ContestResource.CONTEST_TEAM_MEMBER,
                ContestTeamMemberDto.TYPES, serviceNamespace);
        contestTeamMemberRoleResource = new RestResource1<>(
                ContestResource.CONTEST_TEAM_MEMBER_ROLE, ContestTeamMemberRoleDto.TYPES,
                serviceNamespace);
    }

    public static ContestTeamMemberClient fromService(ServiceNamespace serviceNamespace) {
        return instances
                .computeIfAbsent(serviceNamespace, k -> new ContestTeamMemberClient(serviceNamespace));
    }

    public ContestTeamMember createContestTeamMember(ContestTeamMember contestTeamMember) {
        final ContestTeamMember result =
                contestTeamMemberResource.create(new ContestTeamMemberDto(contestTeamMember))
                        .execute().toPojo(serviceNamespace);
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
                .execute().toPojo(serviceNamespace);
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
        return DtoUtil.toPojos(contestTeamMemberResource.list()
                .optionalQueryParam("userId", userId)
                .optionalQueryParam("contestId", contestId)
                .optionalQueryParam("roleId", roleId)
                .withCache(CacheName.CONTEST_DETAILS)
                .execute(), serviceNamespace);
    }
    public List<ContestTeamMember> getTeamMembers(Long categoryId, Long contestYear) {
        return DtoUtil.toPojos(contestTeamMemberResource.collectionService("getByContestYear",ContestTeamMemberDto.TYPES.getTypeReference())
                .optionalQueryParam("categoryId", categoryId)
                .optionalQueryParam("contestYear", contestYear)
                .getList(), serviceNamespace);
    }




}
