package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for LoginLog. This utility wraps
 * {@link com.ext.portlet.service.impl.LoginLogLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see LoginLogLocalService
 * @see com.ext.portlet.service.base.LoginLogLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.LoginLogLocalServiceImpl
 * @generated
 */
public class LoginLogLocalServiceUtil {
    private static LoginLogLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.LoginLogLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the login log to the database. Also notifies the appropriate model listeners.
    *
    * @param loginLog the login log
    * @return the login log that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.LoginLog addLoginLog(
        com.ext.portlet.model.LoginLog loginLog)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addLoginLog(loginLog);
    }

    /**
    * Creates a new login log with the primary key. Does not add the login log to the database.
    *
    * @param pk the primary key for the new login log
    * @return the new login log
    */
    public static com.ext.portlet.model.LoginLog createLoginLog(long pk) {
        return getService().createLoginLog(pk);
    }

    /**
    * Deletes the login log with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param pk the primary key of the login log
    * @return the login log that was removed
    * @throws PortalException if a login log with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.LoginLog deleteLoginLog(long pk)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().deleteLoginLog(pk);
    }

    /**
    * Deletes the login log from the database. Also notifies the appropriate model listeners.
    *
    * @param loginLog the login log
    * @return the login log that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.LoginLog deleteLoginLog(
        com.ext.portlet.model.LoginLog loginLog)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().deleteLoginLog(loginLog);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.LoginLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.LoginLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.ext.portlet.model.LoginLog fetchLoginLog(long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchLoginLog(pk);
    }

    /**
    * Returns the login log with the primary key.
    *
    * @param pk the primary key of the login log
    * @return the login log
    * @throws PortalException if a login log with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.LoginLog getLoginLog(long pk)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getLoginLog(pk);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the login logs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.LoginLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of login logs
    * @param end the upper bound of the range of login logs (not inclusive)
    * @return the range of login logs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.LoginLog> getLoginLogs(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getLoginLogs(start, end);
    }

    /**
    * Returns the number of login logs.
    *
    * @return the number of login logs
    * @throws SystemException if a system exception occurred
    */
    public static int getLoginLogsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getLoginLogsCount();
    }

    /**
    * Updates the login log in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param loginLog the login log
    * @return the login log that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.LoginLog updateLoginLog(
        com.ext.portlet.model.LoginLog loginLog)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateLoginLog(loginLog);
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

    public static com.ext.portlet.model.LoginLog createLoginLog(
        com.liferay.portal.model.User user, java.lang.String ipAddr,
        java.lang.String entryUrl)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().createLoginLog(user, ipAddr, entryUrl);
    }

    public static void clearService() {
        _service = null;
    }

    public static LoginLogLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    LoginLogLocalService.class.getName());

            if (invokableLocalService instanceof LoginLogLocalService) {
                _service = (LoginLogLocalService) invokableLocalService;
            } else {
                _service = new LoginLogLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(LoginLogLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(LoginLogLocalService service) {
    }
}
