package org.xcolab.portlets.contestmanagement.wrappers;

import com.ext.portlet.model.ContestTeamMember;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestTeamMemberLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
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

import java.util.List;

/**
 * Created by Thomas on 2/12/2015.
 */
public class ContestTeamWrapper {

    private ContestTeamBean contestTeamBean;
    private Long contestId;
    private static final String ENTITY_CLASS_LOADER_CONTEXT = "plansProposalsFacade-portlet";

    public ContestTeamWrapper(ContestTeamBean contestTeamBean){
        this.contestTeamBean = contestTeamBean;
        this.contestId = contestTeamBean.getContestId();
    }

    public void updateContestTeamMembers()
            throws Exception {
        removeAllContestTeamMembersForContest();
        assignMemberToContest(MemberRole.JUDGES, contestTeamBean.getUserIdsJudges());
        assignMemberToContest(MemberRole.ADVISOR, contestTeamBean.getUserIdsAdvisors());
        assignMemberToContest(MemberRole.FELLOW, contestTeamBean.getUserIdsFellows());
        assignMemberToContest(MemberRole.CONTESTMANAGER, contestTeamBean.getUserIdsContestManagers());
    }

    private void assignMemberToContest(MemberRole memberRole, List<Long> userIds)
            throws Exception{
        assignMemberWithRoleToContest(memberRole, userIds);
        assignMemberRoleToUser(memberRole, userIds);
        subscribeUserToContest(userIds);
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

    private void assignMemberWithRoleToContest(MemberRole memberRole, List<Long> userIds)
            throws SystemException, PortalException {
        String memberRoleName = memberRole.getPrintName();
        if (memberRole == MemberRole.JUDGES) memberRoleName = "Judge"; // TODO change in config file
        for (Long userId : userIds) {
            Long contestMemberId = CounterLocalServiceUtil.increment(ContestTeamMember.class.getName());
            if(contestMemberId < 400){ // TODO check how the value is currently generated
                CounterLocalServiceUtil.reset(ContestTeamMember.class.getName(),400);
                contestMemberId = CounterLocalServiceUtil.increment(ContestTeamMember.class.getName());
            }
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
            String contestTeamMemberRole = contestTeamMember.getRole();
            Long userId = contestTeamMember.getUserId();
            ContestTeamMemberLocalServiceUtil.deleteContestTeamMember(contestTeamMember);
            removeUserRoleIfNotUsedInAnotherContest(userId, contestTeamMemberRole);
        }
    }


    private void removeUserRoleIfNotUsedInAnotherContest(Long userId, String memberRoleName){
        try {
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(
                    ENTITY_CLASS_LOADER_CONTEXT, "portletClassLoader");

            DynamicQuery queryContestTeamMembershipsByRoleAndUserId =
                    DynamicQueryFactoryUtil.forClass(ContestTeamMember.class, portletClassLoader)
                            .add(PropertyFactoryUtil.forName("userId").eq(new Long(userId)))
                            .add(PropertyFactoryUtil.forName("role").eq(new String(memberRoleName)))
                            .setProjection(ProjectionFactoryUtil.count("role"));

            List queryResult = ContestTeamMemberLocalServiceUtil.dynamicQuery(queryContestTeamMembershipsByRoleAndUserId);
            Long roleCount =  (Long) queryResult.get(0);

            if ( roleCount ==  0 ) {
                MemberRole memberRole = MemberRole.getMember(memberRoleName);
                Long roleId = memberRole.getRoleId();
                RoleLocalServiceUtil.deleteUserRole(userId, roleId);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void subscribeUserToContest(List<Long> userIds) throws Exception{
        for (Long userId : userIds) {
            ContestLocalServiceUtil.subscribe(contestId, userId);
        }
    }
}
