package com.ext.portlet.service.impl;

import com.ext.portlet.model.ContestDiscussion;
import com.ext.portlet.service.base.ContestDiscussionLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the contest discussion local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.ContestDiscussionLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.ContestDiscussionLocalServiceBaseImpl
 * @see com.ext.portlet.service.ContestDiscussionLocalServiceUtil
 */
public class ContestDiscussionLocalServiceImpl
    extends ContestDiscussionLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.ContestDiscussionLocalServiceUtil} to access the contest discussion local service.
     */

    public Long getDiscussionIdByContestIdAndTabName(Long contestId, String tabName) throws SystemException, PortalException {
        ContestDiscussion contestDiscussion =  contestDiscussionPersistence.findByContestIdAndTab(contestId, tabName);
        return contestDiscussion.getDiscussionId();
    }
}
