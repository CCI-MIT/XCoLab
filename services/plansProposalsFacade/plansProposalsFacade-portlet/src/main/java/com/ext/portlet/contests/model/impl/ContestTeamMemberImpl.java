package com.ext.portlet.contests.model.impl;

import com.ext.portlet.contests.model.Contest;
import com.ext.portlet.contests.model.ContestTeamMember;
import com.ext.portlet.contests.service.ContestLocalServiceUtil;
import com.ext.portlet.contests.service.ContestTeamMemberLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * The extended model implementation for the ContestTeamMember service. Represents a row in the &quot;Contests_ContestTeamMember&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.contests.model.ContestTeamMember} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
public class ContestTeamMemberImpl extends ContestTeamMemberBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this class directly. All methods that expect a contest team member model instance should use the {@link com.ext.portlet.contests.model.ContestTeamMember} interface instead.
     */
    public ContestTeamMemberImpl() {
    }

}
