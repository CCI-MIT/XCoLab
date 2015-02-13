package org.xcolab.interfaces;

import com.ext.portlet.model.*;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import org.xcolab.enums.MemberRole;
import org.xcolab.wrapper.ContestWrapper;

import javax.portlet.PortletRequest;

public interface TabPermissions {

    boolean getCanAdmin();

    boolean getCanStaff();

    boolean getCanRole(MemberRole role);

    boolean getCanDelete();

    boolean getCanCreate();

    boolean getIsOwner();

}