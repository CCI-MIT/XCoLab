package org.xcolab.portlets.contestmanagement.wrappers;

import com.ext.portlet.model.ContestTeamMember;
import com.ext.portlet.model.ContestTeamMemberWrapper;
import com.ext.portlet.service.ContestTeamMemberLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Role;
import com.liferay.portal.service.RoleLocalServiceUtil;
import org.xcolab.enums.MemberRole;
import org.xcolab.portlets.contestmanagement.beans.ContestTeamBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Thomas on 2/12/2015.
 */
public class ContestTeamWrapper {

    private ContestTeamBean contestTeamBean;
    private Long contestId;

    public ContestTeamWrapper(ContestTeamBean contestTeamBean){
        this.contestTeamBean = contestTeamBean;
        this.contestId = contestTeamBean.getContestId();
    }

    public void updateContestTeamMembers()
            throws SystemException, PortalException {
        removeAllContestTeamMembersForContest();
        assignMemberToContest(MemberRole.JUDGES,contestTeamBean.getUserIdsJudges());
        assignMemberToContest(MemberRole.ADVISOR, contestTeamBean.getUserIdsAdvisors());
        assignMemberToContest(MemberRole.FELLOW, contestTeamBean.getUserIdsFellows());
    }

    private void assignMemberToContest(MemberRole memberRole, List<Long> userIds)
            throws SystemException, PortalException {
        asssignMemberWithRoleToContest(memberRole, userIds);
        assignMemberRoleToUser(memberRole, userIds);
    }

    private void assignMemberRoleToUser(MemberRole memberRole, List<Long> userIds)
            throws SystemException, PortalException {
        for(Long userId : userIds) {
            Long roleId = memberRole.getRoleId();
            Role role = RoleLocalServiceUtil.getRole(roleId);
            RoleLocalServiceUtil.addUserRole(userId,roleId);
            RoleLocalServiceUtil.updateRole(role);
        }
    }

    private void asssignMemberWithRoleToContest(MemberRole memberRole, List<Long> userIds)
            throws SystemException, PortalException {
        String memberRoleName = memberRole.getPrintName();
        if (memberRole == MemberRole.JUDGES) memberRoleName = "Judge"; // TODO change in config file
        for (Long userId : userIds) {
            Long contestMemberId = CounterLocalServiceUtil.increment(ContestTeamMember.class.getName());
            ContestTeamMember contestTeamMember = ContestTeamMemberLocalServiceUtil.createContestTeamMember(contestMemberId);
            contestTeamMember.setContestId(contestId);
            contestTeamMember.setUserId(userId);
            contestTeamMember.setRole(memberRoleName);
            ContestTeamMemberLocalServiceUtil.addContestTeamMember(contestTeamMember);
        }
    }

    private  void removeAllContestTeamMembersForContest()
            throws SystemException, PortalException{
        List<ContestTeamMember> contestTeamMembers = ContestTeamMemberLocalServiceUtil.findForContest(contestId);
        for(ContestTeamMember contestTeamMember : contestTeamMembers){
            ContestTeamMemberLocalServiceUtil.deleteContestTeamMember(contestTeamMember);
        }
    }

    private void removeUserFromContestTeamMembers(Long contestId, Long userId, String memberRoleName)
            throws PortalException, SystemException{

        /*DynamicQuery queryContestTeamMemberByRoleAndUserIdAndContestId =
                DynamicQueryFactoryUtil.forClass(ContestTeamMember.class)
                        .add(PropertyFactoryUtil.forName("userId").eq(new Long(userId)))
                        .add(PropertyFactoryUtil.forName("role").eq(new String(memberRoleName)));*/


        DynamicQuery subQuery = DynamicQueryFactoryUtil.forClass(ContestTeamMember.class)
                .add(PropertyFactoryUtil.forName("id_").isNotEmpty())
                .setProjection(ProjectionFactoryUtil.max("id_"));

        List queryResult = ContestTeamMemberLocalServiceUtil.dynamicQuery(subQuery,0,1);
        ContestTeamMember contestTeamMember = (ContestTeamMember) queryResult.get(0);
        ContestTeamMemberLocalServiceUtil.deleteContestTeamMember(contestTeamMember);
    }
}
