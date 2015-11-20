package com.ext.portlet.service.persistence;

import com.ext.portlet.model.SpamReport;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the spam report service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SpamReportPersistenceImpl
 * @see SpamReportUtil
 * @generated
 */
public interface SpamReportPersistence extends BasePersistence<SpamReport> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link SpamReportUtil} to access the spam report persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the spam reports where spamUserId = &#63;.
    *
    * @param spamUserId the spam user ID
    * @return the matching spam reports
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.SpamReport> findBySpamUserId(
        long spamUserId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the spam reports where spamUserId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.SpamReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param spamUserId the spam user ID
    * @param start the lower bound of the range of spam reports
    * @param end the upper bound of the range of spam reports (not inclusive)
    * @return the range of matching spam reports
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.SpamReport> findBySpamUserId(
        long spamUserId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the spam reports where spamUserId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.SpamReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param spamUserId the spam user ID
    * @param start the lower bound of the range of spam reports
    * @param end the upper bound of the range of spam reports (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching spam reports
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.SpamReport> findBySpamUserId(
        long spamUserId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first spam report in the ordered set where spamUserId = &#63;.
    *
    * @param spamUserId the spam user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching spam report
    * @throws com.ext.portlet.NoSuchSpamReportException if a matching spam report could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.SpamReport findBySpamUserId_First(
        long spamUserId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchSpamReportException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first spam report in the ordered set where spamUserId = &#63;.
    *
    * @param spamUserId the spam user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching spam report, or <code>null</code> if a matching spam report could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.SpamReport fetchBySpamUserId_First(
        long spamUserId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last spam report in the ordered set where spamUserId = &#63;.
    *
    * @param spamUserId the spam user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching spam report
    * @throws com.ext.portlet.NoSuchSpamReportException if a matching spam report could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.SpamReport findBySpamUserId_Last(
        long spamUserId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchSpamReportException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last spam report in the ordered set where spamUserId = &#63;.
    *
    * @param spamUserId the spam user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching spam report, or <code>null</code> if a matching spam report could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.SpamReport fetchBySpamUserId_Last(
        long spamUserId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the spam reports before and after the current spam report in the ordered set where spamUserId = &#63;.
    *
    * @param id_ the primary key of the current spam report
    * @param spamUserId the spam user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next spam report
    * @throws com.ext.portlet.NoSuchSpamReportException if a spam report with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.SpamReport[] findBySpamUserId_PrevAndNext(
        long id_, long spamUserId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchSpamReportException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the spam reports where spamUserId = &#63; from the database.
    *
    * @param spamUserId the spam user ID
    * @throws SystemException if a system exception occurred
    */
    public void removeBySpamUserId(long spamUserId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of spam reports where spamUserId = &#63;.
    *
    * @param spamUserId the spam user ID
    * @return the number of matching spam reports
    * @throws SystemException if a system exception occurred
    */
    public int countBySpamUserId(long spamUserId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the spam reports where discussionMessageId = &#63;.
    *
    * @param discussionMessageId the discussion message ID
    * @return the matching spam reports
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.SpamReport> findByDiscussionMessageId(
        long discussionMessageId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the spam reports where discussionMessageId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.SpamReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param discussionMessageId the discussion message ID
    * @param start the lower bound of the range of spam reports
    * @param end the upper bound of the range of spam reports (not inclusive)
    * @return the range of matching spam reports
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.SpamReport> findByDiscussionMessageId(
        long discussionMessageId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the spam reports where discussionMessageId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.SpamReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param discussionMessageId the discussion message ID
    * @param start the lower bound of the range of spam reports
    * @param end the upper bound of the range of spam reports (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching spam reports
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.SpamReport> findByDiscussionMessageId(
        long discussionMessageId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first spam report in the ordered set where discussionMessageId = &#63;.
    *
    * @param discussionMessageId the discussion message ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching spam report
    * @throws com.ext.portlet.NoSuchSpamReportException if a matching spam report could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.SpamReport findByDiscussionMessageId_First(
        long discussionMessageId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchSpamReportException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first spam report in the ordered set where discussionMessageId = &#63;.
    *
    * @param discussionMessageId the discussion message ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching spam report, or <code>null</code> if a matching spam report could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.SpamReport fetchByDiscussionMessageId_First(
        long discussionMessageId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last spam report in the ordered set where discussionMessageId = &#63;.
    *
    * @param discussionMessageId the discussion message ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching spam report
    * @throws com.ext.portlet.NoSuchSpamReportException if a matching spam report could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.SpamReport findByDiscussionMessageId_Last(
        long discussionMessageId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchSpamReportException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last spam report in the ordered set where discussionMessageId = &#63;.
    *
    * @param discussionMessageId the discussion message ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching spam report, or <code>null</code> if a matching spam report could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.SpamReport fetchByDiscussionMessageId_Last(
        long discussionMessageId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the spam reports before and after the current spam report in the ordered set where discussionMessageId = &#63;.
    *
    * @param id_ the primary key of the current spam report
    * @param discussionMessageId the discussion message ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next spam report
    * @throws com.ext.portlet.NoSuchSpamReportException if a spam report with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.SpamReport[] findByDiscussionMessageId_PrevAndNext(
        long id_, long discussionMessageId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchSpamReportException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the spam reports where discussionMessageId = &#63; from the database.
    *
    * @param discussionMessageId the discussion message ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByDiscussionMessageId(long discussionMessageId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of spam reports where discussionMessageId = &#63;.
    *
    * @param discussionMessageId the discussion message ID
    * @return the number of matching spam reports
    * @throws SystemException if a system exception occurred
    */
    public int countByDiscussionMessageId(long discussionMessageId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the spam reports where reporterUserId = &#63;.
    *
    * @param reporterUserId the reporter user ID
    * @return the matching spam reports
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.SpamReport> findByReporterUserId(
        long reporterUserId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the spam reports where reporterUserId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.SpamReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param reporterUserId the reporter user ID
    * @param start the lower bound of the range of spam reports
    * @param end the upper bound of the range of spam reports (not inclusive)
    * @return the range of matching spam reports
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.SpamReport> findByReporterUserId(
        long reporterUserId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the spam reports where reporterUserId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.SpamReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param reporterUserId the reporter user ID
    * @param start the lower bound of the range of spam reports
    * @param end the upper bound of the range of spam reports (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching spam reports
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.SpamReport> findByReporterUserId(
        long reporterUserId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first spam report in the ordered set where reporterUserId = &#63;.
    *
    * @param reporterUserId the reporter user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching spam report
    * @throws com.ext.portlet.NoSuchSpamReportException if a matching spam report could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.SpamReport findByReporterUserId_First(
        long reporterUserId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchSpamReportException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first spam report in the ordered set where reporterUserId = &#63;.
    *
    * @param reporterUserId the reporter user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching spam report, or <code>null</code> if a matching spam report could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.SpamReport fetchByReporterUserId_First(
        long reporterUserId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last spam report in the ordered set where reporterUserId = &#63;.
    *
    * @param reporterUserId the reporter user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching spam report
    * @throws com.ext.portlet.NoSuchSpamReportException if a matching spam report could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.SpamReport findByReporterUserId_Last(
        long reporterUserId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchSpamReportException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last spam report in the ordered set where reporterUserId = &#63;.
    *
    * @param reporterUserId the reporter user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching spam report, or <code>null</code> if a matching spam report could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.SpamReport fetchByReporterUserId_Last(
        long reporterUserId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the spam reports before and after the current spam report in the ordered set where reporterUserId = &#63;.
    *
    * @param id_ the primary key of the current spam report
    * @param reporterUserId the reporter user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next spam report
    * @throws com.ext.portlet.NoSuchSpamReportException if a spam report with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.SpamReport[] findByReporterUserId_PrevAndNext(
        long id_, long reporterUserId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchSpamReportException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the spam reports where reporterUserId = &#63; from the database.
    *
    * @param reporterUserId the reporter user ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByReporterUserId(long reporterUserId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of spam reports where reporterUserId = &#63;.
    *
    * @param reporterUserId the reporter user ID
    * @return the number of matching spam reports
    * @throws SystemException if a system exception occurred
    */
    public int countByReporterUserId(long reporterUserId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the spam report where reporterUserId = &#63; and discussionMessageId = &#63; or throws a {@link com.ext.portlet.NoSuchSpamReportException} if it could not be found.
    *
    * @param reporterUserId the reporter user ID
    * @param discussionMessageId the discussion message ID
    * @return the matching spam report
    * @throws com.ext.portlet.NoSuchSpamReportException if a matching spam report could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.SpamReport findByReporterUserIdDiscussionMessageId(
        long reporterUserId, long discussionMessageId)
        throws com.ext.portlet.NoSuchSpamReportException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the spam report where reporterUserId = &#63; and discussionMessageId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param reporterUserId the reporter user ID
    * @param discussionMessageId the discussion message ID
    * @return the matching spam report, or <code>null</code> if a matching spam report could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.SpamReport fetchByReporterUserIdDiscussionMessageId(
        long reporterUserId, long discussionMessageId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the spam report where reporterUserId = &#63; and discussionMessageId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param reporterUserId the reporter user ID
    * @param discussionMessageId the discussion message ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching spam report, or <code>null</code> if a matching spam report could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.SpamReport fetchByReporterUserIdDiscussionMessageId(
        long reporterUserId, long discussionMessageId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the spam report where reporterUserId = &#63; and discussionMessageId = &#63; from the database.
    *
    * @param reporterUserId the reporter user ID
    * @param discussionMessageId the discussion message ID
    * @return the spam report that was removed
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.SpamReport removeByReporterUserIdDiscussionMessageId(
        long reporterUserId, long discussionMessageId)
        throws com.ext.portlet.NoSuchSpamReportException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of spam reports where reporterUserId = &#63; and discussionMessageId = &#63;.
    *
    * @param reporterUserId the reporter user ID
    * @param discussionMessageId the discussion message ID
    * @return the number of matching spam reports
    * @throws SystemException if a system exception occurred
    */
    public int countByReporterUserIdDiscussionMessageId(long reporterUserId,
        long discussionMessageId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the spam reports where spamUserId = &#63; and discussionMessageId = &#63;.
    *
    * @param spamUserId the spam user ID
    * @param discussionMessageId the discussion message ID
    * @return the matching spam reports
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.SpamReport> findBySpamUserIdDiscussionMessageId(
        long spamUserId, long discussionMessageId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the spam reports where spamUserId = &#63; and discussionMessageId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.SpamReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param spamUserId the spam user ID
    * @param discussionMessageId the discussion message ID
    * @param start the lower bound of the range of spam reports
    * @param end the upper bound of the range of spam reports (not inclusive)
    * @return the range of matching spam reports
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.SpamReport> findBySpamUserIdDiscussionMessageId(
        long spamUserId, long discussionMessageId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the spam reports where spamUserId = &#63; and discussionMessageId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.SpamReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param spamUserId the spam user ID
    * @param discussionMessageId the discussion message ID
    * @param start the lower bound of the range of spam reports
    * @param end the upper bound of the range of spam reports (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching spam reports
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.SpamReport> findBySpamUserIdDiscussionMessageId(
        long spamUserId, long discussionMessageId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first spam report in the ordered set where spamUserId = &#63; and discussionMessageId = &#63;.
    *
    * @param spamUserId the spam user ID
    * @param discussionMessageId the discussion message ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching spam report
    * @throws com.ext.portlet.NoSuchSpamReportException if a matching spam report could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.SpamReport findBySpamUserIdDiscussionMessageId_First(
        long spamUserId, long discussionMessageId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchSpamReportException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first spam report in the ordered set where spamUserId = &#63; and discussionMessageId = &#63;.
    *
    * @param spamUserId the spam user ID
    * @param discussionMessageId the discussion message ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching spam report, or <code>null</code> if a matching spam report could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.SpamReport fetchBySpamUserIdDiscussionMessageId_First(
        long spamUserId, long discussionMessageId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last spam report in the ordered set where spamUserId = &#63; and discussionMessageId = &#63;.
    *
    * @param spamUserId the spam user ID
    * @param discussionMessageId the discussion message ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching spam report
    * @throws com.ext.portlet.NoSuchSpamReportException if a matching spam report could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.SpamReport findBySpamUserIdDiscussionMessageId_Last(
        long spamUserId, long discussionMessageId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchSpamReportException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last spam report in the ordered set where spamUserId = &#63; and discussionMessageId = &#63;.
    *
    * @param spamUserId the spam user ID
    * @param discussionMessageId the discussion message ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching spam report, or <code>null</code> if a matching spam report could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.SpamReport fetchBySpamUserIdDiscussionMessageId_Last(
        long spamUserId, long discussionMessageId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the spam reports before and after the current spam report in the ordered set where spamUserId = &#63; and discussionMessageId = &#63;.
    *
    * @param id_ the primary key of the current spam report
    * @param spamUserId the spam user ID
    * @param discussionMessageId the discussion message ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next spam report
    * @throws com.ext.portlet.NoSuchSpamReportException if a spam report with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.SpamReport[] findBySpamUserIdDiscussionMessageId_PrevAndNext(
        long id_, long spamUserId, long discussionMessageId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchSpamReportException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the spam reports where spamUserId = &#63; and discussionMessageId = &#63; from the database.
    *
    * @param spamUserId the spam user ID
    * @param discussionMessageId the discussion message ID
    * @throws SystemException if a system exception occurred
    */
    public void removeBySpamUserIdDiscussionMessageId(long spamUserId,
        long discussionMessageId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of spam reports where spamUserId = &#63; and discussionMessageId = &#63;.
    *
    * @param spamUserId the spam user ID
    * @param discussionMessageId the discussion message ID
    * @return the number of matching spam reports
    * @throws SystemException if a system exception occurred
    */
    public int countBySpamUserIdDiscussionMessageId(long spamUserId,
        long discussionMessageId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the spam report in the entity cache if it is enabled.
    *
    * @param spamReport the spam report
    */
    public void cacheResult(com.ext.portlet.model.SpamReport spamReport);

    /**
    * Caches the spam reports in the entity cache if it is enabled.
    *
    * @param spamReports the spam reports
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.SpamReport> spamReports);

    /**
    * Creates a new spam report with the primary key. Does not add the spam report to the database.
    *
    * @param id_ the primary key for the new spam report
    * @return the new spam report
    */
    public com.ext.portlet.model.SpamReport create(long id_);

    /**
    * Removes the spam report with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id_ the primary key of the spam report
    * @return the spam report that was removed
    * @throws com.ext.portlet.NoSuchSpamReportException if a spam report with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.SpamReport remove(long id_)
        throws com.ext.portlet.NoSuchSpamReportException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.SpamReport updateImpl(
        com.ext.portlet.model.SpamReport spamReport)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the spam report with the primary key or throws a {@link com.ext.portlet.NoSuchSpamReportException} if it could not be found.
    *
    * @param id_ the primary key of the spam report
    * @return the spam report
    * @throws com.ext.portlet.NoSuchSpamReportException if a spam report with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.SpamReport findByPrimaryKey(long id_)
        throws com.ext.portlet.NoSuchSpamReportException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the spam report with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id_ the primary key of the spam report
    * @return the spam report, or <code>null</code> if a spam report with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.SpamReport fetchByPrimaryKey(long id_)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the spam reports.
    *
    * @return the spam reports
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.SpamReport> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the spam reports.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.SpamReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of spam reports
    * @param end the upper bound of the range of spam reports (not inclusive)
    * @return the range of spam reports
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.SpamReport> findAll(int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the spam reports.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.SpamReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of spam reports
    * @param end the upper bound of the range of spam reports (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of spam reports
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.SpamReport> findAll(int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the spam reports from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of spam reports.
    *
    * @return the number of spam reports
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
