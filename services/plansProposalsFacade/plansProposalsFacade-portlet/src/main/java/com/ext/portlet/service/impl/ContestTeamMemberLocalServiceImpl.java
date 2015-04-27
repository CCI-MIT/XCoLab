package com.ext.portlet.service.impl;

import java.util.List;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestTeamMember;
import com.ext.portlet.model.impl.ContestTeamMemberImpl;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestTeamMemberLocalServiceUtil;
import com.ext.portlet.service.base.ContestTeamMemberLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * The implementation of the contest team member local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.ContestTeamMemberLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.ContestTeamMemberLocalServiceBaseImpl
 * @see com.ext.portlet.service.ContestTeamMemberLocalServiceUtil
 */
public class ContestTeamMemberLocalServiceImpl
    extends ContestTeamMemberLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.ContestTeamMemberLocalServiceUtil} to access the contest team member local service.
     */
    

    
    public ContestTeamMember addContestTeamMember(Long userId, Long contestPk, String role) throws SystemException {
        ContestTeamMember member = ContestTeamMemberLocalServiceUtil.createContestTeamMember(CounterLocalServiceUtil.increment(ContestTeamMember.class.getName()));
        member.setUserId(userId);
        member.setContestId(contestPk);
        member.setRole(role);
        store(member);
        
        return member;
    }
    
    public List<ContestTeamMember> findForContest(Long contestPk) throws SystemException {
        return contestTeamMemberPersistence.findByContestId(contestPk);
    }
    

    public void store(ContestTeamMember contestTeamMember) throws SystemException {
        if (contestTeamMember.isNew()) {
            if (contestTeamMember.getId() <= 0) {
                contestTeamMember.setId(CounterLocalServiceUtil.increment(ContestTeamMember.class.getName()));
            }
            ContestTeamMemberLocalServiceUtil.addContestTeamMember(contestTeamMember);
        }
        else {
            ContestTeamMemberLocalServiceUtil.updateContestTeamMember(contestTeamMember);
        }
    }
    
    public void delete(ContestTeamMember contestTeamMember) throws SystemException {
        ContestTeamMemberLocalServiceUtil.deleteContestTeamMember(contestTeamMember);
    }
    
    public User getUser(ContestTeamMember contestTeamMember) throws PortalException, SystemException {
        return UserLocalServiceUtil.getUser(contestTeamMember.getUserId());
    }
    
    public Contest getContest(ContestTeamMember contestTeamMember) throws PortalException, SystemException {
        return ContestLocalServiceUtil.getContest(contestTeamMember.getContestId());
    }
}
