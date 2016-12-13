package com.ext.portlet.service.impl;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestTeamMember;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestTeamMemberLocalServiceUtil;
import com.ext.portlet.service.base.ContestTeamMemberLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.xcolab.enums.MemberRole;

import java.util.List;

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

}
