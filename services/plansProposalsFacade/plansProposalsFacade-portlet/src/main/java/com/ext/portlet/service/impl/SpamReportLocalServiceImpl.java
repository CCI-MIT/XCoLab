package com.ext.portlet.service.impl;

import com.ext.portlet.model.SpamReport;
import com.ext.portlet.service.base.SpamReportLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * The implementation of the spam report local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.SpamReportLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.SpamReportLocalServiceBaseImpl
 * @see com.ext.portlet.service.SpamReportLocalServiceUtil
 */
public class SpamReportLocalServiceImpl extends SpamReportLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.SpamReportLocalServiceUtil} to access the spam report local service.
     */

    @Override
    public List<SpamReport> getBySpamUserId(long userId) throws SystemException {
        return spamReportPersistence.findBySpamUserId(userId);
    }

    @Override
    public List<SpamReport> getByDiscussionMessageId(long discussionMessageId) throws SystemException {
        return spamReportPersistence.findByDiscussionMessageId(discussionMessageId);
    }

    @Override
    public List<SpamReport> getByReporterUserId(long userId) throws SystemException {
        return spamReportPersistence.findByReporterUserId(userId);
    }

    @Override
    public List<SpamReport> getBySpamUserIdDiscussionMessageId(long userId, long discussionMessageId) throws SystemException {
        return spamReportPersistence.findBySpamUserIdDiscussionMessageId(userId, discussionMessageId);
    }
}
