package org.xcolab.view.pages.contestmanagement.wrappers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.contest.ContestTeamMemberClientUtil;
import org.xcolab.client.contest.pojo.IContestTeamMember;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.permissions.SystemRole;
import org.xcolab.util.activities.enums.ActivityCategory;
import org.xcolab.util.http.exceptions.UncheckedEntityNotFoundException;
import org.xcolab.view.pages.contestmanagement.beans.ContestTeamBean;

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
        assignMemberToContest(SystemRole.JUDGE, contestTeamBean.getUserIdsJudges());
        assignMemberToContest(SystemRole.ADVISOR, contestTeamBean.getUserIdsAdvisors());
        assignMemberToContest(SystemRole.FELLOW, contestTeamBean.getUserIdsFellows());
        assignMemberToContest(SystemRole.CONTEST_MANAGER,
                contestTeamBean.getUserIdsContestManagers());
        assignMemberToContest(SystemRole.IMPACT_ASSESSMENT_FELLOW,
                contestTeamBean.getUserIdsIAFellows());
    }

    private void assignMemberToContest(SystemRole systemRole, List<Long> userIds) {
        assignMembersToContestWithRole(userIds, systemRole);
        assignMemberRoleToUser(systemRole, userIds);
        subscribeUsersToContest(userIds);
    }

    private void assignMemberRoleToUser(SystemRole systemRole, List<Long> userIds) {
        long roleId = systemRole.getRoleId();
        for (Long userId : userIds) {
            MembersClient.assignMemberRole(userId, roleId);
        }
    }

    private void assignMembersToContestWithRole(List<Long> userIds, SystemRole systemRole) {
        for (Long userId : userIds) {
            IContestTeamMember contestTeamMember = new IContestTeamMember();
            contestTeamMember.setContestId(contestId);
            contestTeamMember.setUserId(userId);
            contestTeamMember.setRoleId(systemRole.getRoleId());
            ContestTeamMemberClientUtil.createContestTeamMember(contestTeamMember);
        }
    }

    private void subscribeUsersToContest(List<Long> userIds) {
        for (Long userId : userIds) {
            ActivitiesClientUtil.addSubscription(userId, ActivityCategory.CONTEST, contestId, "");
        }
    }

    private void removeTeamMember(IContestTeamMember contestTeamMember) {
        try {
            ContestTeamMemberClientUtil.deleteContestTeamMember(contestTeamMember.getId());
        } catch (UncheckedEntityNotFoundException e) {
            log.warn("ContestTeamMember {} already deleted", contestTeamMember.getId());
        }
        Long userId = contestTeamMember.getUserId();
        Long roleId = contestTeamMember.getRoleId();
        if (ContestTeamMemberClientUtil.getTeamMembers(userId, null, roleId).isEmpty()) {
            MembersClient.removeMemberRole(userId, roleId);
        }

    }

    private void removeAllContestTeamMembersForContest() {
        List<IContestTeamMember> contestTeamMembers =
                ContestTeamMemberClientUtil.getTeamMembers(null, contestId, null);
        for (IContestTeamMember contestTeamMember : contestTeamMembers) {
            removeTeamMember(contestTeamMember);
        }
    }
}
