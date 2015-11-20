package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for SpamReport. This utility wraps
 * {@link com.ext.portlet.service.impl.SpamReportLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see SpamReportLocalService
 * @see com.ext.portlet.service.base.SpamReportLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.SpamReportLocalServiceImpl
 * @generated
 */
public class SpamReportLocalServiceUtil {
    private static SpamReportLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.SpamReportLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the spam report to the database. Also notifies the appropriate model listeners.
    *
    * @param spamReport the spam report
    * @return the spam report that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.SpamReport addSpamReport(
        com.ext.portlet.model.SpamReport spamReport)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addSpamReport(spamReport);
    }

    /**
    * Creates a new spam report with the primary key. Does not add the spam report to the database.
    *
    * @param id_ the primary key for the new spam report
    * @return the new spam report
    */
    public static com.ext.portlet.model.SpamReport createSpamReport(long id_) {
        return getService().createSpamReport(id_);
    }

    /**
    * Deletes the spam report with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id_ the primary key of the spam report
    * @return the spam report that was removed
    * @throws PortalException if a spam report with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.SpamReport deleteSpamReport(long id_)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().deleteSpamReport(id_);
    }

    /**
    * Deletes the spam report from the database. Also notifies the appropriate model listeners.
    *
    * @param spamReport the spam report
    * @return the spam report that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.SpamReport deleteSpamReport(
        com.ext.portlet.model.SpamReport spamReport)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().deleteSpamReport(spamReport);
    }

    public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return getService().dynamicQuery();
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.SpamReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.SpamReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.liferay.portal.kernel.dao.orm.Projection projection)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery, projection);
    }

    public static com.ext.portlet.model.SpamReport fetchSpamReport(long id_)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchSpamReport(id_);
    }

    /**
    * Returns the spam report with the primary key.
    *
    * @param id_ the primary key of the spam report
    * @return the spam report
    * @throws PortalException if a spam report with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.SpamReport getSpamReport(long id_)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getSpamReport(id_);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
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
    public static java.util.List<com.ext.portlet.model.SpamReport> getSpamReports(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getSpamReports(start, end);
    }

    /**
    * Returns the number of spam reports.
    *
    * @return the number of spam reports
    * @throws SystemException if a system exception occurred
    */
    public static int getSpamReportsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getSpamReportsCount();
    }

    /**
    * Updates the spam report in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param spamReport the spam report
    * @return the spam report that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.SpamReport updateSpamReport(
        com.ext.portlet.model.SpamReport spamReport)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateSpamReport(spamReport);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public static java.lang.String getBeanIdentifier() {
        return getService().getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public static void setBeanIdentifier(java.lang.String beanIdentifier) {
        getService().setBeanIdentifier(beanIdentifier);
    }

    public static java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return getService().invokeMethod(name, parameterTypes, arguments);
    }

    public static com.ext.portlet.model.SpamReport create()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().create();
    }

    public static com.ext.portlet.model.SpamReport create(
        long discussionMessageId, long spamUserId, long reporterUserId,
        boolean isAdminReport)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .create(discussionMessageId, spamUserId, reporterUserId,
            isAdminReport);
    }

    public static java.util.List<com.ext.portlet.model.SpamReport> getBySpamUserId(
        long userId) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getBySpamUserId(userId);
    }

    public static java.util.List<com.ext.portlet.model.SpamReport> getByDiscussionMessageId(
        long discussionMessageId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getByDiscussionMessageId(discussionMessageId);
    }

    public static java.util.List<com.ext.portlet.model.SpamReport> getByReporterUserId(
        long userId) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getByReporterUserId(userId);
    }

    public static boolean hasReporterUserIdDiscussionMessageId(long userId,
        long discussionMessageId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .hasReporterUserIdDiscussionMessageId(userId,
            discussionMessageId);
    }

    public static com.ext.portlet.model.SpamReport getByReporterUserIdDiscussionMessageId(
        long userId, long discussionMessageId)
        throws com.ext.portlet.NoSuchSpamReportException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getByReporterUserIdDiscussionMessageId(userId,
            discussionMessageId);
    }

    public static java.util.List<com.ext.portlet.model.SpamReport> getBySpamUserIdDiscussionMessageId(
        long userId, long discussionMessageId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getBySpamUserIdDiscussionMessageId(userId,
            discussionMessageId);
    }

    public static void clearService() {
        _service = null;
    }

    public static SpamReportLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    SpamReportLocalService.class.getName());

            if (invokableLocalService instanceof SpamReportLocalService) {
                _service = (SpamReportLocalService) invokableLocalService;
            } else {
                _service = new SpamReportLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(SpamReportLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(SpamReportLocalService service) {
    }
}
