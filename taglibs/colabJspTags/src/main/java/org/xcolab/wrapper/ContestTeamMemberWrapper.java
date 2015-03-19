package org.xcolab.wrapper;

import com.ext.portlet.model.ContestTeamMember;
import com.ext.portlet.service.ContestTeamMemberLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

public class ContestTeamMemberWrapper {

    private ContestTeamMember wrapped;

    public ContestTeamMemberWrapper(ContestTeamMember wrapped) {
        this.wrapped = wrapped;
    }
    
    public User getUser() throws PortalException, SystemException {
        return ContestTeamMemberLocalServiceUtil.getUser(wrapped);
    }
    
    public String getRole() {
        return wrapped.getRole();
    }
    
    public Long getUserId() {
        return wrapped.getUserId();
    }
}
