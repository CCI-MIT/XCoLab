package org.xcolab.interfaces;

import com.ext.portlet.model.Contest;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import org.xcolab.wrappers.BaseContestWrapper;

import javax.portlet.PortletRequest;
import java.io.Serializable;

public interface TabContext extends Serializable {

    Contest getContest(PortletRequest request) throws PortalException, SystemException;

    BaseContestWrapper getContestWrapped(PortletRequest request) throws PortalException, SystemException;

    TabPermissions getPermissions(PortletRequest request) throws PortalException, SystemException;

    User getUser(PortletRequest request) throws PortalException, SystemException;

    void invalidateContext(PortletRequest request);

}