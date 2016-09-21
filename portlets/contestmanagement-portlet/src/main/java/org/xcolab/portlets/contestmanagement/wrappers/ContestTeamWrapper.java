package org.xcolab.portlets.contestmanagement.wrappers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Role;
import com.liferay.portal.service.RoleLocalServiceUtil;

import org.xcolab.client.activities.ActivitiesClient;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.pojo.ContestTeamMember;
import org.xcolab.enums.MemberRole;
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

    public void updateContestTeamMembers()
            throws SystemException, PortalException {
        removeAllContestTeamMembersForContest();
        assignMemberToContest(MemberRole.JUDGE, contestTeamBean.getUserIdsJudges());
        assignMemberToContest(MemberRole.ADVISOR, contestTeamBean.getUserIdsAdvisors());
        assignMemberToContest(MemberRole.FELLOW, contestTeamBean.getUserIdsFellows());
        assignMemberToContest(MemberRole.CONTEST_MANAGER, contestTeamBean.getUserIdsContestManagers());
    }

    private void assignMemberToContest(MemberRole memberRole, List<Long> userIds)
            throws SystemException, PortalException {
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

    private void assignMemberRoleToUser(MemberRole memberRole, List<Long> userIds)
            throws SystemException, PortalException {
        for (Long userId : userIds) {
            Long roleId = memberRole.getRoleId();
            Role role = RoleLocalServiceUtil.getRole(roleId);
            RoleLocalServiceUtil.addUserRole(userId, roleId);
            RoleLocalServiceUtil.updateRole(role);
        }
    }

    private void assignMembersToContestWithRole(List<Long> userIds, MemberRole memberRole) {
        for (Long userId : userIds) {

            ContestTeamMember contestTeamMember = new ContestTeamMember();
            contestTeamMember.setContestId(contestId);
            contestTeamMember.setUserId(userId);
            contestTeamMember.setRoleId(memberRole.getRoleId());
            ContestClient.createContestTeamMember(contestTeamMember);
        }
    }

    private void removeAllContestTeamMembersForContest() {
        List<ContestTeamMember> contestTeamMembers = ContestClient.getTeamMembers(contestId);
        for (ContestTeamMember contestTeamMember : contestTeamMembers) {
            try {
                ContestClient.deleteContestTeamMember(contestTeamMember.getId_());
            } catch (UncheckedEntityNotFoundException e) {
                log.warn("ContestTeamMember {} already deleted", contestTeamMember.getId_());
            }
        }
    }

    private void subscribeUsersToContest(List<Long> userIds) {
        for (Long userId : userIds) {
            ActivitiesClient.addSubscription(userId, ActivityEntryType.CONTEST, contestId, "");
        }
    }
}
