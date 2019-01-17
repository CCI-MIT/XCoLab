package org.xcolab.client.contest;

import org.xcolab.client.contest.pojo.IContestTeamMember;
import org.xcolab.client.contest.pojo.IContestTeamMemberRole;

import java.util.List;
import java.util.Map;

public final class ContestTeamMemberClientUtil {

    private static final ContestTeamMemberClient client = new ContestTeamMemberClient();

    private ContestTeamMemberClientUtil() {
    }

    public static ContestTeamMemberClient getClient() {
        return client;
    }

    public static IContestTeamMember createContestTeamMember(
            IContestTeamMember contestTeamMember) {
        return client.createContestTeamMember(contestTeamMember);
    }

    public static void deleteContestTeamMember(Long contestTeamuserId) {
        client.deleteContestTeamMember(contestTeamuserId);
    }

    public static IContestTeamMemberRole getContestTeamMemberRole(long id) {
        return client.getContestTeamMemberRole(id);
    }

    public static List<Long> getAdvisorsForContest(Long contestId) {
        return client.getAdvisorsForContest(contestId);
    }

    public static List<Long> getRoleForContestTeam(Long contestId, Long roleId) {
        return client.getRoleForContestTeam(contestId, roleId);
    }

    public static Map<Long, List<Long>> getContestTeamMembersByRole(
            Long contestId) {
        return client.getContestTeamMembersByRole(contestId);
    }

    public static List<IContestTeamMember> getTeamMembers(Long userId, Long contestId, Long roleId) {
        return client.getTeamMembers(userId, contestId, roleId);
    }
    public static List<IContestTeamMember> getTeamMembers(Long categoryId, Long contestYear) {
        return client.getTeamMembers(categoryId, contestYear);
    }

    public static List<Long> getJudgesForContest(Long contestId) {
        return client.getJudgesForContest(contestId);
    }

    public static List<Long> getFellowsForContest(Long contestId) {
        return client.getFellowsForContest(contestId);
    }

    public static List<Long> getContestManagersForContest(Long contestId) {
        return client.getContestManagersForContest(contestId);
    }
    public static List<Long> getIAFellowsForContest(Long contestId){
        return client.getIAFellowsForContest(contestId);
    }

}
