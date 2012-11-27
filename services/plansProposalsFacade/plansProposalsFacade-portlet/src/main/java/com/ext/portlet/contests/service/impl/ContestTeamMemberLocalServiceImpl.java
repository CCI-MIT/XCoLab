package com.ext.portlet.contests.service.impl;

import java.util.List;

import com.ext.portlet.contests.model.ContestTeamMember;
import com.ext.portlet.contests.model.impl.ContestTeamMemberImpl;
import com.ext.portlet.contests.service.base.ContestTeamMemberLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the contest team member local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.contests.service.ContestTeamMemberLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.contests.service.base.ContestTeamMemberLocalServiceBaseImpl
 * @see com.ext.portlet.contests.service.ContestTeamMemberLocalServiceUtil
 */
public class ContestTeamMemberLocalServiceImpl
    extends ContestTeamMemberLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.contests.service.ContestTeamMemberLocalServiceUtil} to access the contest team member local service.
     */

    
    public ContestTeamMember addContestTeamMember(Long userId, Long contestPk, String role) throws SystemException {
        ContestTeamMember member = new ContestTeamMemberImpl();
        member.setUserId(userId);
        member.setContestId(contestPk);
        member.setRole(role);
        member.store();
        
        return member;
    }
    
    public List<ContestTeamMember> findForContest(Long contestPk) throws SystemException {
        return contestTeamMemberPersistence.findByContestId(contestPk);
    }
}
