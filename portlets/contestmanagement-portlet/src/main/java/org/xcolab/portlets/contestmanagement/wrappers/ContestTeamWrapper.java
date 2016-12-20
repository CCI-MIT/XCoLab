package org.xcolab.portlets.contestmanagement.wrappers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.contest.ContestTeamMemberClientUtil;
import org.xcolab.client.contest.pojo.team.ContestTeamMember;
import org.xcolab.client.members.MembersClient;
import org.xcolab.entity.utils.enums.MemberRole;
import org.xcolab.liferay.SharedColabUtil;
import org.xcolab.portlets.contestmanagement.beans.ContestTeamBean;
import org.xcolab.util.enums.activity.ActivityEntryType;
import org.xcolab.util.http.exceptions.UncheckedEntityNotFoundException;

import java.util.List;

public class ContestTeamWrapper {

    private static final Logger log = LoggerFactory.getLogger(ContestTeamWrapper.class);

    private final ContestTeamBean contestTeamBean;
    private final Long contestId;

    public ContestTeamWrapper(ContestTeamBean contestTeamBean) {
        this.contestTeamBean = contestTeamBean;
        this.contestId = contestTeamBean.getContestId();
    }

    public void updateContestTeamMembers() {
        removeAllContestTeamMembersForContest();
        assignMemberToContest(MemberRole.JUDGE, contestTeamBean.getUserIdsJudges());
        assignMemberToContest(MemberRole.ADVISOR, contestTeamBean.getUserIdsAdvisors());
        assignMemberToContest(MemberRole.FELLOW, contestTeamBean.getUserIdsFellows());
        assignMemberToContest(MemberRole.CONTEST_MANAGER, contestTeamBean.getUserIdsContestManagers());
        assignMemberToContest(MemberRole.IMPACT_ASSESSMENT_FELLOW, contestTeamBean.getUserIdsIAFellows());
    }

    private void assignMemberToContest(MemberRole memberRole, List<Long> userIds) {
        assignMembersToContestWithRole(userIds, memberRole);
        assignMemberRoleToUser(memberRole, userIds);
        subscribeUsersToContest(userIds);
        crossColabRegisterUsersForSharedContests(userIds);
    }
    private void crossColabRegisterUsersForSharedContests(List<Long> userIds){

        for(Long id: userIds) {
            SharedColabUtil.checkTriggerForAutoUserCreationInContest(contestId, id);
        }
    }

    private void assignMemberRoleToUser(MemberRole memberRole, List<Long> userIds) {
        for (Long userId : userIds) {
            Long roleId = memberRole.getRoleId();
            MembersClient.assignMemberRole(userId, roleId);
        }
    }

    private void assignMembersToContestWithRole(List<Long> userIds, MemberRole memberRole) {
        for (Long userId : userIds) {

            ContestTeamMember contestTeamMember = new ContestTeamMember();
            contestTeamMember.setContestId(contestId);
            contestTeamMember.setUserId(userId);
            contestTeamMember.setRoleId(memberRole.getRoleId());
            ContestTeamMemberClientUtil.createContestTeamMember(contestTeamMember);
        }
    }

    private void removeAllContestTeamMembersForContest() {
        List<ContestTeamMember> contestTeamMembers = ContestTeamMemberClientUtil.getTeamMembers(contestId);
        for (ContestTeamMember contestTeamMember : contestTeamMembers) {
            try {
                ContestTeamMemberClientUtil.deleteContestTeamMember(contestTeamMember.getId_());
            } catch (UncheckedEntityNotFoundException e) {
                log.warn("ContestTeamMember {} already deleted", contestTeamMember.getId_());
            }
        }
    }

    private void subscribeUsersToContest(List<Long> userIds) {
        for (Long userId : userIds) {
            ActivitiesClientUtil.addSubscription(userId, ActivityEntryType.CONTEST, contestId, "");
        }
    }
}
