package com.ext.portlet.service.persistence;

import com.ext.portlet.model.SpamReport;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the spam report service. This utility wraps {@link SpamReportPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SpamReportPersistence
 * @see SpamReportPersistenceImpl
 * @generated
 */
public class SpamReportUtil {
    private static SpamReportPersistence _persistence;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
     */
    public static void clearCache() {
        getPersistence().clearCache();
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
     */
    public static void clearCache(SpamReport spamReport) {
        getPersistence().clearCache(spamReport);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
     */
    public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().countWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
     */
    public static List<SpamReport> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<SpamReport> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<SpamReport> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static SpamReport update(SpamReport spamReport)
        throws SystemException {
        return getPersistence().update(spamReport);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static SpamReport update(SpamReport spamReport,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(spamReport, serviceContext);
    }

    /**
    * Returns all the spam reports where spamUserId = &#63;.
    *
    * @param spamUserId the spam user ID
    * @return the matching spam reports
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.SpamReport> findBySpamUserId(
        long spamUserId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findBySpamUserId(spamUserId);
    }

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
    public static java.util.List<com.ext.portlet.model.SpamReport> findBySpamUserId(
        long spamUserId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findBySpamUserId(spamUserId, start, end);
    }

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
    public static java.util.List<com.ext.portlet.model.SpamReport> findBySpamUserId(
        long spamUserId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySpamUserId(spamUserId, start, end, orderByComparator);
    }

    /**
    * Returns the first spam report in the ordered set where spamUserId = &#63;.
    *
    * @param spamUserId the spam user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching spam report
    * @throws com.ext.portlet.NoSuchSpamReportException if a matching spam report could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.SpamReport findBySpamUserId_First(
        long spamUserId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchSpamReportException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySpamUserId_First(spamUserId, orderByComparator);
    }

    /**
    * Returns the first spam report in the ordered set where spamUserId = &#63;.
    *
    * @param spamUserId the spam user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching spam report, or <code>null</code> if a matching spam report could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.SpamReport fetchBySpamUserId_First(
        long spamUserId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchBySpamUserId_First(spamUserId, orderByComparator);
    }

    /**
    * Returns the last spam report in the ordered set where spamUserId = &#63;.
    *
    * @param spamUserId the spam user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching spam report
    * @throws com.ext.portlet.NoSuchSpamReportException if a matching spam report could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.SpamReport findBySpamUserId_Last(
        long spamUserId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchSpamReportException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySpamUserId_Last(spamUserId, orderByComparator);
    }

    /**
    * Returns the last spam report in the ordered set where spamUserId = &#63;.
    *
    * @param spamUserId the spam user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching spam report, or <code>null</code> if a matching spam report could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.SpamReport fetchBySpamUserId_Last(
        long spamUserId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchBySpamUserId_Last(spamUserId, orderByComparator);
    }

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
    public static com.ext.portlet.model.SpamReport[] findBySpamUserId_PrevAndNext(
        long id_, long spamUserId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchSpamReportException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySpamUserId_PrevAndNext(id_, spamUserId,
            orderByComparator);
    }

    /**
    * Removes all the spam reports where spamUserId = &#63; from the database.
    *
    * @param spamUserId the spam user ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeBySpamUserId(long spamUserId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeBySpamUserId(spamUserId);
    }

    /**
    * Returns the number of spam reports where spamUserId = &#63;.
    *
    * @param spamUserId the spam user ID
    * @return the number of matching spam reports
    * @throws SystemException if a system exception occurred
    */
    public static int countBySpamUserId(long spamUserId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countBySpamUserId(spamUserId);
    }

    /**
    * Returns all the spam reports where discussionMessageId = &#63;.
    *
    * @param discussionMessageId the discussion message ID
    * @return the matching spam reports
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.SpamReport> findByDiscussionMessageId(
        long discussionMessageId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByDiscussionMessageId(discussionMessageId);
    }

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
    public static java.util.List<com.ext.portlet.model.SpamReport> findByDiscussionMessageId(
        long discussionMessageId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByDiscussionMessageId(discussionMessageId, start, end);
    }

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
    public static java.util.List<com.ext.portlet.model.SpamReport> findByDiscussionMessageId(
        long discussionMessageId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByDiscussionMessageId(discussionMessageId, start, end,
            orderByComparator);
    }

    /**
    * Returns the first spam report in the ordered set where discussionMessageId = &#63;.
    *
    * @param discussionMessageId the discussion message ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching spam report
    * @throws com.ext.portlet.NoSuchSpamReportException if a matching spam report could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.SpamReport findByDiscussionMessageId_First(
        long discussionMessageId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchSpamReportException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByDiscussionMessageId_First(discussionMessageId,
            orderByComparator);
    }

    /**
    * Returns the first spam report in the ordered set where discussionMessageId = &#63;.
    *
    * @param discussionMessageId the discussion message ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching spam report, or <code>null</code> if a matching spam report could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.SpamReport fetchByDiscussionMessageId_First(
        long discussionMessageId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByDiscussionMessageId_First(discussionMessageId,
            orderByComparator);
    }

    /**
    * Returns the last spam report in the ordered set where discussionMessageId = &#63;.
    *
    * @param discussionMessageId the discussion message ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching spam report
    * @throws com.ext.portlet.NoSuchSpamReportException if a matching spam report could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.SpamReport findByDiscussionMessageId_Last(
        long discussionMessageId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchSpamReportException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByDiscussionMessageId_Last(discussionMessageId,
            orderByComparator);
    }

    /**
    * Returns the last spam report in the ordered set where discussionMessageId = &#63;.
    *
    * @param discussionMessageId the discussion message ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching spam report, or <code>null</code> if a matching spam report could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.SpamReport fetchByDiscussionMessageId_Last(
        long discussionMessageId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByDiscussionMessageId_Last(discussionMessageId,
            orderByComparator);
    }

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
    public static com.ext.portlet.model.SpamReport[] findByDiscussionMessageId_PrevAndNext(
        long id_, long discussionMessageId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchSpamReportException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByDiscussionMessageId_PrevAndNext(id_,
            discussionMessageId, orderByComparator);
    }

    /**
    * Removes all the spam reports where discussionMessageId = &#63; from the database.
    *
    * @param discussionMessageId the discussion message ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByDiscussionMessageId(long discussionMessageId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByDiscussionMessageId(discussionMessageId);
    }

    /**
    * Returns the number of spam reports where discussionMessageId = &#63;.
    *
    * @param discussionMessageId the discussion message ID
    * @return the number of matching spam reports
    * @throws SystemException if a system exception occurred
    */
    public static int countByDiscussionMessageId(long discussionMessageId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByDiscussionMessageId(discussionMessageId);
    }

    /**
    * Returns all the spam reports where reporterUserId = &#63;.
    *
    * @param reporterUserId the reporter user ID
    * @return the matching spam reports
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.SpamReport> findByReporterUserId(
        long reporterUserId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByReporterUserId(reporterUserId);
    }

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
    public static java.util.List<com.ext.portlet.model.SpamReport> findByReporterUserId(
        long reporterUserId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByReporterUserId(reporterUserId, start, end);
    }

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
    public static java.util.List<com.ext.portlet.model.SpamReport> findByReporterUserId(
        long reporterUserId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByReporterUserId(reporterUserId, start, end,
            orderByComparator);
    }

    /**
    * Returns the first spam report in the ordered set where reporterUserId = &#63;.
    *
    * @param reporterUserId the reporter user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching spam report
    * @throws com.ext.portlet.NoSuchSpamReportException if a matching spam report could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.SpamReport findByReporterUserId_First(
        long reporterUserId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchSpamReportException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByReporterUserId_First(reporterUserId, orderByComparator);
    }

    /**
    * Returns the first spam report in the ordered set where reporterUserId = &#63;.
    *
    * @param reporterUserId the reporter user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching spam report, or <code>null</code> if a matching spam report could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.SpamReport fetchByReporterUserId_First(
        long reporterUserId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByReporterUserId_First(reporterUserId,
            orderByComparator);
    }

    /**
    * Returns the last spam report in the ordered set where reporterUserId = &#63;.
    *
    * @param reporterUserId the reporter user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching spam report
    * @throws com.ext.portlet.NoSuchSpamReportException if a matching spam report could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.SpamReport findByReporterUserId_Last(
        long reporterUserId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchSpamReportException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByReporterUserId_Last(reporterUserId, orderByComparator);
    }

    /**
    * Returns the last spam report in the ordered set where reporterUserId = &#63;.
    *
    * @param reporterUserId the reporter user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching spam report, or <code>null</code> if a matching spam report could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.SpamReport fetchByReporterUserId_Last(
        long reporterUserId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByReporterUserId_Last(reporterUserId, orderByComparator);
    }

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
    public static com.ext.portlet.model.SpamReport[] findByReporterUserId_PrevAndNext(
        long id_, long reporterUserId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchSpamReportException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByReporterUserId_PrevAndNext(id_, reporterUserId,
            orderByComparator);
    }

    /**
    * Removes all the spam reports where reporterUserId = &#63; from the database.
    *
    * @param reporterUserId the reporter user ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByReporterUserId(long reporterUserId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByReporterUserId(reporterUserId);
    }

    /**
    * Returns the number of spam reports where reporterUserId = &#63;.
    *
    * @param reporterUserId the reporter user ID
    * @return the number of matching spam reports
    * @throws SystemException if a system exception occurred
    */
    public static int countByReporterUserId(long reporterUserId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByReporterUserId(reporterUserId);
    }

    /**
    * Returns the spam report where reporterUserId = &#63; and discussionMessageId = &#63; or throws a {@link com.ext.portlet.NoSuchSpamReportException} if it could not be found.
    *
    * @param reporterUserId the reporter user ID
    * @param discussionMessageId the discussion message ID
    * @return the matching spam report
    * @throws com.ext.portlet.NoSuchSpamReportException if a matching spam report could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.SpamReport findByReporterUserIdDiscussionMessageId(
        long reporterUserId, long discussionMessageId)
        throws com.ext.portlet.NoSuchSpamReportException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByReporterUserIdDiscussionMessageId(reporterUserId,
            discussionMessageId);
    }

    /**
    * Returns the spam report where reporterUserId = &#63; and discussionMessageId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param reporterUserId the reporter user ID
    * @param discussionMessageId the discussion message ID
    * @return the matching spam report, or <code>null</code> if a matching spam report could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.SpamReport fetchByReporterUserIdDiscussionMessageId(
        long reporterUserId, long discussionMessageId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByReporterUserIdDiscussionMessageId(reporterUserId,
            discussionMessageId);
    }

    /**
    * Returns the spam report where reporterUserId = &#63; and discussionMessageId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param reporterUserId the reporter user ID
    * @param discussionMessageId the discussion message ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching spam report, or <code>null</code> if a matching spam report could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.SpamReport fetchByReporterUserIdDiscussionMessageId(
        long reporterUserId, long discussionMessageId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByReporterUserIdDiscussionMessageId(reporterUserId,
            discussionMessageId, retrieveFromCache);
    }

    /**
    * Removes the spam report where reporterUserId = &#63; and discussionMessageId = &#63; from the database.
    *
    * @param reporterUserId the reporter user ID
    * @param discussionMessageId the discussion message ID
    * @return the spam report that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.SpamReport removeByReporterUserIdDiscussionMessageId(
        long reporterUserId, long discussionMessageId)
        throws com.ext.portlet.NoSuchSpamReportException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .removeByReporterUserIdDiscussionMessageId(reporterUserId,
            discussionMessageId);
    }

    /**
    * Returns the number of spam reports where reporterUserId = &#63; and discussionMessageId = &#63;.
    *
    * @param reporterUserId the reporter user ID
    * @param discussionMessageId the discussion message ID
    * @return the number of matching spam reports
    * @throws SystemException if a system exception occurred
    */
    public static int countByReporterUserIdDiscussionMessageId(
        long reporterUserId, long discussionMessageId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByReporterUserIdDiscussionMessageId(reporterUserId,
            discussionMessageId);
    }

    /**
    * Returns all the spam reports where spamUserId = &#63; and discussionMessageId = &#63;.
    *
    * @param spamUserId the spam user ID
    * @param discussionMessageId the discussion message ID
    * @return the matching spam reports
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.SpamReport> findBySpamUserIdDiscussionMessageId(
        long spamUserId, long discussionMessageId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySpamUserIdDiscussionMessageId(spamUserId,
            discussionMessageId);
    }

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
    public static java.util.List<com.ext.portlet.model.SpamReport> findBySpamUserIdDiscussionMessageId(
        long spamUserId, long discussionMessageId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySpamUserIdDiscussionMessageId(spamUserId,
            discussionMessageId, start, end);
    }

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
    public static java.util.List<com.ext.portlet.model.SpamReport> findBySpamUserIdDiscussionMessageId(
        long spamUserId, long discussionMessageId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySpamUserIdDiscussionMessageId(spamUserId,
            discussionMessageId, start, end, orderByComparator);
    }

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
    public static com.ext.portlet.model.SpamReport findBySpamUserIdDiscussionMessageId_First(
        long spamUserId, long discussionMessageId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchSpamReportException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySpamUserIdDiscussionMessageId_First(spamUserId,
            discussionMessageId, orderByComparator);
    }

    /**
    * Returns the first spam report in the ordered set where spamUserId = &#63; and discussionMessageId = &#63;.
    *
    * @param spamUserId the spam user ID
    * @param discussionMessageId the discussion message ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching spam report, or <code>null</code> if a matching spam report could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.SpamReport fetchBySpamUserIdDiscussionMessageId_First(
        long spamUserId, long discussionMessageId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchBySpamUserIdDiscussionMessageId_First(spamUserId,
            discussionMessageId, orderByComparator);
    }

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
    public static com.ext.portlet.model.SpamReport findBySpamUserIdDiscussionMessageId_Last(
        long spamUserId, long discussionMessageId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchSpamReportException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySpamUserIdDiscussionMessageId_Last(spamUserId,
            discussionMessageId, orderByComparator);
    }

    /**
    * Returns the last spam report in the ordered set where spamUserId = &#63; and discussionMessageId = &#63;.
    *
    * @param spamUserId the spam user ID
    * @param discussionMessageId the discussion message ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching spam report, or <code>null</code> if a matching spam report could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.SpamReport fetchBySpamUserIdDiscussionMessageId_Last(
        long spamUserId, long discussionMessageId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchBySpamUserIdDiscussionMessageId_Last(spamUserId,
            discussionMessageId, orderByComparator);
    }

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
    public static com.ext.portlet.model.SpamReport[] findBySpamUserIdDiscussionMessageId_PrevAndNext(
        long id_, long spamUserId, long discussionMessageId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchSpamReportException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySpamUserIdDiscussionMessageId_PrevAndNext(id_,
            spamUserId, discussionMessageId, orderByComparator);
    }

    /**
    * Removes all the spam reports where spamUserId = &#63; and discussionMessageId = &#63; from the database.
    *
    * @param spamUserId the spam user ID
    * @param discussionMessageId the discussion message ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeBySpamUserIdDiscussionMessageId(long spamUserId,
        long discussionMessageId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence()
            .removeBySpamUserIdDiscussionMessageId(spamUserId,
            discussionMessageId);
    }

    /**
    * Returns the number of spam reports where spamUserId = &#63; and discussionMessageId = &#63;.
    *
    * @param spamUserId the spam user ID
    * @param discussionMessageId the discussion message ID
    * @return the number of matching spam reports
    * @throws SystemException if a system exception occurred
    */
    public static int countBySpamUserIdDiscussionMessageId(long spamUserId,
        long discussionMessageId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countBySpamUserIdDiscussionMessageId(spamUserId,
            discussionMessageId);
    }

    /**
    * Caches the spam report in the entity cache if it is enabled.
    *
    * @param spamReport the spam report
    */
    public static void cacheResult(com.ext.portlet.model.SpamReport spamReport) {
        getPersistence().cacheResult(spamReport);
    }

    /**
    * Caches the spam reports in the entity cache if it is enabled.
    *
    * @param spamReports the spam reports
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.SpamReport> spamReports) {
        getPersistence().cacheResult(spamReports);
    }

    /**
    * Creates a new spam report with the primary key. Does not add the spam report to the database.
    *
    * @param id_ the primary key for the new spam report
    * @return the new spam report
    */
    public static com.ext.portlet.model.SpamReport create(long id_) {
        return getPersistence().create(id_);
    }

    /**
    * Removes the spam report with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id_ the primary key of the spam report
    * @return the spam report that was removed
    * @throws com.ext.portlet.NoSuchSpamReportException if a spam report with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.SpamReport remove(long id_)
        throws com.ext.portlet.NoSuchSpamReportException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(id_);
    }

    public static com.ext.portlet.model.SpamReport updateImpl(
        com.ext.portlet.model.SpamReport spamReport)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(spamReport);
    }

    /**
    * Returns the spam report with the primary key or throws a {@link com.ext.portlet.NoSuchSpamReportException} if it could not be found.
    *
    * @param id_ the primary key of the spam report
    * @return the spam report
    * @throws com.ext.portlet.NoSuchSpamReportException if a spam report with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.SpamReport findByPrimaryKey(long id_)
        throws com.ext.portlet.NoSuchSpamReportException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(id_);
    }

    /**
    * Returns the spam report with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id_ the primary key of the spam report
    * @return the spam report, or <code>null</code> if a spam report with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.SpamReport fetchByPrimaryKey(long id_)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(id_);
    }

    /**
    * Returns all the spam reports.
    *
    * @return the spam reports
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.SpamReport> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

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
    public static java.util.List<com.ext.portlet.model.SpamReport> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

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
    public static java.util.List<com.ext.portlet.model.SpamReport> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the spam reports from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of spam reports.
    *
    * @return the number of spam reports
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static SpamReportPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (SpamReportPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    SpamReportPersistence.class.getName());

            ReferenceRegistry.registerReference(SpamReportUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(SpamReportPersistence persistence) {
    }
}
