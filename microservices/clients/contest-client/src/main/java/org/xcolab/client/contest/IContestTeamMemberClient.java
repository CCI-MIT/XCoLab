package org.xcolab.client.contest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contest.pojo.IContestTeamMember;
import org.xcolab.client.contest.pojo.IContestTeamMemberRole;
import org.xcolab.client.user.permissions.SystemRole;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@FeignClient("xcolab-contest-service")
public interface IContestTeamMemberClient {

    @PostMapping("/contestTeamMembers")
    IContestTeamMember createContestTeamMember(@RequestBody IContestTeamMember contestTeamMember);

    @DeleteMapping("/contestTeamMembers/{contestTeamUserId}")
    boolean deleteContestTeamMember(@PathVariable("contestTeamUserId") Long contestTeamUserId);

    @GetMapping("/contestTeamMemberRoles/{contestTeamMemberRoleId}")
    IContestTeamMemberRole getContestTeamMemberRole(
            @PathVariable("contestTeamMemberRoleId") Long contestTeamMemberRoleId);

    default List<Long> getAdvisorsForContest(Long contestId) {
        return getRoleForContestTeam(contestId, SystemRole.ADVISOR.getRoleId());
    }

    default List<Long> getJudgesForContest(Long contestId) {
        return getRoleForContestTeam(contestId, SystemRole.JUDGE.getRoleId());
    }

    default List<Long> getFellowsForContest(Long contestId) {
        return getRoleForContestTeam(contestId, SystemRole.FELLOW.getRoleId());
    }

    default List<Long> getContestManagersForContest(Long contestId) {
        return getRoleForContestTeam(contestId, SystemRole.CONTEST_MANAGER.getRoleId());
    }

    default List<Long> getIAFellowsForContest(Long contestId) {
        return getRoleForContestTeam(contestId, SystemRole.IMPACT_ASSESSMENT_FELLOW.getRoleId());
    }

    default List<Long> getRoleForContestTeam(Long contestId, Long roleId) {
        Map<Long, List<Long>> teamRoleToUsersMap = getContestTeamMembersByRole(contestId);
        List<Long> members = teamRoleToUsersMap.get(roleId);
        if (members == null) {
            return new ArrayList<>();
        } else {
            return members;
        }
    }

    default Map<Long, List<Long>> getContestTeamMembersByRole(Long contestId) {
        Map<Long, List<Long>> teamRoleToUsersMap = new TreeMap<>();
        for (IContestTeamMember ctm : getTeamMembers(null, contestId, null)) {
            List<Long> roleUsers =
                    teamRoleToUsersMap.computeIfAbsent(ctm.getRoleId(), k -> new ArrayList<>());
            roleUsers.add(ctm.getUserId());
        }
        return teamRoleToUsersMap;
    }

    @GetMapping("/contestTeamMembers")
    List<IContestTeamMember> getTeamMembers(
            @RequestParam(value = "userId", required = false) Long userId,
            @RequestParam(value = "contestId", required = false) Long contestId,
            @RequestParam(value = "roleId", required = false) Long roleId);

    @GetMapping("/contestTeamMembers/getByContestYear")
    public List<IContestTeamMember> getTeamMembers(
            @RequestParam(value = "categoryId", required = false) Long categoryId,
            @RequestParam(value = "contestYear", required = false) Long contestYear);
}
